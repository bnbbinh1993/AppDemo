package vn.bn.teams.appdemo.core.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.LinearLayoutManager

import vn.bn.teams.appdemo.core.adapter.MyRecyclerViewAdapter
import vn.bn.teams.appdemo.core.adapter.SwipeReadAdapter

import vn.bn.teams.appdemo.data.database.MyDatabase


import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import vn.bn.teams.appdemo.R
import vn.bn.teams.appdemo.core.custom.AudioManager
import vn.bn.teams.appdemo.data.Constants
import vn.bn.teams.appdemo.data.models.DataAlphabetFollow
import vn.bn.teams.appdemo.databinding.ActivitySwipeReadBinding


class SwipeReadActivity : AppCompatActivity() {

    private var linearLayoutManager: LinearLayoutManager? = null
    private var arrayList: ArrayList<DataAlphabetFollow>? = null
    private var myRecyclerViewAdapter: MyRecyclerViewAdapter? = null
    private var db: MyDatabase? = null
    private var currentPosition: Int = 0

     lateinit var binding: ActivitySwipeReadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySwipeReadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = MyDatabase(this)
        initRecyclerview()
        onClick()
    }

    private fun initView() {
        val key = intent.getStringExtra(resources.getString(R.string.key_detail))
        when (key) {

            Constants.ALPHABETS -> {
                arrayList = db?.getAllItemsAlphabet()
            }
            Constants.NUMBER -> {
                arrayList = db?.getAllItemsNumber()
            }
            Constants.ANIMALS -> {
                arrayList = db?.getAllItemsAnimal()
            }
            Constants.FRUITS -> {
                arrayList = db?.getAllItemsFruit()
            }
            Constants.SQUARE -> {
                arrayList = db?.getAllItemsGeometry()
            }
            Constants.COLOR -> {
                arrayList = db?.getAllItemsColor()
            }
            Constants.SCHOOL -> {
                arrayList = db?.getAllItemsSchool()
            }
            Constants.FLOWERS -> {
                arrayList = db?.getAllItemsFlower()
            }
            Constants.VEHICLE -> {
                arrayList = db?.getAllItemsVehicle()
            }
            Constants.COUNTRY -> {
                arrayList = db?.getAllItemsCountry()
            }
        }
    }

    private fun initRecyclerview() {
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvswipe.layoutManager = linearLayoutManager
        binding.rvswipe.setHasFixedSize(true)
        initView()
        myRecyclerViewAdapter = MyRecyclerViewAdapter(applicationContext, arrayList!!, this)
        binding.rvswipe.adapter = myRecyclerViewAdapter
        // init viewpager

        val swipeAdapter = SwipeReadAdapter(applicationContext,arrayList!!)

        binding.vpswipe.adapter = swipeAdapter
        swipeViewpager()
        playSound(0)
    }

    private fun swipeViewpager() {
        binding.vpswipe.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                currentPosition = position
            }

            override fun onPageSelected(position: Int) {
                playSound(position)
                if (position <= currentPosition) {
                    if (position > 0) {
                        binding.rvswipe.post(Runnable { // Call smooth scroll
                            binding.rvswipe.smoothScrollToPosition(position - 1)
                        })
                    }
                    if (position == 0) {
                            binding.rvswipe.post(Runnable { // Call smooth scroll
                                binding.rvswipe.smoothScrollToPosition(position)
                            })
                    }
                } else {
                    if (currentPosition < arrayList!!.size - 1) {
                        binding.rvswipe.post(Runnable { // Call smooth scroll
                            binding.rvswipe.smoothScrollToPosition(position + 1)
                        })
                    }
                }
            }
        })
    }

    private fun getItem(i: Int): Int {
        return binding.vpswipe.currentItem + i
    }


    private fun onClick() {
        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.btnNext.setOnClickListener {
            binding.vpswipe.setCurrentItem(getItem(+1), true)
        }
        binding.btnPrev.setOnClickListener {
            binding.vpswipe.setCurrentItem(getItem(-1), true)
        }
        binding.btnSpeaker.setOnClickListener {
            playSoundAgain(currentPosition)
        }
    }
    private fun playSound(pos: Int) {
        Handler(Looper.getMainLooper()).postDelayed({
            var audioManager = AudioManager(applicationContext, arrayList!![pos].sound)
            audioManager.startSound()
        }, 1000)
    }
    private fun playSoundAgain(pos: Int) {
            var audioManager = AudioManager(applicationContext, arrayList!![pos].sound)
            audioManager.startSound()
    }
}