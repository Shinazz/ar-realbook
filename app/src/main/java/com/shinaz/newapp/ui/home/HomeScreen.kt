package com.shinaz.newapp.ui.home

import com.shinaz.newapp.ui.base.BaseActivity
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.shinaz.newapp.R
import com.shinaz.newapp.databinding.ActivityHomeScreenBinding
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class HomeScreen: BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    lateinit var binding: ActivityHomeScreenBinding
//    lateinit var bindingAppBar: AppBarMainBinding
    lateinit var navController: NavController
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        }
        window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        setContentView(R.layout.activity_home_screen)
        binding= ActivityHomeScreenBinding.inflate(layoutInflater)
//        setSupportActionBar(binding.appBarLayout.toolbar)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val drawerVavView: NavigationView = findViewById(R.id.drawerNavView)
        val appBar = findViewById<View>(R.id.app_bar_layout)
        drawerLayout = findViewById(R.id.my_drawer_layout)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController= navHostFragment.navController
//        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer

        val toolbar = findViewById<Toolbar>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val actionbar= supportActionBar
//        actionbar?.setHomeAsUpIndicator(R.drawable.ic_home_black_24dp)
//        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.setDisplayShowTitleEnabled(false)

//
//        val actionBar = supportActionBar
        if (supportActionBar != null) {
            supportActionBar?.elevation = 0f
        }
        actionBarDrawerToggle =
            ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.nav_open,
                R.string.nav_close
            )

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle!!)


        actionBarDrawerToggle.isDrawerIndicatorEnabled = false
        actionBarDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_home_black_24dp)
//        toolbar.setNavigationIcon(R.drawable.ic_home_black_24dp);
//        actionBarDrawerToggle?.syncState()

        actionBarDrawerToggle?.toolbarNavigationClickListener = View.OnClickListener {
                if (drawerLayout?.isDrawerOpen(GravityCompat.END)) {
                    drawerLayout?.closeDrawer(GravityCompat.END)
                } else {
                        drawerLayout?.openDrawer(GravityCompat.END)
                }
            }
        toolbar.childCount.let { count ->
            for (i in 0 until count) {
                // make toggle drawable center-vertical, you can make each view alignment whatever you want
                if (toolbar?.getChildAt(i) is ImageButton) {
                    val lp =
                       toolbar?.getChildAt(i)?.layoutParams as Toolbar.LayoutParams
                    lp.layoutDirection = View.LAYOUT_DIRECTION_RTL
                    lp.gravity = Gravity.BOTTOM
                    toolbar?.getChildAt(i)?.layoutParams =
                        lp
                }
            }
        }
        // to make the Navigation drawer icon always appear on the action bar
        drawerVavView.setNavigationItemSelectedListener(this)
//        getmViewDataBinding()?.text?.text = "shinaazzz"
//        drawerVavView.setupWithNavController(navController)
    }
//    override val layoutId: Int = R.layout.activity_home_screen
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Log.e("shinaz", "shina")
        // Handle navigation view item clicks here.
        when (item.itemId) {

            R.id.navigation_dashboard -> {
                navController.navigate(item.itemId)
            }
            else -> {
                navController.navigate(item.itemId)
            }
        }
        drawerLayout.closeDrawer(GravityCompat.END)
        return true

    }


    // override the onOptionsItemSelected()
    // function to implement
    // the item click listener callback
    // to open and close the navigation
    // drawer when the icon is clicked
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.e("asdadasd","asdadssda")
        return if (actionBarDrawerToggle?.onOptionsItemSelected(item) == true) {

            true
        } else super.onOptionsItemSelected(item)
    }
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START) == true) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}