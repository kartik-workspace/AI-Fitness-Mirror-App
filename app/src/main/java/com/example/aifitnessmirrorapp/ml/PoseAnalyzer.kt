package com.example.aifitnessmirrorapp.ml

import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.pose.Pose
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.PoseLandmark
import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions
import kotlin.math.abs

class PoseAnalyzer : ImageAnalysis.Analyzer {
    private val poseDetector = PoseDetection.getClient(
        AccuratePoseDetectorOptions.Builder()
            .setDetectorMode(AccuratePoseDetectorOptions.STREAM_MODE)
            .build()
    )

    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image ?: return
        val rotationDegrees = imageProxy.imageInfo.rotationDegrees

        val inputImage = InputImage.fromMediaImage(mediaImage, rotationDegrees)
        poseDetector.process(inputImage)
            .addOnSuccessListener { pose ->
                processPose(pose)
            }
            .addOnCompleteListener {
                imageProxy.close()
            }
    }

    private fun processPose(pose: Pose) {
        val leftShoulder = pose.getPoseLandmark(PoseLandmark.LEFT_SHOULDER)
        val rightShoulder = pose.getPoseLandmark(PoseLandmark.RIGHT_SHOULDER)
        val leftHip = pose.getPoseLandmark(PoseLandmark.LEFT_HIP)
        val rightHip = pose.getPoseLandmark(PoseLandmark.RIGHT_HIP)

        // Example: check if back is straight
        if (leftShoulder != null && leftHip != null && rightShoulder != null && rightHip != null) {
            val slopeLeft = (leftShoulder.position.y - leftHip.position.y) /
                    (leftShoulder.position.x - leftHip.position.x)
            val slopeRight = (rightShoulder.position.y - rightHip.position.y) /
                    (rightShoulder.position.x - rightHip.position.x)

            if (abs(slopeLeft - slopeRight) < 0.2) {
                FeedbackManager.say("Good posture!")
            } else {
                FeedbackManager.say("Straighten your back")
            }
        }
    }
}
