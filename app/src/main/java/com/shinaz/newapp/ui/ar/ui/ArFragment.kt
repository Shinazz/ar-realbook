package com.shinaz.newapp.ui.ar.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.shinaz.base.util.ApiState
import com.shinaz.newapp.R
import com.shinaz.newapp.databinding.ArFragmentBinding
import com.shinaz.newapp.model.ar.UserData
import com.shinaz.newapp.ui.ar.ArActivity
import com.shinaz.newapp.ui.ar.ui.after.equalsDelta
import com.shinaz.newapp.ui.base.BaseFragment
import kotlinx.coroutines.flow.collect
import java.io.ByteArrayOutputStream
import javax.inject.Inject


class ArFragment : BaseFragment<ArFragmentBinding, ArViewModel>() {

    companion object {
        fun newInstance() = ArFragment()
    }

    override val viewModel: ArViewModel
        get() = arViewModel


    @Inject
    lateinit var arViewModel: ArViewModel

    override val layoutId: Int
        get() = R.layout.ar_fragment


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (ContextCompat.checkSelfPermission(requireActivity(), android.Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CAMERA), 101);
        }
        viewDataBinding?.takePic?.setOnClickListener {
            startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE), 100)
            viewModel.getProfile()


        }
        viewDataBinding?.putPic?.setOnClickListener {
            (requireActivity() as ArActivity).navController.navigate(R.id.action_ArFragment_to_ArPutFragment)
        }
        viewModel.getProfile()

        lifecycleScope.launchWhenStarted {
            viewModel._userFlow.collect { it ->
                when (it) {
                    is ApiState.Loading -> {
                    }
                    is ApiState.Failure -> {
                        Log.d("main", "onCreate: ${it?.msg}")
                    }
                    is ApiState.Success -> {
                        if (it?.data != null && it?.data?.size!! > 0) {
                            if (it?.data?.get(0)?.image !== null) {
                                val bitmap = BitmapFactory.decodeByteArray(it.data[0].image!!, 0, it.data[0].image!!.size)
                                (requireActivity() as ArActivity).bitmapfromDB = bitmap
                            }
                            if (it?.data?.get(0)?.location !== null) {
                                Log.e("data", it.data[0].location?.latitude.toString())
                                (requireActivity() as ArActivity).locFromDB = it.data[0].location!!
                            }
                        }

                    }
                    is ApiState.Empty -> {

                    }
                }
            }
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        Log.e("request shinaz", requestCode as String)
        if (requestCode == 100) {
            val photo = data?.extras?.get("data") as Bitmap?
            viewDataBinding?.capturedImage?.setImageBitmap(photo)
            if (photo != null) {
                (requireActivity() as ArActivity).bitmap = photo
                val stream = ByteArrayOutputStream()
                photo.compress(Bitmap.CompressFormat.PNG, 90, stream)
                val image = stream.toByteArray()
                viewModel.insert(UserData("2", "shinaz azeez", "realbook pinned here!", "i pinned this in realbook",
                        (requireActivity() as ArActivity).itemLocation, image
                ))

            }
        }
    }

}