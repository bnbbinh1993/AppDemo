package vn.bn.teams.appdemo.core.activities

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.addCallback
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.play.integrity.internal.t
import vn.bn.teams.appdemo.core.adapter.DetailBigListAlphabetAdapter
import vn.bn.teams.appdemo.core.custom.AudioManager
import vn.bn.teams.appdemo.data.Constants
import vn.bn.teams.appdemo.data.database.MyDatabase
import vn.bn.teams.appdemo.data.models.DataAlphabetFollow
import vn.bn.teams.appdemo.databinding.ActivityDetailBigListFollowBinding


class DetailBigListItemActivity : AppCompatActivity() {
    private var gridLayoutManager: GridLayoutManager? = null
    private var arrayList = mutableListOf<DataAlphabetFollow>()
    private var playlist: ArrayList<Int>? = null
    private var detailBigListAlphabetAdapter: DetailBigListAlphabetAdapter? = null
    private var db: MyDatabase? = null
    private var checkDestroy: Boolean = false
    var mediaPlayer: MediaPlayer? = null
    var i = 0
    private lateinit var binding: ActivityDetailBigListFollowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBigListFollowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = MyDatabase(this)
        initView()

        onClick()
    }

    private fun initView() {
        val title = intent.getStringExtra(Constants.KEY_DETAIL)
        val key = intent.getStringExtra(Constants.KEY_FOLLOW)

        when (title) {
            Constants.ALPHABETS -> {
                arrayList = db?.getAllItemsAlphabet()!!
                initRecyclerview()
                binding.txtTitle.text = title
                if (key == Constants.FOLLOW) {
                    playlist = db?.getSoundtemsAlphabet(this)
                    playListSound()
                }
            }

            Constants.NUMBER -> {
                arrayList = db?.getAllItemsNumber()!!
                initRecyclerview()
                binding.txtTitle.text = title
                if (key == Constants.FOLLOW) {
                    playlist = db?.getSoundtemsNumber(this)
                    playListSound()
                }
            }

            Constants.ANIMALS -> {
                arrayList = db?.getAllItemsAnimal()!!
                initRecyclerview()
                binding.txtTitle.text = title
                if (key == Constants.FOLLOW) {
                    playlist = db?.getSoundtemsAnimal(this)
                    playListSound()
                }
            }

            Constants.FRUITS -> {
                arrayList = db?.getAllItemsFruit()!!
                initRecyclerview()
                binding.txtTitle.text = title
                if (key == Constants.FOLLOW) {
                    playlist = db?.getSoundtemsFruit(this)
                    playListSound()
                }
            }

            Constants.SQUARE -> {
                arrayList = db?.getAllItemsGeometry()!!
                initRecyclerview()
                binding.txtTitle.text = title
                if (key == Constants.FOLLOW) {
                    playlist = db?.getSoundtemsGeometry(this)
                    playListSound()
                }
            }

            Constants.SCHOOL -> {
                arrayList = db?.getAllItemsSchool()!!
                initRecyclerview()
                binding.txtTitle.text = title
                if (key == Constants.FOLLOW) {
                    playlist = db?.getSoundtemSchool(this)
                    playListSound()
                }
            }

            Constants.COLOR -> {
                arrayList = db?.getAllItemsColor()!!
                initRecyclerview()
                binding.txtTitle.text = title
                if (key == Constants.FOLLOW) {

                    playlist = db?.getSoundtemsColor(this)
                    playListSound()
                }
            }

            Constants.FLOWERS -> {
                arrayList = db?.getAllItemsFlower()!!
                initRecyclerview()
                binding.txtTitle.text = title
                if (key == Constants.FOLLOW) {
                    playlist = db?.getSoundtemsFlower(this)
                    playListSound()
                }
            }

            Constants.VEHICLE -> {
                arrayList = db?.getAllItemsVehicle()!!
                initRecyclerview()
                binding.txtTitle.text = title
                if (key == Constants.FOLLOW) {
                    playlist = db?.getSoundtemsVehicle(this)
                    playListSound()
                }
            }

            Constants.COUNTRY -> {
                arrayList = db?.getAllItemsCountry()!!
                initRecyclerview()
                binding.txtTitle.text = title
                if (key == Constants.FOLLOW) {
                    playlist = db?.getSoundtemsCountry(this)
                    playListSound()
                }
            }
        }
    }

    private fun playListSound() {
        mediaPlayer = MediaPlayer.create(this, playlist!![0])
        mediaPlayer?.start()
        if (playlist!!.size > 1) playNext()

    }

    private fun playNext() {
        Handler(Looper.getMainLooper()).postDelayed({
            mediaPlayer!!.release()
            if (!checkDestroy) {
                mediaPlayer = MediaPlayer.create(this, playlist!![++i])
                mediaPlayer?.start()
                binding.listDetailAlphabet.smoothScrollToPosition(i+12)
            }
            mediaPlayer!!.setOnCompletionListener {
                if (playlist!!.size > i +1 && !checkDestroy) playNext()
            }
        }, 2000)
    }

    override fun onDestroy() {
        checkDestroy = true
        super.onDestroy()
    }

    private fun onClick() {
        binding.btnBack.setOnClickListener {
            callback.handleOnBackPressed()
        }
    }

    private fun setAnimation(itemView: View) {
        val anim = ValueAnimator.ofFloat(1f, 1.5f)
        anim.duration = 300
        anim.addUpdateListener { animation ->
            itemView.scaleX = animation.animatedValue as Float
            itemView.scaleY = animation.animatedValue as Float
        }
        anim.repeatCount = 1
        anim.repeatMode = ValueAnimator.REVERSE
        anim.start()
    }


    private fun initRecyclerview() {
        gridLayoutManager =
            GridLayoutManager(applicationContext, 3, LinearLayoutManager.VERTICAL, false)
        binding.listDetailAlphabet.layoutManager = gridLayoutManager
        binding.listDetailAlphabet.setHasFixedSize(true)
        detailBigListAlphabetAdapter = DetailBigListAlphabetAdapter(applicationContext, arrayList)
        binding.listDetailAlphabet.adapter = detailBigListAlphabetAdapter

        detailBigListAlphabetAdapter!!.itemClick =
            { pos: Int, dataAlphabetFollow: DataAlphabetFollow, view: View ->

                val audio = AudioManager(this, dataAlphabetFollow.sound)
                audio.startSound()


            }
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        callback.handleOnBackPressed()
    }

    private val callback = this.onBackPressedDispatcher.addCallback(this) {
        if (mediaPlayer != null) {
            mediaPlayer!!.release()
        }
        finish()
    }

}

