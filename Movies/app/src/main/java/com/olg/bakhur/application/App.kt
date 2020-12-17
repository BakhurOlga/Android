package com.olg.bakhur.application

import android.app.Application
import com.olg.bakhur.application.di.component.AppComponent
import com.olg.bakhur.application.di.component.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.factory().create(this)
    }

    companion object {
        lateinit var component: AppComponent
            private set
    }
}