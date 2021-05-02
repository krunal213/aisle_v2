package com.app.aisle.di.module.repository

import com.app.aisle.di.module.datasource.DataSourceModule
import com.app.aisle.repository.AisleLocalDataSource
import com.app.aisle.repository.AisleNetworkNetworkDataSource
import com.app.aisle.repository.AisleRepository
import dagger.Module
import dagger.Provides

@Module(includes = [DataSourceModule::class])
object RepositoryModule {

    @Provides
    fun getSugarCosmeticsNetworkRepository(networkDataSource : AisleNetworkNetworkDataSource, localDataSource : AisleLocalDataSource) : AisleRepository {
        return AisleRepository(networkDataSource,localDataSource)
    }

}