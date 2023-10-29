package vn.bn.teams.appdemo.core.adapter

import android.animation.ValueAnimator
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import vn.bn.teams.appdemo.R

import vn.bn.teams.appdemo.core.activities.TestActivity
import vn.bn.teams.appdemo.core.custom.AudioManager

import vn.bn.teams.appdemo.data.models.DataQuiz


class TestPagerAdapter(
    var list: ArrayList<DataQuiz>,
    var context: Context,
    var mContext: TestActivity
) : PagerAdapter() {
    var choose: String? = null
    var ans: String? = null
    var checkCorrect: Boolean? = null
    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as View
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context)
            .inflate(R.layout.item_quiz, container, false)

        val imgOption1: ImageView = view.findViewById(R.id.option1)
        val imgOption2: ImageView = view.findViewById(R.id.option2)
        val imgOption3: ImageView = view.findViewById(R.id.option3)
        val imgOption4: ImageView = view.findViewById(R.id.option4)

        getImageId(list[position].option1, context)?.let { imgOption1.setImageResource(it) }
        getImageId(list[position].option2, context)?.let { imgOption2.setImageResource(it) }
        getImageId(list[position].option3, context)?.let { imgOption3.setImageResource(it) }
        getImageId(list[position].option4, context)?.let { imgOption4.setImageResource(it) }
        container.addView(view)
        imgOption1.setOnClickListener {
            choose = list[position].option1.split("img_".toRegex())[1]
            onClick(position)
        }
        imgOption2.setOnClickListener {
            choose = list[position].option2.split("img_".toRegex())[1]
            onClick(position)
        }
        imgOption3.setOnClickListener {
            choose = list[position].option3.split("img_".toRegex())[1]
            onClick(position)
        }
        imgOption4.setOnClickListener {
            choose = list[position].option4.split("img_".toRegex())[1]
            onClick(position)
        }
        return view
    }

    override fun destroyItem(parent: ViewGroup, position: Int, `object`: Any) {
        // Remove the view from view group specified position
        parent.removeView(`object` as View)
    }

    private var correct = 0.toLong()

    fun playSoundCorrect() {
        correct++
        val audioManager = AudioManager(context, "sounds_effect_chinhxac")
        audioManager.startSound()
        Handler(Looper.getMainLooper()).postDelayed({
            mContext.doNext(correct)
            isClick = true
        }, 2000)

    }

    fun playSoundInCorrect() {
        val audioManager = AudioManager(context, "sounds_effect_sai")
        audioManager.startSound()
        Handler(Looper.getMainLooper()).postDelayed({
            mContext.doNext(correct)
            isClick = true
        }, 2000)
    }

    private fun getImageId(name: String?, context: Context): Int? {
        return if (name == null) {
            null
        } else {
            context.resources.getIdentifier(name, "drawable", context.packageName)
        }
    }

    private var isClick = true
    fun onClick(position: Int) {
        if (isClick){
            isClick = false
            ans = list[position].ans.split("sounds_".toRegex())[1]
            if (choose == ans) {
                playSoundCorrect()
                mContext.binding.iconAnswer.setImageResource(R.drawable.correct)
                setAnimation(mContext.binding.iconAnswer)
            } else {
                playSoundInCorrect()
                mContext.binding.iconAnswer.setImageResource(R.drawable.incorrect)
                setAnimation(mContext.binding.iconAnswer)
            }
        }

    }

    private fun setAnimation(itemView: View) {
        val anim = ValueAnimator.ofFloat(0f, 1f)
        anim.duration = 600
        anim.addUpdateListener { animation ->
            itemView.scaleX = animation.animatedValue as Float
            itemView.scaleY = animation.animatedValue as Float
        }
        anim.repeatCount = 1
        anim.repeatMode = ValueAnimator.REVERSE
        anim.start()
    }

}