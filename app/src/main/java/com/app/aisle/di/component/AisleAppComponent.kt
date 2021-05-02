package com.app.aisle.di.component

import com.app.aisle.di.module.viewmodel.ViewModelModule
import com.app.aisle.discover.viewmodel.DiscoverViewModel
import com.app.aisle.login.viewmodel.LoginViewModel
import dagger.Component

@Component(modules = [ViewModelModule::class])
interface AisleAppComponent {

    fun getLoginViewModel() : LoginViewModel

    fun getHomeViewModel() : DiscoverViewModel

}