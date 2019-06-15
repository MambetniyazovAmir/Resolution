package com.example.resolution.ui.main

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resolution.R
import com.example.resolution.callback.OnImageItemClickListener
import com.example.resolution.data.ImageModel
import com.example.resolution.helper.FirebaseHelper
import com.example.resolution.ui.login.LoginActivity
import com.example.resolution.ui.main.list.ResolutionAdapter
import com.example.resolution.ui.review.ReviewActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView, OnImageItemClickListener {
    private val presenter = MainPresenter(this, FirebaseHelper())
    private val imageCode = 1
    private val permissionCode = 2
    private val adapter = ResolutionAdapter(this)
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (auth.currentUser == null) {
            updateView(false)
            return
        }
        presenter.isSignedIn()

        listView.adapter = adapter
        listView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        choosePhoto.setOnClickListener {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED){
                    val permissions = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, permissionCode)
                }
                else{
                    pickImageFromGallery()
                }
            }
            else{
                pickImageFromGallery()
            }
        }

        presenter.getHistory()
    }
    private fun pickImageFromGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent,imageCode)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            permissionCode -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    pickImageFromGallery()
                else{
                    Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //super.onActivityResult(requestCode, resultCode, data)
        if(resultCode==Activity.RESULT_OK && requestCode==imageCode){
            presenter.pickImage(this, data?.data)
        }
    }
    override fun updateView(isSignedIn: Boolean) {
        if (!isSignedIn) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return
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

    override fun setLoading(isLoading: Boolean) {
        if (isLoading){
            progressBar.visibility = View.VISIBLE
        }
        else{
            progressBar.visibility = View.GONE
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(messageResId: Int) {
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }

    override fun showResizedImage(model: ImageModel) {
        val intent = Intent(this, ReviewActivity::class.java)
        intent.putExtra(ReviewActivity.ORIGINAL_IMAGE_URL, model.original_image)
        intent.putExtra(ReviewActivity.SUPER_IMAGE_URL, model.super_image)
        startActivity(intent)
    }

    override fun onItemClick(model: ImageModel) {
        showResizedImage(model)
    }

    override fun setData(models: List<ImageModel>) {
        adapter.setData(models)
    }
}
