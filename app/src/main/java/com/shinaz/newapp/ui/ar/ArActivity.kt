package com.shinaz.newapp.ui.ar

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.shinaz.newapp.R
import com.shinaz.newapp.databinding.ActivityHomeScreenBinding
import com.shinaz.newapp.databinding.ArActivityBinding
import com.shinaz.newapp.model.ar.LocationData
import com.shinaz.newapp.ui.ar.ui.ArFragment
import com.shinaz.newapp.ui.ar.ui.ArViewModel
import com.shinaz.newapp.ui.base.BaseActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class ArActivity : BaseActivity() {
    private lateinit var binding: ArActivityBinding
    lateinit var bitmap: Bitmap
    lateinit var navController: NavController
    lateinit var mFusedLocationProviderClient: FusedLocationProviderClient
    val locationRequestId = 100

    var itemLocation: LocationData?  = null

    lateinit var locFromDB: LocationData

    lateinit var bitmapfromDB: Bitmap

    @Inject
    lateinit var arViewModel: ArViewModel

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = ArActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, ArFragment.newInstance())
//                .commitNow()
//        }
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.ar_container) as NavHostFragment
        navController= navHostFragment.navController

        val navInflater = navController.navInflater
        val graph = navInflater.inflate(R.navigation.nav_ar_graph)
        navController.graph = graph
        graph.startDestination = R.id.ArFragment
            binding.text1.text = "get location"
        binding.text1.setOnClickListener {

            getLocation()
        }

    }



    fun getLocation() {

        if (checkForLocationPermission()) {
            updateLocation()
        } else {
            askLocationPermission()
        }
    }

    @SuppressLint("VisibleForTests")
    fun updateLocation() {
        var locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 10000
        locationRequest.fastestInterval = 5000

        mFusedLocationProviderClient = FusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        mFusedLocationProviderClient.requestLocationUpdates(
            locationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }


    var mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {

            var location: Location = p0.lastLocation
            itemLocation = LocationData(location.longitude, location.latitude)
            updateAddressUI(location)

        }
    }

    fun updateAddressUI(location: Location) {

        var geocoder: Geocoder
        var addressList = ArrayList<Address>()

        geocoder = Geocoder(applicationContext, Locale.getDefault())
        Log.e("location", "" +location.latitude + "");
        Log.e("location", "" + location.longitude );

        addressList = geocoder.getFromLocation(
            location.latitude,
            location.longitude,
            1
        ) as ArrayList<Address>
        binding.text1.text = addressList.get(0).getAddressLine(0)
    }


    fun checkForLocationPermission(): Boolean {

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        )
            return true

        return false

    }


    fun askLocationPermission() {

        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            locationRequestId
        )

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == locationRequestId) {

            if (grantResults != null && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                getLocation()
            }
        }

    }

}