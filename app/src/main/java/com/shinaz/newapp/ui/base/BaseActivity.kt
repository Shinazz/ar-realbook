package com.shinaz.newapp.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ComponentCallbacks2
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Build
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.ActivityResult
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.AndroidViewModel
import com.google.android.material.navigation.NavigationView
import com.shinaz.newapp.R
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection

abstract class BaseActivity: AppCompatActivity(), HasAndroidInjector  {
//    var isWindowBlocked = false
//
//    //Data bin variable.
//    private var mViewDataBinding: T? = null
//
//    private var sHeight = 0
//
//    /**
//     * @return layout resource id
//     */
//    @get:LayoutRes
//    abstract val layoutId: Int
//
//   override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val s = javaClass.canonicalName
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//        initBaseActivity()
//    }
//
//
//    /**
//     * Initialization
//     * 1. Initializing Data bin.
//     * 2. Setting up view model.
//     */
//    private fun initBaseActivity() {
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//        performDataBinding()
//        initMeasureParameters()
////        setNotifications()
//    }
//
////    private fun setNotifications() {
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
////            val channel = NotificationChannel(
////                "MyNotifications",
////                "MyNotifications",
////                NotificationManager.IMPORTANCE_DEFAULT
////            )
////            val manager: NotificationManager = getSystemService(NotificationManager::class.java)
////            manager.createNotificationChannel(channel)
////        }
////    }
//
//    override fun startActivity(intent: Intent?) {
//        super.startActivity(intent)
//        overridePendingTransition(R.anim.nav_default_enter_anim, R.anim.nav_default_pop_exit_anim)
//    }
//
//    override fun finish() {
//        super.finish()
//        overridePendingTransition(R.anim.nav_default_pop_exit_anim, R.anim.nav_default_pop_enter_anim)
//    }
//
//    /**
//     * This method is used to measure screen bottom height.
//     */
//    private fun initMeasureParameters() {
//        val rectgle = Rect()
//        val window: Window = getWindow()
//        window.decorView
//            .getWindowVisibleDisplayFrame(rectgle)
//        sHeight = rectgle.bottom
//    }
//
//    /**
//     * Check if the keyboard is open.
//     *
//     * @return true if the keyboard is open.
//     */
//    private fun keyOpen(): Boolean {
//        val rectgle = Rect()
//        val window: Window = getWindow()
//        window.decorView.getWindowVisibleDisplayFrame(rectgle)
//        val curheight = rectgle.bottom
//        return curheight != sHeight
//    }
//
//    /**
//     * This method is used to hide keyboard.
//     */
//    fun hideKeyboard() {
//        if (keyOpen()) {
//            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
//            imm?.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
//        }
//    }
//
//    /**
//     * This method is used to show keyboard.
//     */
//    fun showKeyboard() {
//        if (!keyOpen()) {
//            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
//            imm?.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
//        }
//    }
//
//    /**
//     * This method is used to inflate layout using [androidx.databinding.DataBindingUtil].
//     */
//    private fun performDataBinding() {
//        mViewDataBinding = DataBindingUtil.setContentView(this, layoutId)
//        //Data bin variable to be included.
//    }
//
//    /**
//     * This method is used to retrieve data bin object from
//     *
//     * @return <T>DataBinding object.
//    </T> */
//    fun getmViewDataBinding(): T? {
//        return mViewDataBinding
//    }
//
//    fun initTheme() {}
//
//    /**
//     * This method blocks the complete touch events.
//     */
//    fun disableTouch() {
//        getWindow().setFlags(
//            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
//        )
//        isWindowBlocked = true
//    }
//
//    /**
//     * This method re-enables touch.
//     */
//    fun enableTouch() {
//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
//        isWindowBlocked = false
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        clearMemory()
//    }
//
//    protected fun clearMemory() {
//        mViewDataBinding = null
//    }
//
//    /**
//     * Release memory when the UI becomes hidden or when system resources become low.
//     *
//     * @param level the memory-related event that was raised.
//     */
//    override fun onTrimMemory(level: Int) {
//
//        // Determine which lifecycle or system event was raised.
//        // refer to https://developer.android.com/topic/performance/memory for more insight
//        // This should e handled in the second phase of the app development.
//    }
//



     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
     }
  }

