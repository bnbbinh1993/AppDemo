package vn.bn.teams.appdemo.core.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions


import vn.bn.teams.appdemo.core.utils.DialogUtil

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vn.bn.teams.appdemo.BaseActivity
import vn.bn.teams.appdemo.api.ApiInterface
import vn.bn.teams.appdemo.api.RetrofitInstance
import vn.bn.teams.appdemo.data.models.LoginResponse
import vn.bn.teams.appdemo.data.models.RegisterRequest
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
            if (binding.edtEmail.text.toString() != "" && binding.edtPass.text.toString() != "" && binding.edtName.text.toString() != "") {
                DialogUtil.progressDlgShow(this, "Chờ xíu...")
//                doSignUp(
//                    binding.edtEmail.text.toString(),
//                    binding.edtUser.text.toString(),
//                    binding.edtPass.text.toString(),
//                    binding.edtName.text.toString()
//                )
                registerByFirebase(
                    binding.edtName.text.toString(),
                    binding.edtEmail.text.toString(),
                    binding.edtPass.text.toString()
                )
            } else {
                Toast.makeText(this, "Vui Lòng Nhập Đầy Đủ Thông Tin", Toast.LENGTH_SHORT).show()
            }
        }
        binding.backToLogin.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun doSignUp(
        email: String, username: String,
        password: String, name: String
    ) {
        val retIn = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        val registerInfo =
            RegisterRequest(email = email, username = username, password = password, name = name)

        retIn.signUp(registerInfo).enqueue(object :
            Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                DialogUtil.progressDlgHide()
                Toast.makeText(
                    this@RegisterActivity,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.body()?.code == 200) {
                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(intent)
                    DialogUtil.progressDlgHide()
                } else {
                    DialogUtil.progressDlgHide()
                    Toast.makeText(
                        this@RegisterActivity,
                        "Thành Phần Thông Tin Đã Tồn Tại! \nVui Lòng Kiểm Tra Lại",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }

        })
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
                        // If sign in fails, display a message to the user.
                        Log.d("__reg", "createUserWithEmail:success")
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()

                    }
                }
        }

    }

    private fun updateData(
        user: FirebaseUser?,
        accountName: String,
        email: String
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
                    startActivity(Intent(this@RegisterActivity, HomeScreenActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this@RegisterActivity, "Đăng ký thất bại", Toast.LENGTH_SHORT)
                        .show()
                }

            }


    }


}