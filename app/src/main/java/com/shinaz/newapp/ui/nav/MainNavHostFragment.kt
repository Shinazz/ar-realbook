package com.shinaz.newapp.ui.nav

import android.content.Context
import androidx.navigation.fragment.NavHostFragment
import com.shinaz.newapp.ui.home.HomeFragmentFactory
import javax.inject.Inject

class MainNavHostFragment: NavHostFragment() {

    @Inject
    lateinit var fragmentFactory: HomeFragmentFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        childFragmentManager.fragmentFactory = fragmentFactory
    }
}