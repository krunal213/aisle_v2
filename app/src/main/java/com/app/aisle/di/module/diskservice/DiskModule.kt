package com.app.aisle.di.module.diskservice

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.app.aisle.di.module.application.ApplicationModule
import dagger.Module
import dagger.Provides

@Module(includes = [ApplicationModule::class])
object DiskModule {

    @Provides
    fun provideSharePreferenceService(application: Application): SharedPreferences {
        return application.getSharedPreferences(application.packageName,Context.MODE_PRIVATE)
    }
}