package vn.bn.teams.appdemo.core.utils

import android.app.ProgressDialog
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * DialogUtilクラス
 */
class DialogUtil {
    companion object {
        private const val TAG = "DialogUtil"
        private var mProgressDialog: ProgressDialog? = null

        // プログレスダイアログ共通処理
        fun progressDlgShow(act: AppCompatActivity, mes: String) {
            if (mProgressDialog != null) {
                mProgressDialog?.dismiss()
                mProgressDialog = null
            }
            if (act.isFinishing) {
                // 呼出し元Activityが既に終了状態の場合はキャンセル
                // 「戻る」ボタンと各操作を同時押しするなどした場合に発生する
                Log.d(TAG, "owner Activity isFinishing!!!")
                return
            }
            mProgressDialog = ProgressDialog(act)
            mProgressDialog?.setCancelable(false)
            mProgressDialog?.setMessage(mes)
            mProgressDialog?.show()
        }

        fun progressDlgSetMsg(mes: String) {
            mProgressDialog?.setMessage(mes)
        }

        fun progressDlgHide() {
            if (mProgressDialog != null) {
                mProgressDialog?.dismiss()
                mProgressDialog = null
            }
        }

    }
}