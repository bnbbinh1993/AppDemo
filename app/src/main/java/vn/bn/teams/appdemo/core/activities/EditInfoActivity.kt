package vn.bn.teams.appdemo.core.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage

import vn.bn.teams.appdemo.BaseActivity
import vn.bn.teams.appdemo.R
import vn.bn.teams.appdemo.UserManager
import vn.bn.teams.appdemo.core.utils.DialogUtil
import vn.bn.teams.appdemo.databinding.ActivityEditInfoBinding
import java.io.File
import java.util.UUID

class EditInfoActivity : BaseActivity() {
    private lateinit var binding: ActivityEditInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUser()

        binding.mBackEdit.setOnClickListener { finish() }
        binding.imvSave.setOnClickListener {
            save()
        }
        binding.imvPhoto.setOnClickListener {
            showPhotoPermission()
        }


    }

    private fun initUser() {
        binding.tvName.setText(UserManager.userInfo!!.accountName)
        Glide.with(this@EditInfoActivity)
            .load(UserManager.userInfo!!.photo)
            .circleCrop()
            .error(R.drawable.test_avt)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(RequestOptions.circleCropTransform())
            .into(binding.imvPhoto)
    }

    private fun intentCrop() {
        val intent = Intent(this@EditInfoActivity, ImageCropActivity::class.java)
        intent.putExtra("photoFrom", "gallery")
        changeInfoResult.launch(intent)
    }

    private fun save() {
        DialogUtil.progressDlgShow(this,"Đang cập nhật thông tin")
        if (binding.tvName.text.isEmpty()) {
            Toast.makeText(this, "Tên không được để trống", Toast.LENGTH_SHORT).show()
        } else {

            val uid = UserManager.userInfo!!.uid
            val db = FirebaseFirestore.getInstance()
            val map: MutableMap<String, Any> = HashMap()
            map["accountName"] = binding.tvName.text.toString()

            if (path.isNotEmpty()) {
                map["photo"] = path
            }

            db.collection("users").document(uid!!).set(map, SetOptions.merge())
                .addOnCompleteListener {
                    if (isUnavailable) {
                        return@addOnCompleteListener
                    }
                    DialogUtil.progressDlgHide()
                    if (it.isSuccessful) {
                        if (path.isNotEmpty()) {
                            UserManager.userInfo!!.photo = path
                        }
                        UserManager.userInfo!!.accountName = binding.tvName.text.toString()
                        val resultIntent = Intent()
                        resultIntent.putExtra("key", 1)
                        setResult(RESULT_OK, resultIntent)
                        finish()
                    } else {
                        Toast.makeText(
                            this@EditInfoActivity,
                            "Thất bại",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }

                }
        }


    }

    var path = ""

    private fun uploadFile(path: String) {
        DialogUtil.progressDlgShow(this,"Đang tải ảnh")
        val file = Uri.fromFile(File(path))
        val fileName = UUID.randomUUID().toString() + ".jpg"
        val refStorage = FirebaseStorage.getInstance().reference.child("images/$fileName")

        refStorage.putFile(file)
            .addOnSuccessListener { taskSnapshot ->
                taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                    this.path = it.toString()
                    DialogUtil.progressDlgHide()
                }
            }

            .addOnFailureListener { e ->
                DialogUtil.progressDlgHide()
                Log.d("__url", "uploadFile: " + e.message)
            }


    }

    private fun showPhotoPermission() {
        val p0 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES)
        val p1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val p2 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        if (p0 != PackageManager.PERMISSION_GRANTED || p1 != PackageManager.PERMISSION_GRANTED || p2 != PackageManager.PERMISSION_GRANTED) {
            requestPhotoPermission()
        } else {
            intentCrop()
        }
    }

    private fun requestPhotoPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ), 123
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 123) {
            intentCrop()
        }
    }


    private var changeInfoResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            val it = result.data!!
            when (it.getIntExtra("key", 0)) {
                1 -> {
                    val path = it.getStringExtra("path")
                    Glide.with(this@EditInfoActivity)
                        .load(path)
                        .circleCrop()
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .apply(RequestOptions.circleCropTransform())
                        .into(binding.imvPhoto)

                    if (path != null) {
                        uploadFile(path)
                    }
                }

                else -> {

                }
            }

        }
    }
}