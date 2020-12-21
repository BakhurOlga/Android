package com.olg.bakhur.common

import android.app.Application
import com.olg.bakhur.common.di.component.AppComponent
import com.olg.bakhur.common.di.component.DaggerAppComponent

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