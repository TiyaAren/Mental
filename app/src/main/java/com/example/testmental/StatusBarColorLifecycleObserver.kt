package com.example.testmental


import android.app.Activity
import android.os.Build
import android.view.View
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class StatusBarColorLifecycleObserver(
    private val activity: Activity,
    private val statusBarColor: Int,
    private val darkIcons: Boolean,
) : DefaultLifecycleObserver {

    override fun onResume(owner: LifecycleOwner) {
        activity.window.statusBarColor = statusBarColor
        setLightStatusBarIcons(activity, darkIcons)
    }
}

@Suppress("DEPRECATION")
fun setLightStatusBarIcons(activity: Activity, useDarkIcons: Boolean) {
    val window = activity.window
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        WindowCompat.getInsetsController(window, window.decorView)?.apply {
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
            isAppearanceLightStatusBars = useDarkIcons
        }
    } else {
        val decorView = window.decorView
        var flags = decorView.systemUiVisibility
        flags = if (useDarkIcons) {
            flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        }
        decorView.systemUiVisibility = flags
    }
}
