# 🪞 AI Fitness Mirror – Android (Kotlin)

![Kotlin](https://img.shields.io/badge/Kotlin-1.9-blue?logo=kotlin)
![Android](https://img.shields.io/badge/Android-15-green?logo=android)
![OpenCV](https://img.shields.io/badge/OpenCV-4.9-orange?logo=opencv)
![MediaPipe](https://img.shields.io/badge/MediaPipe-Pose-yellow?logo=google)
![License: MIT](https://img.shields.io/badge/License-MIT-lightgrey.svg)

> **Track your posture, count reps, and get instant feedback — your personal AI-powered fitness trainer, right through your phone camera.**

---

## 🎯 Overview  

**AI Fitness Mirror** is an Android app built using **Kotlin**, designed to help users improve their workout form and consistency through **real-time AI posture tracking**.  
The app detects body landmarks from the live camera feed using **MediaPipe Pose**, calculates **joint angles**, and provides **audio + visual feedback** on posture correctness — acting as your intelligent mirror.

---

## 🚀 Features  

✅ Real-time **Pose Detection** (Google MediaPipe)  
✅ **Posture Correction Feedback** using angle analysis  
✅ **Automatic Rep Counting** for squats, push-ups, lunges  
✅ **Joint Angle Calculation** with OpenCV  
✅ **Workout Data Storage** using Room Database  
✅ **Dashboard with Progress Charts** (MPAndroidChart)  
✅ **Text-to-Speech Voice Feedback**  
✅ **Modern MVVM Architecture** with Coroutines + LiveData  
✅ Works on **Android 10 – Android 15**  

---

## 🧠 Tech Stack  

| Layer | Tools / Libraries |
|--------|-------------------|
| **Language** | Kotlin |
| **Camera** | CameraX API |
| **Pose Detection** | Google MediaPipe Pose |
| **Graphics** | OpenCV / Canvas Overlay |
| **Architecture** | MVVM + Coroutines |
| **Database** | Room / DataStore |
| **UI** | Jetpack Compose / XML |
| **Charts** | MPAndroidChart |
| **Feedback** | TextToSpeech (TTS) |

---

## 🧩 Project Structure  

```

ai-fitness-mirror/
│
├── data/
│   ├── database/
│   │   └── WorkoutSessionDao.kt
│   ├── model/
│   │   └── PoseData.kt, WorkoutSession.kt
│   └── repository/
│       └── WorkoutRepository.kt
│
├── domain/
│   ├── usecase/
│   │   └── CalculateAngleUseCase.kt
│   └── utils/
│       └── PoseMathUtils.kt
│
├── ui/
│   ├── camera/
│   │   └── CameraView.kt
│   ├── dashboard/
│   │   └── DashboardActivity.kt
│   └── components/
│       └── PoseOverlayView.kt
│
├── viewmodel/
│   └── PoseViewModel.kt
│
└── main/
└── MainActivity.kt

````

---

## ⚙️ Implementation Details  

### 1️⃣ Pose Detection & Camera Feed  

```kotlin
val poseLandmarker = PoseLandmarker.createFromFile(context, "pose_landmarker_full.task")

cameraProvider.bindToLifecycle(
    this,
    cameraSelector,
    ImageAnalysis.Builder().build().also {
        it.setAnalyzer(executor) { imageProxy ->
            val results = poseLandmarker.detect(imageProxy.image)
            viewModel.updatePose(results)
            imageProxy.close()
        }
    }
)
````

---

### 2️⃣ Joint Angle Calculation (e.g., Squats)

```kotlin
fun calculateAngle(a: PointF, b: PointF, c: PointF): Double {
    val ab = Pair(a.x - b.x, a.y - b.y)
    val cb = Pair(c.x - b.x, c.y - b.y)
    val dot = ab.first * cb.first + ab.second * cb.second
    val magnitude = sqrt((ab.first.pow(2) + ab.second.pow(2)) * (cb.first.pow(2) + cb.second.pow(2)))
    return Math.toDegrees(acos(dot / magnitude))
}
```

---

### 3️⃣ Rep Counting Logic

```kotlin
var isDown = false
var repCount = 0

if (kneeAngle > 160) isDown = false
if (kneeAngle < 90 && !isDown) {
    repCount++
    isDown = true
    textToSpeech.speak("Rep $repCount", TextToSpeech.QUEUE_FLUSH, null, null)
}
```

---

### 4️⃣ Data Storage with Room

```kotlin
@Entity(tableName = "workout_sessions")
data class WorkoutSession(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: Long,
    val exerciseType: String,
    val reps: Int,
    val avgAngle: Double
)
```

---

### 5️⃣ Dashboard Visualization

```kotlin
val entries = sessions.map { Entry(it.date.toFloat(), it.reps.toFloat()) }
val dataSet = LineDataSet(entries, "Reps Over Time")
chart.data = LineData(dataSet)
chart.invalidate()
```

---

## 📊 UI Flow

| Screen                | Description                                  |
| --------------------- | -------------------------------------------- |
| **CameraView**        | Displays real-time pose overlay and feedback |
| **DashboardActivity** | Displays workout history and stats           |
| **SettingsActivity**  | Exercise selection & voice toggle            |

---

## 🖼️ Screenshots (Coming Soon)

| Camera View | Dashboard | Feedback |
| ----------- | --------- | -------- |
| 📸          | 📊        | 🎙️      |

---

## 🛠️ Installation

```bash
git clone https://github.com/kartik-workspace/AI-Fitness-Mirror.git
cd AI-Fitness-Mirror
```

* Open in **Android Studio (Arctic Fox or newer)**
* Add `pose_landmarker_full.task` under `/assets/`
* Run the app on a physical device (Camera permission required)

---

## 🧮 Future Enhancements

* 🧘 Yoga Pose Classification
* 🎙️ Voice Commands (e.g., “Start Push-ups”)
* 💪 Multi-person tracking support
* 📈 Cloud sync for fitness data
* 🕹️ Integration with WearOS

---

## 👨‍💻 Author

**Kartik Waghmare**
📍 Pune, India
📱 Android Developer | ML & OpenCV Enthusiast

🔗 **Connect with me:**

* 💼 [LinkedIn](https://www.linkedin.com/in/kartik-waghmare)
* 📸 [Instagram](https://www.instagram.com/mr_illusionist_kartik)
* 💻 [GitHub](https://github.com/kartik-workspace)

---

## 🛡️ License

This project is licensed under the [MIT License](https://github.com/kartik-workspace/AI-Fitness-Mirror-App/blob/master/LICENSE).
© 2025 Kartik Waghmare. All rights reserved.

---

### ⭐ If you like this project, give it a star on GitHub!
