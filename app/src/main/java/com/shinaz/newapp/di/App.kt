package com.shinaz.newapp.di

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.shinaz.newapp.room.UserRepository
import com.shinaz.newapp.room.UserRoomDB
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : Application(), HasAndroidInjector {
    @Inject lateinit var androidInjector : DispatchingAndroidInjector<Any>
//    val database by lazy { UserRoomDB.getDatabase(this) }
//    val repository by lazy { UserRepository(database.userDao()) }
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
    override fun onCreate() {
        DaggerAppComponent.builder().appModule(AppModule(this)).build().inject(this)
        super.onCreate()
        //Your code
    }

}