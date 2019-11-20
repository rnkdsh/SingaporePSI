package com.singaporepsi.di

import com.singaporepsi.MainApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBindingModule::class,
        ViewModelModule::class]
)
interface AppComponent : AndroidInjector<MainApplication> {
    @Component.Factory
    interface Factory : AndroidInjector.Factory<MainApplication>
}