package com.shinaz.newapp.di

import com.shinaz.newapp.ui.ar.ArActivity
import com.shinaz.newapp.ui.ar.ui.after.ARPutFragment
import com.shinaz.newapp.ui.auth.login.LoginFragment
import com.shinaz.newapp.ui.auth.signup.SignUpActivity
import com.shinaz.newapp.ui.base.MainActivity
import com.shinaz.newapp.ui.home.HomeScreen
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivityInjector(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeHomeScreenActivityInjector(): HomeScreen


    @ContributesAndroidInjector
    abstract fun contributeHLoginActivityInjector(): LoginFragment



    @ContributesAndroidInjector
    abstract fun contributeSignUpActivityInjector(): SignUpActivity

    @ContributesAndroidInjector
    abstract fun contributARctivityInjector(): ArActivity


}