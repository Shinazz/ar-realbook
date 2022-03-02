package com.shinaz.newapp.ui.base

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.shinaz.base.util.ApiState
import com.shinaz.base.viewmodels.MainViewModel
import com.shinaz.newapp.databinding.ActivityMainBinding
import com.shinaz.newapp.ui.ar.ArActivity
import com.shinaz.newapp.ui.ar.ui.after.equalsDelta
import com.shinaz.newapp.ui.auth.login.LoginFragment
import com.shinaz.newapp.ui.auth.signup.SignUpActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    private lateinit var binding: ActivityMainBinding
    @Inject lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerview()

        mainViewModel.getPost()
        binding.loginButton.setOnClickListener{
            startActivity(Intent(this@MainActivity, LoginFragment::class.java))

        }

        binding.signUpBtn.setOnClickListener{
            startActivity(Intent(this@MainActivity, SignUpActivity::class.java))

        }
        binding.arBtn.setOnClickListener{
            startActivity(Intent(this@MainActivity, ArActivity::class.java))

        }
        lifecycleScope.launchWhenStarted {
            mainViewModel._postStateFlow.collect { it->
                when(it){
                    is ApiState.Loading -> {
//                        binding.recyclerview.isVisible=false
//                        binding.progressBar.isVisible=true
                    }
                    is ApiState.Failure -> {
//                        binding.recyclerview.isVisible = false
//                        binding.progressBar.isVisible = false
                        Log.d("main", "onCreate: ${it.msg}")
                    }
                    is ApiState.Success -> {
//                        binding.recyclerview.isVisible = true
//                        binding.progressBar.isVisible = false
//                        postAdapter.setData(it.data)
//                        postAdapter.notifyDataSetChanged()
                    }
                    is ApiState.Empty -> {

                    }
                }
            }
        }
        checkForPins()

    }

    private fun initRecyclerview() {
//        postAdapter= PostAdapter(ArrayList())
//        binding.recyclerview.apply {
//            setHasFixedSize(true)
//            layoutManager=LinearLayoutManager(this@MainActivity)
//            adapter=postAdapter
//        }
    }



    private fun checkForPins() {
        Log.e("test 11.2598801 equals 11.2600059", (11.2598801).equalsDelta(11.2600059).toString())

        Log.e("test1", (Math.abs(11.2598801 / 11.2600059 - 1)).toString())
    }
}