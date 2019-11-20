package com.singaporepsi.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import com.singaporepsi.R
import com.singaporepsi.ui.base.BaseActivity

class HomeActivity : BaseActivity(R.layout.activity_home) {

    private val viewModel: HomeViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.flag_with_space)
    }
}
