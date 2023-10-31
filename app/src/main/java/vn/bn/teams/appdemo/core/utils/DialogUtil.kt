package vn.bn.teams.appdemo.core.utils

import android.app.Dialog
import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.Window
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import vn.bn.teams.appdemo.R

/**
 * DialogUtilクラス
 */
class DialogUtil {
    companion object {
        private const val TAG = "DialogUtil"

        var dialog: Dialog? = null

        // プログレスダイアログ共通処理
        fun progressDlgShow(act: AppCompatActivity, mes: String) {
            if (dialog != null) {
                dialog!!.dismiss()
                dialog = null
            }
            if (act.isFinishing) {
                Log.d(TAG, "owner Activity isFinishing!!!")
                return
            }
            dialog = Dialog(act)
            dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog!!.setContentView(
                LayoutInflater.from(act)
                    .inflate(R.layout.dialog_loading, null)
            )
            dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog!!.setCanceledOnTouchOutside(false)
            dialog!!.setCancelable(false)
            dialog!!.show()
            val tvMessage = dialog!!.findViewById<TextView>(R.id.tvMessage)
            tvMessage.text = mes

        }

        fun progressDlgHide() {
            if (dialog != null) {
                dialog?.dismiss()
                dialog = null
            }
        }

    }
}