package com.app.aisle.di.module.application

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(val application : Application) {

    @Provides
    fun provideApplication() : Application {
        return application
    }

}