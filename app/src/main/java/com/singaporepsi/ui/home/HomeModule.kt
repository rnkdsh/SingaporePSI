package com.singaporepsi.ui.home

import androidx.lifecycle.ViewModel
import com.singaporepsi.di.scope.FragmentScoped
import com.singaporepsi.di.scope.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class HomeModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeHomeFragment(): HomeFragment

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel
}