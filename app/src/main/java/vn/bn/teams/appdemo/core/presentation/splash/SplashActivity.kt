package vn.bn.teams.appdemo.core.presentation.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import vn.bn.teams.appdemo.R
import vn.bn.teams.appdemo.api.SessionManager
import vn.bn.teams.appdemo.core.activities.HomeScreenActivity


class SplashActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_splash)
        sessionManager = SessionManager(this)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, HomeScreenActivity::class.java))
            finish()
        }, 2000)
    }


}
