package com.example.rijksdataexplorer.core

import android.app.Application
import com.example.rijksdataexplorer.core.di.AppComponent
import com.example.rijksdataexplorer.core.di.DaggerAppComponent

class RijksDataExplorerApplication : Application() {

    val appComponent: AppComponent = DaggerAppComponent
        .builder()
        .application(this)
        .build()
}