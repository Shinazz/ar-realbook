package com.shinaz.newapp.ui.home.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.shinaz.base.util.ApiState
import com.shinaz.newapp.R
import com.shinaz.newapp.databinding.FragmentDashboardBinding
import com.shinaz.newapp.repository.Locale2
import com.shinaz.newapp.ui.base.BaseFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>() {

    @Inject
    lateinit var dashboardViewModel: DashboardViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            viewDataBinding?.textDashboard?.text = it
        })


        dashboardViewModel.getProfile()
        lifecycleScope.launchWhenStarted {
            dashboardViewModel._profileStateFlow.collect { it ->
                when (it) {
                    is ApiState.Loading -> {
//                        binding.recyclerview.isVisible=false
//                        binding.loading.isVisible=true
                    }
                    is ApiState.Failure -> {
//                        binding.recyclerview.isVisible = false
//                        binding.loading.isVisible = false
                        Log.d("main", "onCreate: ${it.msg}")
                    }
                    is ApiState.Success -> {
//                        Locale2.accessToken = it.data.token
//                        Log.e("logged in", it.data.token.toString())

                        viewDataBinding?.textDashboard?.text = "Welcome " + it.data.name + "(" + it.data.email +") you have " + it.data.coinsEarned + " coins"
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
        get() = R.layout.fragment_dashboard
    override val viewModel: DashboardViewModel
        get() = dashboardViewModel
}