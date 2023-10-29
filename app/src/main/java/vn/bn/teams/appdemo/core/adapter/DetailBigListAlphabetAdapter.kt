package vn.bn.teams.appdemo.core.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import vn.bn.teams.appdemo.core.custom.AudioManager

import android.animation.ValueAnimator
import vn.bn.teams.appdemo.R
import vn.bn.teams.appdemo.data.models.DataAlphabetFollow


class DetailBigListAlphabetAdapter(
    var context: Context,
    var arrayList: MutableList<DataAlphabetFollow>
) :
    RecyclerView.Adapter<DetailBigListAlphabetAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_detail_alphabet, parent, false)
        return ViewHolder(itemHolder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataAlphabetFollow = arrayList[position]
        getImageId(dataAlphabetFollow.image, context)?.let { holder.image.setImageResource(it) }
        holder.itemView.setOnClickListener {
            val audioManager = AudioManager(context, dataAlphabetFollow.sound)
            audioManager.startSound()
            setAnimation(holder.itemView)
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

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image = itemView.findViewById<ImageView>(R.id.img_alphabet)
    }

    private fun getImageId(name: String?, context: Context): Int? {
        return if (name == null) {
            null
        } else {
            context.resources.getIdentifier(name, "drawable", context.packageName)
        }
    }
}