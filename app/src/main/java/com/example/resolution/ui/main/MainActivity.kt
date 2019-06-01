package com.example.resolution.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.resolution.R
import com.example.resolution.helper.FirebaseHelper
import com.example.resolution.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*


class MainActivity : AppCompatActivity(), MainView {
    private val presenter = MainPresenter(this, FirebaseHelper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.isSignedIn()

    }
    override fun updateView(isSignedIn: Boolean) {
        val intent = Intent(this, LoginActivity::class.java)
        if (isSignedIn) {
            Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
        } else {
            startActivity(intent)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId){
            R.id.logoutButton -> {
                presenter.logout()
            }
        }
        return false
    }
}
