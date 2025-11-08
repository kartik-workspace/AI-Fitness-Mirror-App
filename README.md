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

