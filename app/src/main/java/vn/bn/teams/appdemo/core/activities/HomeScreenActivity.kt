package vn.bn.teams.appdemo.core.activities

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Source
import vn.bn.teams.appdemo.core.utils.DialogUtil

import vn.bn.teams.appdemo.R
import vn.bn.teams.appdemo.UserManager

import vn.bn.teams.appdemo.core.custom.AudioManager
import vn.bn.teams.appdemo.data.Constants
import vn.bn.teams.appdemo.databinding.ActivityHomeScreenBinding

class HomeScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)



        if (FirebaseAuth.getInstance().uid == null
        ) {
            login()
            return
        } else {

            UserManager.loadUserInfo(
                Source.SERVER,
                this@HomeScreenActivity,
                object : UserManager.LoadListener {
                    override fun onSuccess() {
                        initUser()
                        onClick()
                    }

                    override fun onFailed(e: String) {
                        Toast.makeText(
                            this@HomeScreenActivity,
                            "Kết nối thất bại vui lòng thử lại",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })

        }


    }

    private fun initUser() {
        binding.tvName.text = UserManager.userInfo!!.accountName
        Glide.with(this@HomeScreenActivity)
            .load(UserManager.userInfo!!.photo)
            .circleCrop()
            .error(R.drawable.test_avt)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(RequestOptions.circleCropTransform())
            .into(binding.imvAvatar)
        binding.imvAvatar.scaleType = ImageView.ScaleType.CENTER_CROP
    }


    private fun login() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }


    private fun onClick() {

        binding.editInfo.setOnClickListener {
            val intent = Intent(this@HomeScreenActivity, EditInfoActivity::class.java)
            changeInfoResult.launch(intent)

        }

        binding.rank.setOnClickListener {
            startActivity(Intent(this@HomeScreenActivity, RankActivity::class.java))
        }

        binding.btnFollow.setOnClickListener {
            DialogUtil.progressDlgShow(this, "Chờ xíu...")
            playSound("sounds_start_doctheo")
            val intent = Intent(this@HomeScreenActivity, BigListItemActivity::class.java)
            intent.putExtra(Constants.KEY_HOME, binding.btnFollow.text)
            intent.putExtra(Constants.KEY_HOME_FOLLOW, Constants.FOLLOW)
            startActivity(intent)
        }
        binding.btnTouch.setOnClickListener {
            DialogUtil.progressDlgShow(this, "Chờ xíu...")
            playSound("sounds_start_chamvadoc")
            val intent = Intent(this@HomeScreenActivity, BigListItemActivity::class.java)
            intent.putExtra(Constants.KEY_HOME, binding.btnTouch.text)
            intent.putExtra(Constants.KEY_HOME_FOLLOW, Constants.TOUCH)
            startActivity(intent)
        }
        binding.btnTest.setOnClickListener {
            DialogUtil.progressDlgShow(this, "Chờ xíu...")
            playSound("sounds_start_test")
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this@HomeScreenActivity, TestActivity::class.java)
                startActivity(intent)
            }, 2500)

        }
        binding.btnSwipe.setOnClickListener {
            DialogUtil.progressDlgShow(this, "Chờ xíu...")
            playSound("sounds_start_vuotvadoc")
            val intent = Intent(this@HomeScreenActivity, BigListItemActivity::class.java)
            intent.putExtra(Constants.KEY_HOME, binding.btnSwipe.text)
            startActivity(intent)

        }
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage("Bạn muốn đăng xuất?")
                .setPositiveButton("Đồng ý") { _, _ ->
                    doLogout()
                }
                .setCancelable(true)
                .show()
        }

    }

    private fun playSound(name: String) {
        val audioManager = AudioManager(applicationContext, name)
        audioManager.startSound()
    }

    fun doLogout() {
        UserManager.clearUser()
        DialogUtil.progressDlgShow(this, "Chờ xíu...")
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@HomeScreenActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
            DialogUtil.progressDlgHide()
        }, 1500)

    }


    private var changeInfoResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            val it = result.data!!
            when (it.getIntExtra("key", 0)) {
                1 -> {

                    Glide.with(this@HomeScreenActivity)
                        .load(UserManager.userInfo!!.photo)
                        .circleCrop()
                        .error(R.drawable.test_avt)
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .apply(RequestOptions.circleCropTransform())
                        .into(binding.imvAvatar)
                    binding.tvName.text = UserManager.userInfo!!.accountName

                }

                else -> {

                }
            }

        }
    }


}