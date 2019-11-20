package com.singaporepsi.di

import com.singaporepsi.di.scope.ActivityScoped
import com.singaporepsi.ui.home.HomeActivity
import com.singaporepsi.ui.home.HomeModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [HomeModule::class])
    internal abstract fun homeActivity(): HomeActivity
}