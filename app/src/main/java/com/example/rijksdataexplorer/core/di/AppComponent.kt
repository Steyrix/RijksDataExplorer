package com.example.rijksdataexplorer.core.di

import android.app.Application
import com.example.rijksdataexplorer.core.network.AppNetworkingModule
import com.example.rijksdataexplorer.main.MainActivity
import com.example.rijksdataexplorer.core.data.repository.RijksRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, AppNetworkingModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(app: Application): Builder
    }

    fun inject(activity: MainActivity)

    fun rijksRepository(): RijksRepository
}