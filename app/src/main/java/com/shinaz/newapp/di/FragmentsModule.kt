package com.shinaz.newapp.di

import com.shinaz.newapp.ui.ar.ui.ArFragment
import com.shinaz.newapp.ui.ar.ui.after.ARPutFragment
import com.shinaz.newapp.ui.auth.login.LoginFragment
import com.shinaz.newapp.ui.auth.signup.FirstFragment
import com.shinaz.newapp.ui.auth.signup.SecondFragment
import com.shinaz.newapp.ui.home.ui.dashboard.DashboardFragment
import com.shinaz.newapp.ui.home.ui.home.HomeFragment
import com.shinaz.newapp.ui.home.ui.notifications.NotificationsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun contributenotiFragmentInjector(): NotificationsFragment

    @ContributesAndroidInjector
    abstract fun contributedashFragmentInjector(): DashboardFragment

    @ContributesAndroidInjector
    abstract fun contributehomeFragmentInjector(): HomeFragment

    @ContributesAndroidInjector
    abstract fun first(): FirstFragment

    @ContributesAndroidInjector
    abstract fun second(): SecondFragment

    @ContributesAndroidInjector
    abstract fun arfragment(): ArFragment

    @ContributesAndroidInjector
    abstract fun arputfragment(): ARPutFragment
}