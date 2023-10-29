package vn.bn.teams.appdemo.core.activities

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View.OnTouchListener
import android.view.Window
import android.widget.TextView

import com.google.android.material.button.MaterialButton
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

import com.google.firebase.firestore.SetOptions

import vn.bn.teams.appdemo.BaseActivity
import vn.bn.teams.appdemo.R
import vn.bn.teams.appdemo.UserManager

import vn.bn.teams.appdemo.core.adapter.TestPagerAdapter
import vn.bn.teams.appdemo.core.custom.AudioManager
import vn.bn.teams.appdemo.data.database.MyDatabase

import vn.bn.teams.appdemo.core.utils.DialogUtil

import vn.bn.teams.appdemo.data.models.DataAlphabetFollow
import vn.bn.teams.appdemo.data.models.DataQuiz
import vn.bn.teams.appdemo.databinding.ActivityTestBinding


class TestActivity : BaseActivity() {
    private var db: MyDatabase? = null
    var listQuiz: ArrayList<DataAlphabetFollow>? = null
    var quizs: ArrayList<DataQuiz>? = null
    var adapter: TestPagerAdapter? = null
    var data: String? = null
    var position = 0

    lateinit var binding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        DialogUtil.progressDlgHide()
        db = MyDatabase(this)
        onClick()
        setListQuiz()
        getQuiz(position)
    }

    @SuppressLint("SetTextI18n")
    private fun getQuiz(pos: Int) {
        binding.tvTotal.text = "" + (pos + 1) + "/10"
        Handler(Looper.getMainLooper()).postDelayed({
            val audioManager = AudioManager(applicationContext, quizs!![pos].ans)
            audioManager.startSound()
        }, 100)
    }

    private fun playAgain(pos: Int) {
        val audioManager = AudioManager(applicationContext, quizs!![pos].ans)
        audioManager.startSound()
    }

    private fun setListQuiz() {
        listQuiz = db?.getTest()
        quizs = ArrayList()
        for (i in 0..9) {
            quizs!!.add(
                DataQuiz(
                    listQuiz!![i * 4].image,
                    listQuiz!![i * 4 + 1].image,
                    listQuiz!![i * 4 + 2].image,
                    listQuiz!![i * 4 + 3].image,
                    getRandomSound(i)!!
                )
            )
        }
        adapter = TestPagerAdapter(quizs!!, applicationContext, this)
        binding.vpquiz.adapter = adapter
        binding.vpquiz.setOnTouchListener(OnTouchListener { v, event -> true })
    }

    fun getRandomSound(i: Int): String? {
        val rand = (i * 4..i * 4 + 3).random()
        return listQuiz!![rand].sound
    }

    private fun onClick() {
        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.btnNext.setOnClickListener {
            // doNext()
        }
        binding.btnPrev.setOnClickListener {
            doPrev()
        }
        binding.vpquiz.setOnClickListener {
            Log.d("TruongDV19", "clicked")
        }
        binding.btnSpeaker.setOnClickListener {
            playAgain(position)
        }
    }

    fun doPrev() {
        if (position > 0) {
            binding.vpquiz.setCurrentItem(getItem(-1), true)
            position--
            getQuiz(position)
        } else {
            position = 0
        }
    }

    fun doNext(pos: Long) {
        if (position < quizs!!.size) {
            binding.vpquiz.setCurrentItem(getItem(+1), true)
            position++
            if (position < quizs!!.size) {
                getQuiz(position)
            } else {
                convertPoint(pos)
            }

        }
    }

    private fun convertPoint(point: Long) {
        val rank: Long = if (point > 8) {
            5
        } else if (point > 6) {
            2
        } else {
            0
        }
        DialogUtil.progressDlgSetMsg("Đang xử lý")
        val map: MutableMap<String, Any> = HashMap()
        map["uid"] = UserManager.userInfo!!.uid!!
        map["name"] = UserManager.userInfo!!.accountName!!
        map["avatar"] = UserManager.userInfo!!.photo!!
        map["rank"] = FieldValue.increment(rank)
        val db = FirebaseFirestore.getInstance()
        db.collection("rank").document(UserManager.userInfo!!.uid!!).set(map, SetOptions.merge())
            .addOnCompleteListener {
                if (isUnavailable) {
                    return@addOnCompleteListener
                }
                DialogUtil.progressDlgHide()
                showDialogDownload(point, rank)
            }
    }


    @SuppressLint("SetTextI18n")
    private fun showDialogDownload(point: Long, rank: Long) {
        val dialog = Dialog(this@TestActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(
            LayoutInflater.from(this@TestActivity).inflate(R.layout.dialog_result, null)
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
        val ok = dialog.findViewById<MaterialButton>(R.id.btnAgree)
        val tvResult = dialog.findViewById<TextView>(R.id.tvResult)
        val tvRank = dialog.findViewById<TextView>(R.id.tvRank)
        tvResult.text = "Số điểm bạn đạt được là $point/10"
        tvRank.text = "Điểm rank: +$rank"
        ok.setOnClickListener {
            dialog.dismiss()
            finish()
        }
    }


    private fun getItem(i: Int): Int {
        return binding.vpquiz.currentItem + i
    }


}