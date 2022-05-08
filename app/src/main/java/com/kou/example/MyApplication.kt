package com.kou.example

import android.app.Application
import com.kou.example.modules.networkModule
import com.kou.example.modules.repositoryModule
import com.kou.example.modules.viewModelModule
import org.koin.core.context.startKoin

/**
 * Application classed used on the project
 */
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(repositoryModule, networkModule, viewModelModule)
        }
    }
}