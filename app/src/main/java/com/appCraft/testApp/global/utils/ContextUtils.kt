package com.appCraft.testApp.global.utils

import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator

//fun Context.vibrate(duration: Long) {
//    val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator?
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//        vibrator?.vibrate(VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE))
//    } else {
//        @Suppress("DEPRECATION")
//        vibrator?.vibrate(duration)
//    }
//}
