//package com.example.aifitnessmirrorapp.utils
//
//// AngleUtils.kt
//import com.google.mlkit.common.PointF3D
//import kotlin.math.acos
//import kotlin.math.sqrt
//
//object AngleUtils {
//    // Compute angle ABC (B is vertex) in degrees
//    fun angleBetween(a: PointF3D, b: PointF3D, c: PointF3D): Double {
//        val abx = a.x - b.x
//        val aby = a.y - b.y
//        val abz = a.z - b.z
//
//        val cbx = c.x - b.x
//        val cby = c.y - b.y
//        val cbz = c.z - b.z
//
//        val dot = (abx * cbx + aby * cby + abz * cbz).toDouble()
//        val magAB = sqrt((abx*abx + aby*aby + abz*abz).toDouble())
//        val magCB = sqrt((cbx*cbx + cby*cby + cbz*cbz).toDouble())
//
//        if (magAB == 0.0 || magCB == 0.0) return 0.0
//        val cosVal = (dot / (magAB * magCB)).coerceIn(-1.0, 1.0)
//        return Math.toDegrees(acos(cosVal))
//    }
//
//    // Simple exponential smoothing
//    fun smooth(prev: Double, current: Double, alpha: Double = 0.3): Double {
//        return prev * (1 - alpha) + current * alpha
//    }
//}
