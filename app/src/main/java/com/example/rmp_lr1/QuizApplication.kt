package com.example.rmp_lr1

import android.app.Application
import android.content.Context
import com.example.rmp_lr1.di.AppComponent
import com.example.rmp_lr1.di.DaggerAppComponent

class QuizApplication : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is QuizApplication -> appComponent
        else -> applicationContext.appComponent
    }