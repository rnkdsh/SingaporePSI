package com.singaporepsi.ui.home

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import com.singaporepsi.R
import com.singaporepsi.ui.base.BaseFragment

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by activityViewModels { viewModelFactory }

    override fun getViewModel() = homeViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}