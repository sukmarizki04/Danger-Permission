package com.form.permisionruntime

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.form.permisionruntime.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val CAMERA_PERMISION_CODE=123
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCamera.setOnClickListener {
            checkPermission(Manifest.permission.CAMERA,CAMERA_PERMISION_CODE)
        }
    }
    private fun checkPermission(permission:String,requestCode:Int){
        if (ContextCompat.checkSelfPermission(this@MainActivity,permission)==PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(permission),requestCode)
        }else {
            Toast.makeText(this@MainActivity,"Permision Succes",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == CAMERA_PERMISION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_DENIED){
                binding.btnCamera.text = "Permision Granted"
                Toast.makeText(this@MainActivity,"Camera Permision gagal",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@MainActivity,"Camera Permision Succes",Toast.LENGTH_SHORT).show()
            }
        }
    }
}