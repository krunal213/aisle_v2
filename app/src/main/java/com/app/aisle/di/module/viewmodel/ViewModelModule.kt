package com.app.aisle.di.module.viewmodel

import android.app.Application
import com.app.aisle.di.module.application.ApplicationModule
import com.app.aisle.di.module.repository.RepositoryModule
import com.app.aisle.discover.viewmodel.DiscoverViewModel
import com.app.aisle.login.viewmodel.LoginViewModel
import com.app.aisle.repository.AisleRepository
import dagger.Module
import dagger.Provides

@Module(includes = [RepositoryModule::class,ApplicationModule::class])
object ViewModelModule {

    @Provides
    fun provideLoginViewModel(application: Application,aisleRepository : AisleRepository) : LoginViewModel{
        return LoginViewModel(application,aisleRepository)
    }

    @Provides
    fun provideHomeViewModel(application: Application,aisleRepository : AisleRepository) : DiscoverViewModel {
        return DiscoverViewModel(
            application,
            aisleRepository
        )
    }

}