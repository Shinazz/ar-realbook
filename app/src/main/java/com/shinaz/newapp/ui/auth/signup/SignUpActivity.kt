package com.shinaz.newapp.ui.auth.signup

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.shinaz.newapp.R

class SignUpActivity : AppCompatActivity() {

    var token: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setSupportActionBar(findViewById(R.id.toolbar))

        val navHostFragment =  supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_signup) as NavHostFragment
        val graphInflater = navHostFragment.navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.nav_graph)
        val navController = navHostFragment.navController

        val uri = intent.data
        if(uri !==null && uri.pathSegments !== null && uri.pathSegments.size > 0 ) {
            uri.pathSegments[0]
            Log.e("uri shinaz", uri.toString());
            Toast.makeText(this, uri.toString(), Toast.LENGTH_LONG)
            Log.e("uri string", uri.pathSegments[0]);
            Toast.makeText(this,uri.pathSegments[0], Toast.LENGTH_LONG)
            token = uri.pathSegments[0]
            navGraph.startDestination = R.id.SecondFragment
        }  else {
            navGraph.startDestination = R.id.FirstFragment
            Log.e("helloooo", "uri.toString()");
            Toast.makeText(this, "ri.toString()", Toast.LENGTH_LONG)
        }
        navController.graph = navGraph

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }


}