package com.example.wsr.Api.model

import android.app.Application
import com.example.wsr.MainMenu.MenuFragment
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(listOf(MainViewModel.modul))
        }
    }
}
