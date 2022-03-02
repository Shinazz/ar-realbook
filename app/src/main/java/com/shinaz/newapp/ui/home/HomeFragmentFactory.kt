package com.shinaz.newapp.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.shinaz.newapp.ui.home.ui.dashboard.DashboardFragment
import com.shinaz.newapp.ui.home.ui.home.HomeFragment
import com.shinaz.newapp.ui.home.ui.notifications.NotificationsFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi

class HomeFragmentFactory: FragmentFactory() {
    @ExperimentalCoroutinesApi
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            DashboardFragment::class.java.name ->{
                DashboardFragment()
            }
            HomeFragment::class.java.name ->{
                HomeFragment()
            }
            NotificationsFragment::class.java.name ->{
                NotificationsFragment()
            }
            else ->
                super.instantiate(classLoader, className)
        }
    }
}