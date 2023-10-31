package vn.bn.teams.appdemo.core.activities

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthActionCodeException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.auth

import vn.bn.teams.appdemo.core.utils.DialogUtil

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vn.bn.teams.appdemo.R

import vn.bn.teams.appdemo.api.ApiInterface
import vn.bn.teams.appdemo.api.RetrofitInstance
import vn.bn.teams.appdemo.api.SessionManager
import vn.bn.teams.appdemo.data.models.LoginRequest
import vn.bn.teams.appdemo.data.models.LoginResponse
import vn.bn.teams.appdemo.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        onClick()
        sessionManager = SessionManager(this)


    }

    private fun onClick() {
        binding.registerAccount.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {

            if (binding.edtUserName.text.toString() != "" && binding.edtPassWord.text.toString() != "") {
                DialogUtil.progressDlgShow(this, "Chờ xíu...")
                doLogin(binding.edtUserName.text.toString(), binding.edtPassWord.text.toString())
            } else {
                showNotification("Vui lòng nhập đủ thông tin")
            }
        }
        binding.forgotPassword.setOnClickListener {
            forgotPassword()
        }
    }

    private fun doLogin(
        username: String,
        password: String
    ) {
        logInByFirebase(username, password)
    }

    private fun showNotification(message: String) {
        val dialog = Dialog(this@LoginActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(
            LayoutInflater.from(this@LoginActivity)
                .inflate(R.layout.dialog_notification_error, null)
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
        val cancel = dialog.findViewById<MaterialButton>(R.id.btnCancel)
        val tvMessage = dialog.findViewById<TextView>(R.id.tvMessage)
        tvMessage.text = message
        cancel.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun logInByFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                DialogUtil.progressDlgHide()
                if (task.isSuccessful) {
                    val intent = Intent(this@LoginActivity, HomeScreenActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                    finish()
                } else {
                    showNotification("Thông tin email hoặc mật khẩu không chính xác!")

                }
            }
    }

    private lateinit var auth: FirebaseAuth

    @SuppressLint("LogNotTimber")
    private fun forgotPassword() {

        val dialog = Dialog(this@LoginActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(
            LayoutInflater.from(this@LoginActivity).inflate(R.layout.dialog_reset_pass, null)
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
        val send = dialog.findViewById<MaterialButton>(R.id.btnSend)
        val tvEmail = dialog.findViewById<TextView>(R.id.tvEmail)

        send.setOnClickListener {
            dialog.dismiss()
            if (tvEmail.text.isNotEmpty()) {
                auth.sendPasswordResetEmail(tvEmail.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this@LoginActivity,
                                "Đã gửi link xác minh tới email của bạn",
                                Toast.LENGTH_SHORT
                            ).show()
                            Log.d("__tag", "Email xác minh đã được gửi.")

                        } else {
                            val errorMessage = when (task.exception) {
                                is FirebaseAuthActionCodeException -> "Lỗi: Địa chỉ email không hợp lệ."
                                is FirebaseAuthUserCollisionException -> "Lỗi: Địa chỉ email không tồn tại."
                                else -> "Lỗi: ${task.exception?.message}"
                            }
                            Log.w("__tag", "Đã xảy ra lỗi: $errorMessage")

                        }
                    }
            }

        }


    }


}