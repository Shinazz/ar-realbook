package com.shinaz.newapp.ui.auth.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.shinaz.base.util.ApiState
import com.shinaz.newapp.R
import com.shinaz.newapp.databinding.FragmentFirstBinding
import com.shinaz.newapp.model.auth.SignUpReq
import com.shinaz.newapp.repository.Locale2
import com.shinaz.newapp.ui.base.BaseFragment
import com.shinaz.newapp.ui.home.HomeScreen
import com.shinaz.newapp.ui.home.ui.dashboard.DashboardViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.observeOn
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : BaseFragment<FragmentFirstBinding, SIgnUpViewModel>() {
    @Inject
    lateinit var singUpViewModel: SIgnUpViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding?.emailVerifyBtn?.setOnClickListener {
            val verifyReq = SignUpReq(
                viewDataBinding?.emailId?.text?.toString()?.trim(),
                userName = viewDataBinding?.userName?.text?.toString()?.trim()
            )
            singUpViewModel.verifyEmail(verifyReq)
        }


        lifecycleScope.launchWhenStarted {
            singUpViewModel._verifyEmailStateFlow.collect { it ->
                when (it) {
                    is ApiState.Loading -> {
//                        binding.recyclerview.isVisible=false
//                        viewDataBinding?.loading.isVisible=true
                    }
                    is ApiState.Failure -> {
//                        binding.recyclerview.isVisible = false
//                        binding.loading.isVisible = false
                        Log.d("main", "onCreate: ${it.msg}")
                    }
                    is ApiState.Success -> {
//                        Locale2.accessToken = it.data.token
//                        Log.e("logged in", it.data.token.toString())
//                        startActivity(Intent(this@LoginFragment, HomeScreen::class.java))

//                        binding.recyclerview.isVisible = true
//                        binding.loading.isVisible = false
//                        postAdapter.setData(it.data)
//                        postAdapter.notifyDataSetChanged()
                    }
                    is ApiState.Empty -> {

                    }
                }

            }
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_first
    override val viewModel: SIgnUpViewModel
        get() = singUpViewModel
}