package vn.bn.teams.appdemo

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import vn.bn.teams.appdemo.data.models.UserInfo

object UserManager {
    var userInfo: UserInfo? = null

    interface LoadListener {
        fun onSuccess()
        fun onFailed(e: String)
    }

    @SuppressLint("LogNotTimber")
    fun loadUserInfo(source: Source, activity: Activity, loadListener: LoadListener) {
        FirebaseFirestore.getInstance().collection("users")
            .document(FirebaseAuth.getInstance().uid!!)
            .get(source)
            .addOnSuccessListener { documentSnapshot: DocumentSnapshot ->
                if (activity.isDestroyed || activity.isFinishing) {
                    return@addOnSuccessListener
                }
                userInfo = documentSnapshot.toObject(UserInfo::class.java)
                loadListener.onSuccess()
            }
            .addOnFailureListener { e: Exception? ->
                if (activity.isDestroyed || activity.isFinishing) {
                    return@addOnFailureListener
                }
                loadListener.onFailed(e!!.message!!)
                Log.d("__load", e.localizedMessage ?: "Load user info failed.")
            }
    }

    fun clearUser() {
        userInfo = null
        Firebase.auth.signOut()
    }


}