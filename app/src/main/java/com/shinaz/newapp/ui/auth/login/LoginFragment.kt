package com.shinaz.newapp.ui.auth.login

import android.content.Intent
import androidx.annotation.StringRes
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.shinaz.base.util.ApiState
import kotlinx.coroutines.flow.collect
import com.shinaz.newapp.databinding.FragmentLoginBinding
import com.shinaz.newapp.repository.Locale2
import com.shinaz.newapp.ui.home.HomeScreen
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class LoginFragment : AppCompatActivity() {
    private lateinit var binding: FragmentLoginBinding

    @Inject lateinit var loginViewModel: LoginViewModel
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        binding = FragmentLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val usernameEditText = binding.username
        val passwordEditText = binding.password
        val loginButton = binding.login
        val loadingProgressBar = binding.loading


        lifecycleScope.launchWhenStarted {
            loginViewModel._loginStateFlow.collect { it ->
                when (it) {
                    is ApiState.Loading -> {
//                        binding.recyclerview.isVisible=false
                        binding.loading.isVisible=true
                    }
                    is ApiState.Failure -> {
//                        binding.recyclerview.isVisible = false
                        binding.loading.isVisible = false
                        Log.d("main", "onCreate: ${it.msg}")
                    }
                    is ApiState.Success -> {
                        Locale2.accessToken = it.data.token
                        Log.e("logged in", it.data.token.toString())
                        startActivity(Intent(this@LoginFragment, HomeScreen::class.java))

//                        binding.recyclerview.isVisible = true
                        binding.loading.isVisible = false
//                        postAdapter.setData(it.data)
//                        postAdapter.notifyDataSetChanged()
                    }
                    is ApiState.Empty -> {

                    }
                }
            }
        }

//        loginViewModel.loginFormState.observe(viewLifecycleOwner,
//            Observer { loginFormState ->
//                if (loginFormState == null) {
//                    return@Observer
//                }
//                loginButton.isEnabled = loginFormState.isDataValid
//                loginFormState.usernameError?.let {
//                    usernameEditText.error = getString(it)
//                }
//                loginFormState.passwordError?.let {
//                    passwordEditText.error = getString(it)
//                }
//            })

//        loginViewModel.loginResult.observe(viewLifecycleOwner,
//            Observer { loginResult ->
//                loginResult ?: return@Observer
//                loadingProgressBar.visibility = View.GONE
//                loginResult.error?.let {
//                    showLoginFailed(it)
//                }
//                loginResult.success?.let {
//                    updateUiWithUser(it)
//                }
//            })

//            val afterTextChangedListener = object : TextWatcher {
//                override fun beforeTextChanged(
//                    s: CharSequence,
//                    start: Int,
//                    count: Int,
//                    after: Int
//                ) {
//                    // ignore
//                }
//
//                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//                    // ignore
//                }
//
//                override fun afterTextChanged(s: Editable) {
//                    loginViewModel.loginDataChanged(
//                        usernameEditText.text.toString(),
//                        passwordEditText.text.toString()
//                    )
//                }
//            }
//            usernameEditText.addTextChangedListener(afterTextChangedListener)
//            passwordEditText.addTextChangedListener(afterTextChangedListener)
//            passwordEditText.setOnEditorActionListener { _, actionId, _ ->
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    loginViewModel.login(
//                        usernameEditText.text.toString(),
//                        passwordEditText.text.toString()
//                    )
//                }
//                false
//            }
            Log.e("shinaz", "binding.login.toString()")
            Log.e("shinaz", binding.login.toString())
            Log.e("shinaz", "binding.login.toString()")

            binding.login.setOnClickListener{
                Log.e("shinaz", "shinaz")
                loginViewModel.login(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
            loginButton.setOnClickListener {
                loadingProgressBar.visibility = View.VISIBLE
                Log.e("shinaz", "shinaz")
                loginViewModel.login(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
        }

//    private fun updateUiWithUser(model: LoggedInUserView) {
//        val welcome = getString(R.string.welcome) + model.displayName
//        // TODO : initiate successful logged in experience
//        val appContext = context?.applicationContext ?: return
//        Toast.makeText(appContext, welcome, Toast.LENGTH_LONG).show()
//    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        val appContext = this?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }
}