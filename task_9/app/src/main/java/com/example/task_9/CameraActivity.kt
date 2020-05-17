package com.example.task_9


import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.CameraView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.example.task_9.dataManagment.NewsPreview
import kotlinx.coroutines.*
import java.io.File
import java.util.*


class CameraActivity : AppCompatActivity() {

    companion object {
        const val CAMERA_REQUEST_CODE = 0
    }

    private var cameraView: CameraView? = null
    var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.camera_view)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED) {
            startCamera()
        } else {
            requestPermission()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),
            CAMERA_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCamera()
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.CAMERA
                    )
                ) {
                    Toast.makeText(this,
                        "Nam ochen nujna! Inache nado budet lezt v nastroyki",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(this,
                        "Nu lez v nastroyki togda",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                finish()
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun startCamera() {
        cameraView = findViewById(R.id.camera_view)
        cameraView?.captureMode = CameraView.CaptureMode.IMAGE
        cameraView?.bindToLifecycle(this as LifecycleOwner)
    }

    fun takePicture(view: View?) {
        val file = generatePictureFile()
        cameraView?.takePicture(
            file,
            AsyncTask.SERIAL_EXECUTOR,
            object: ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    job = GlobalScope.launch(context = Dispatchers.Main) {
                        val text = null
                        val rnds = (0..1000000).random()
                        val preview: NewsPreview = NewsPreview(
                            -1,
                            "New news header ${rnds}",
                            Date(),
                            "Content ${rnds}",
                            file.absolutePath
                        )
                        App.addNews(preview)
                        finish()
                    }
                }
                override fun onError(exception: ImageCaptureException) {
                    finish()
                }
            }
        )
    }

    private fun generatePictureFile(): File {
        return File(filesDir, UUID.randomUUID().toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }
}