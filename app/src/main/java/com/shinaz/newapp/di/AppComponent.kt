package com.shinaz.newapp.di

import com.shinaz.newapp.ui.auth.login.LoginFragment
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidInjectionModule::class,
            FragmentsModule::class,
            ActivityModule::class,
            AppModule::class
        ]
)
interface AppComponent {
    fun inject(application: App)

}