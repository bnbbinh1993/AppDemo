package vn.bn.teams.appdemo.core.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vn.bn.teams.appdemo.R
import vn.bn.teams.appdemo.core.activities.BigListItemActivity
import vn.bn.teams.appdemo.core.activities.DetailBigListItemActivity
import vn.bn.teams.appdemo.core.activities.SwipeReadActivity
import vn.bn.teams.appdemo.core.custom.AudioManager
import vn.bn.teams.appdemo.data.Constants
import vn.bn.teams.appdemo.data.models.DataFollow


class BigListFollowAdapter(var context: Context, var mContext: BigListItemActivity) :
    RecyclerView.Adapter<BigListFollowAdapter.ViewHolder>() {
    private var arrayList: ArrayList<DataFollow>? = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_big_list_follow, parent, false)
        return ViewHolder(itemHolder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataBigList: DataFollow = arrayList!![position]
        getImageId(dataBigList.img_content, context)?.let { holder.content.setImageResource(it) }
        setAnimation(holder.itemView)
        holder.title.text = dataBigList.title
        holder.itemView.setOnClickListener {
            val audioManager = AudioManager(context, dataBigList.sound)
            audioManager.startSound()
            setAnimation(holder.itemView)
            if (mContext.binding.txtTitle.text == Constants.SWIPE) {
                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(context, SwipeReadActivity::class.java)
                    intent.putExtra(Constants.KEY_DETAIL, dataBigList.title)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent)
                }, 1500)
            } else {
                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(context, DetailBigListItemActivity::class.java)
                    intent.putExtra(Constants.KEY_DETAIL, dataBigList.title)
                    intent.putExtra(Constants.KEY_FOLLOW, mContext.key)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent)
                }, 1500)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: ArrayList<DataFollow>) {
        this.arrayList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return arrayList!!.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var content = itemView.findViewById<ImageView>(R.id.imgContent)
        var title = itemView.findViewById<TextView>(R.id.txtTitle)
    }

    private fun getImageId(name: String?, context: Context): Int? {
        return if (name == null) {
            null
        } else {
            context.resources.getIdentifier(name, "drawable", context.packageName)
        }
    }

    fun setAnimation(item: View) {
        AnimationUtils.loadAnimation(context, R.anim.anim_flip).also { hyperspaceJumpAnimation ->
            item.startAnimation(hyperspaceJumpAnimation)
        }
    }

}