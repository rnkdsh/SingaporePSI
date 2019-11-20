package com.singaporepsi.ui.home

import androidx.activity.viewModels
import com.singaporepsi.R
import com.singaporepsi.ui.base.BaseActivity

class HomeActivity : BaseActivity(R.layout.activity_home) {

    private val viewModel: HomeViewModel by viewModels { viewModelFactory }
}
