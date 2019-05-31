package com.example.resolution.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.resolution.R
import com.example.resolution.helper.FirebaseHelper
import com.example.resolution.ui.login.LoginActivity
import com.example.resolution.ui.main.MainActivity
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterView {
    private val presenter = RegisterPresenter(this, FirebaseHelper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        signUpButton.setOnClickListener {
            presenter.register(etEmail.text.toString(), etPassword.text.toString())
        }
        signedUpButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun setLoading(boolean: Boolean) {
        when(boolean){
            true -> {
                progressBar.visibility = View.VISIBLE
            }
            false ->{
                progressBar.visibility = View.GONE
            }
        }
    }

    override fun updateView(firebaseUser: FirebaseUser?) {
        if(firebaseUser != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(messageResId: Int) {
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }
}
