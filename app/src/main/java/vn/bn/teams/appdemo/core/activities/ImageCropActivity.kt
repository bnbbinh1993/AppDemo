package vn.bn.teams.appdemo.core.activities

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.isseiaoki.simplecropview.CropImageView
import com.isseiaoki.simplecropview.callback.CropCallback
import vn.bn.teams.appdemo.databinding.ActivityImageCropBinding
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ImageCropActivity : AppCompatActivity() {

    private var takePhotoFile: File? = null
    private val resultIntent = Intent()
    private lateinit var binding: ActivityImageCropBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageCropBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.cropImageView.setMinFrameSizeInPx(180)
        binding.cropImageView.setOutputMaxSize(180, 180)
        binding.cropImageView.setCropMode(CropImageView.CropMode.CIRCLE_SQUARE)
        binding.cropImageView.setHandleShowMode(CropImageView.ShowMode.SHOW_ALWAYS)
        binding.cropImageView.setGuideShowMode(CropImageView.ShowMode.SHOW_ALWAYS)
        binding.cropImageView.setAnimationEnabled(true)

        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.btnSave.setOnClickListener {
            crop()
        }

        val photoFrom = intent.getStringExtra("photoFrom")
        val type = intent.getStringExtra("type")
        resultIntent.putExtra("type", type)

        if (photoFrom != null && photoFrom == "camera") {
            takePhoto()
        } else {
            openGallery()
        }
    }

    private fun crop() {
        binding.cropImageView.cropAsync(object : CropCallback {
            override fun onSuccess(cropped: Bitmap) {
                if (isFinishing || isDestroyed) {
                    return
                }
                try {

                    val f = createImageFile()
                    val bos = ByteArrayOutputStream()
                    cropped.compress(Bitmap.CompressFormat.JPEG, 80, bos)
                    val bitmapData = bos.toByteArray()
                    val fos = FileOutputStream(f)
                    fos.write(bitmapData)
                    fos.flush()
                    fos.close()
                    resultIntent.putExtra("path", f.path)
                    resultIntent.putExtra("key", 1)
                    setResult(RESULT_OK, resultIntent)
                    finish()
                    Log.d("__cropped compress", "resize: " + f.length() / 1024)
                } catch (e: IOException) {
                    Toast.makeText(this@ImageCropActivity, e.localizedMessage, Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onError(e: Throwable) {
                Toast.makeText(this@ImageCropActivity, e.localizedMessage, Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    var galleryStartForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK && result.data != null && result.data!!
                .data != null
        ) {
            loadToCropView(result.data!!.data!!)
        } else {
            finish()
        }
    }


    private fun openGallery() {
        try {
            galleryStartForResult.launch(
                Intent().setAction(Intent.ACTION_GET_CONTENT).setType("image/*")
            )
        } catch (exp: Exception) {
            Toast.makeText(this@ImageCropActivity, exp.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }


    private fun takePhoto() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            // Create the File where the photo should go
            try {
                takePhotoFile = createImageFile()
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
            // Continue only if the File was successfully created
            if (takePhotoFile != null) {
                val uri = FileProvider.getUriForFile(
                    this,
                    "vn.bn.teams.appdemo.fileprovider",
                    takePhotoFile!!
                )
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
                takePhotoStartForResult.launch(takePictureIntent)
            }
        }


    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp: String = System.currentTimeMillis().toString()
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            //currentPhotoPath = absolutePath
        }
    }

    private var takePhotoStartForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            val uri = FileProvider.getUriForFile(
                this,
                "vn.bn.teams.appdemo.fileprovider",
                takePhotoFile!!
            )
            loadToCropView(uri)


        } else {
            finish()
        }

    }

    private fun loadToCropView(uri: Uri) {
        binding.cropImageView.load(uri).execute(null)
    }
}