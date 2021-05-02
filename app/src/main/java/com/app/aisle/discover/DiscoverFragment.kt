package com.app.aisle.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.app.aisle.Result
import com.app.aisle.databinding.FragmentDiscoverBinding
import com.app.aisle.di.component.DaggerAisleAppComponent
import com.app.aisle.di.module.application.ApplicationModule
import com.app.aisle.di.module.viewmodel.ViewModelModule
import com.app.aisle.entity.home.HomeDisplayContent
import com.app.aisle.discover.viewmodel.DiscoverViewModel

class DiscoverFragment : Fragment() {

    private val discoverViewModel: DiscoverViewModel by lazy {
        DaggerAisleAppComponent
            .builder()
            .applicationModule(activity?.application?.let { ApplicationModule(it) })
            .viewModelModule(ViewModelModule)
            .build()
            .getHomeViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentDiscoverBinding = FragmentDiscoverBinding.inflate(inflater, container, false)

        discoverViewModel.getAuthorization().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Result.Success -> {
                    discoverViewModel.getProfileList(it.t)
                        .observe(viewLifecycleOwner, Observer<Result<HomeDisplayContent>> {
                            when (it) {
                                is Result.InProgress->{
                                    fragmentDiscoverBinding.shimmerLayout.visibility = View.VISIBLE
                                    fragmentDiscoverBinding.container.visibility = View.GONE
                                    fragmentDiscoverBinding.shimmerLayout.startShimmer()
                                }
                                is Result.Success -> {
                                    fragmentDiscoverBinding.shimmerLayout.stopShimmer()
                                    fragmentDiscoverBinding.shimmerLayout.visibility = View.GONE
                                    fragmentDiscoverBinding.container.visibility = View.VISIBLE
                                    fragmentDiscoverBinding.homeDisplayContent = it.t
                                    fragmentDiscoverBinding.recyclerviewLikesYou.apply {
                                        adapter = DiscoverListAdapter().apply {
                                            addItemDecoration(DiscoverDecoration())
                                            submitList(it.t.likes)
                                        }
                                    }
                                }
                            }
                        })
                }
                else -> {

                }
            }
        })

        return fragmentDiscoverBinding.root
    }


}