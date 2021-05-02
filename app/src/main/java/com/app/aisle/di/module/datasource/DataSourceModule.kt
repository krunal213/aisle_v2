package com.app.aisle.di.module.datasource

import android.app.Application
import android.content.SharedPreferences
import com.app.aisle.di.module.application.ApplicationModule
import com.app.aisle.di.module.diskservice.DiskModule
import com.app.aisle.di.module.networkservice.NetworkModule
import com.app.aisle.networkcontract.AisleNetworkService
import com.app.aisle.repository.AisleLocalDataSource
import com.app.aisle.repository.AisleNetworkNetworkDataSource
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class,DiskModule::class,ApplicationModule::class])
object DataSourceModule {

    @Provides
    fun provideAisleNetworkDataSource(aisleNetworkService: AisleNetworkService) : AisleNetworkNetworkDataSource {
        return AisleNetworkNetworkDataSource(aisleNetworkService)
    }

    @Provides
    fun provideAisleLocalDataSource(sharedPreferences: SharedPreferences,application : Application) : AisleLocalDataSource {
        return AisleLocalDataSource(sharedPreferences,application)
    }

}