package com.faraji.mvibase.example.app

import android.app.Application
import androidx.viewbinding.BuildConfig
import com.faraji.mvibase.MviBaseConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MviApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())

        MviBaseConfig.debugLog = true
    }
}