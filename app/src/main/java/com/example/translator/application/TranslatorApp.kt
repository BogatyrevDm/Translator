package com.example.translator.application

import android.app.Application
import com.example.translator.di.AppComponent
import com.example.translator.di.DaggerAppComponent

class TranslatorApp:Application() {
    companion object{
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().build()
    }
}