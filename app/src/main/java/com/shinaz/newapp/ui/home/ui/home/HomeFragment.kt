package com.shinaz.newapp.ui.home.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shinaz.newapp.R


class HomeFragment : Fragment(R.layout.fragment_home) {
//    private lateinit var binding: FragmentHomeBinding

//    private val homeViewModel: HomeViewModel by hiltNavGraphViewModels(R.id.mobile_navigation)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return super.onCreateView(inflater, container, savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding = FragmentHomeBinding.bind(view)

//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            binding.textHome.text = it
//        })
    }

}