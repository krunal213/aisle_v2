package com.app.aisle.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.aisle.R
import com.app.aisle.databinding.FragmentOtpBinding
import com.app.aisle.di.component.DaggerAisleAppComponent
import com.app.aisle.di.module.application.ApplicationModule
import com.app.aisle.di.module.viewmodel.ViewModelModule
import com.app.aisle.login.viewmodel.LoginViewModel
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.app.aisle.Result

class OtpFragment : Fragment() {

    private val loginViewModel: LoginViewModel by lazy {
        DaggerAisleAppComponent
            .builder()
            .applicationModule(activity?.application?.let { ApplicationModule(it) })
            .viewModelModule(ViewModelModule)
            .build()
            .getLoginViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentOtpBinding = FragmentOtpBinding.inflate(inflater, container, false)

        arguments?.getString(getString(R.string.key_phone_number))?.let {
            fragmentOtpBinding.labelPhoneNumber.text = it
        }
        fragmentOtpBinding.buttonContinue.setOnClickListener {
            val otp = fragmentOtpBinding.labelOtp.text.toString().trim()
            arguments?.getString(getString(R.string.key_phone_number))?.let {
                loginViewModel.validateOtp(it, otp)
                    .observe(viewLifecycleOwner, Observer {
                        when (it) {
                            is Result.InProgress -> {
                                fragmentOtpBinding.progressCircular.visibility = View.VISIBLE
                            }
                            is Result.Success -> {
                                fragmentOtpBinding.progressCircular.visibility = View.GONE
                                findNavController().navigate(R.id.action_otpFragment_to_homeFragment)
                            }
                            is Result.Error -> {
                                fragmentOtpBinding.progressCircular.visibility = View.GONE
                            }
                        }
                    })
            }

        }



        return fragmentOtpBinding.root

    }


}