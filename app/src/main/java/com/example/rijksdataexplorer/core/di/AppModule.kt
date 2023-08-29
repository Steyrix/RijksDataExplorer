package com.example.rijksdataexplorer.core.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
object AppModule {

    @Provides
    fun provideContext(
        application: Application
    ): Context {
        return application.applicationContext
    }
}