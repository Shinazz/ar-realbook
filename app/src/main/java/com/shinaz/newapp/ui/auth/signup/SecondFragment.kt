package com.shinaz.newapp.ui.auth.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.shinaz.base.util.ApiState
import com.shinaz.newapp.R
import com.shinaz.newapp.databinding.FragmentSecondBinding
import com.shinaz.newapp.model.auth.SignUpReq
import com.shinaz.newapp.ui.auth.login.LoginFragment
import com.shinaz.newapp.ui.base.BaseFragment
import com.shinaz.newapp.ui.home.HomeScreen
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : BaseFragment<FragmentSecondBinding, SIgnUpViewModel>() {

    @Inject
    lateinit var sIgnUpViewModel: SIgnUpViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        view.findViewById<Button>(R.id.createAcctBtn).setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
//        }


        viewDataBinding?.createAcctBtn?.setOnClickListener {
            val registerRequest = SignUpReq(
                token = (requireActivity() as SignUpActivity).token,
                password = viewDataBinding?.reEnterPassword?.text?.toString()
            )
            sIgnUpViewModel.register(registerRequest)
        }


        lifecycleScope.launchWhenStarted {
            sIgnUpViewModel._registerEmailStateFlow.collect { it ->
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
                        startActivity(Intent(requireActivity(), LoginFragment::class.java))
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
            get() = R.layout.fragment_second
            override val viewModel: SIgnUpViewModel
            get() = sIgnUpViewModel
        }