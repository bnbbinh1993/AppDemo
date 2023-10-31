package vn.bn.teams.appdemo.core.activities


import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.Window
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import vn.bn.teams.appdemo.BaseActivity
import vn.bn.teams.appdemo.R
import vn.bn.teams.appdemo.core.utils.DialogUtil
import vn.bn.teams.appdemo.databinding.ActivityRegisterBinding


class RegisterActivity : BaseActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClick()
    }

    private fun onClick() {
        binding.btnRegister.setOnClickListener {
            DialogUtil.progressDlgShow(this, "Chờ xíu...")
            val email = binding.edtEmail.text.toString()
            val pass = binding.edtPass.text.toString()
            val name = binding.edtName.text.toString()
            if (email != "" && pass != "" && name != "") {
                if (!isValidEmail(email)) {
                    DialogUtil.progressDlgHide()
                    showNotification("Vui lòng nhập đúng email")
                    binding.edtEmail.requestFocus()
                } else if (pass.length >= 6 && !containsLetterAndNumber(pass)) {
                    DialogUtil.progressDlgHide()
                    showNotification("Mật khẩu phải có cả chữ và số\nTối thiểu 6 kí tự")
                    binding.edtPass.requestFocus()
                }else{
                    registerByFirebase(
                        binding.edtName.text.toString(),
                        binding.edtEmail.text.toString(),
                        binding.edtPass.text.toString()
                    )
                }

            } else {
                DialogUtil.progressDlgHide()
                showNotification("Vui lòng nhập đủ thông tin")
            }
        }
        binding.backToLogin.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showNotification(message: String) {
        val dialog = Dialog(this@RegisterActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(
            LayoutInflater.from(this@RegisterActivity)
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

    private fun containsLetterAndNumber(input: String): Boolean {
        val letterPattern = ".*[a-zA-Z].*"
        val digitPattern = ".*\\d.*"

        return input.matches(Regex(letterPattern)) && input.matches(Regex(digitPattern))
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private lateinit var auth: FirebaseAuth

    @SuppressLint("LogNotTimber")
    private fun registerByFirebase(accountName: String, email: String, password: String) {
        auth = Firebase.auth
        val currentUser = auth.currentUser

        if (currentUser != null) {
            //đã đăng nhập
        } else {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("__reg", "createUserWithEmail:success")
                        val user = auth.currentUser
                        updateData(user, accountName, email)
                    } else {
                        DialogUtil.progressDlgHide()
                        showNotification("Email đã có người sử dụng!")

                    }
                }
        }

    }

    private fun updateData(
        user: FirebaseUser?, accountName: String, email: String
    ) {
        val map: MutableMap<String, Any> = HashMap()
        val time = System.currentTimeMillis()
        map["createdTime"] = time
        map["photo"] = ""
        map["accountName"] = "" + accountName
        map["email"] = "" + email
        map["versionCode"] = 1000
        map["banned"] = false
        map["uid"] = "" + user!!.uid

        val db = FirebaseFirestore.getInstance()
        db.collection("users").document(user.uid).set(map, SetOptions.merge())
            .addOnCompleteListener {
                if (isUnavailable) {
                    return@addOnCompleteListener
                }
                DialogUtil.progressDlgHide()
                if (it.isSuccessful) {
                    val intent = Intent(this@RegisterActivity, HomeScreenActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                    finish()
                } else {
                    showNotification("Đăng ký thất bại")
                }

            }


    }


}