package com.app.aisle.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.app.aisle.R
import com.app.aisle.Result
import com.app.aisle.di.component.DaggerAisleAppComponent
import com.app.aisle.di.module.application.ApplicationModule
import com.app.aisle.di.module.viewmodel.ViewModelModule
import com.app.aisle.login.viewmodel.LoginViewModel

class SplashFragment : Fragment() {

    private val loginViewModel: LoginViewModel by lazy {
        DaggerAisleAppComponent
            .builder()
            .applicationModule(activity?.application?.let { ApplicationModule(it) })
            .viewModelModule(ViewModelModule)
            .build()
            .getLoginViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel.isUserLogin().observe(viewLifecycleOwner, Observer {
            findNavController().navigate(
                when (it) {
                    is Result.Success -> {
                        R.id.action_splashFragment_to_homeFragment
                    }
                    else -> {
                        R.id.action_splashFragment_to_phoneNumberFragment

                    }
                }
            )
        })

    }


}