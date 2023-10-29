package vn.bn.teams.appdemo.api

import android.content.Context
import android.content.SharedPreferences
import vn.bn.teams.appdemo.R


class SessionManager (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
        const val USER_NAME = "user_name"
        const val NAME = "name"
    }

    /**
     * Function to save auth token
     */
    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }
    fun saveUserName(userName: String) {
        val editor = prefs.edit()
        editor.putString(USER_NAME, userName)
        editor.apply()
    }
    fun clearCache() {
        val editor = prefs.edit()
        editor.clear()
        editor.commit()
    }


    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }
    fun fetchAuthUserName(): String? {
        return prefs.getString(USER_NAME, null)
    }

}