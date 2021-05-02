package com.app.aisle.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.app.aisle.R
import com.app.aisle.Result
import com.app.aisle.databinding.FragmentPhoneNumberBinding
import com.app.aisle.di.component.DaggerAisleAppComponent
import com.app.aisle.di.module.application.ApplicationModule
import com.app.aisle.di.module.viewmodel.ViewModelModule
import com.app.aisle.extension.indiaCodePrefix
import com.app.aisle.login.viewmodel.LoginViewModel

class PhoneNumberFragment : Fragment() {

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
        val fragmentPhoneNumberBinding =
            FragmentPhoneNumberBinding.inflate(inflater, container, false)

        fragmentPhoneNumberBinding.buttonContinue.setOnClickListener {
            val phoneNumber = fragmentPhoneNumberBinding.editextPhoneNumber.text.toString().indiaCodePrefix().trim()
            loginViewModel.validatePhoneNumber(phoneNumber)
                .observe(viewLifecycleOwner, Observer {
                    when (it) {
                        is Result.InProgress -> {
                            fragmentPhoneNumberBinding.progressCircular.visibility = View.VISIBLE
                        }
                        is Result.Success -> {
                            fragmentPhoneNumberBinding.progressCircular.visibility = View.GONE
                            findNavController().navigate(
                                R.id.action_phoneNumberFragment_to_otpFragment,
                                bundleOf(getString(R.string.key_phone_number) to phoneNumber)
                            )
                        }
                        is Result.Error -> {
                            fragmentPhoneNumberBinding.progressCircular.visibility = View.GONE
                        }
                    }
                })
        }

        return fragmentPhoneNumberBinding.root
    }


}