package vn.bn.teams.appdemo

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate


open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        makeStatusBarTransparent()

    }
    fun makeStatusBarTransparent() {
        val s = window.decorView.systemUiVisibility
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or s
    }


    protected fun showLoading() {

    }
    protected fun hideLoading() {

    }


    fun makeFullScreen() {
        //requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    protected val isUnavailable: Boolean
        get() = isFinishing || isDestroyed


}