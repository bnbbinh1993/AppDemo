package vn.bn.teams.appdemo.core.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import vn.bn.teams.appdemo.R
import vn.bn.teams.appdemo.data.models.Rank

class RankAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var list = mutableListOf<Rank>()
    var itemClick: ((position: Int, rank: Rank, view: View) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setListRank(list: MutableList<Rank>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun addRank(list: MutableList<Rank>) {
        val size = list.size
        list.addAll(list)
        notifyItemRangeInserted(size - 1, list.size)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return VH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_rank_1, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val hd = holder as VH
        hd.bind(list[position], position)
        hd.itemView.setOnClickListener {
            itemClick?.invoke(
                position,
                list[position],
                it
            )
        }
    }


}

class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val avatar: ImageView = itemView.findViewById(R.id.imvAvatar)
    private val name: TextView = itemView.findViewById(R.id.tvName)
    private val point: TextView = itemView.findViewById(R.id.tvPoint)
    private val tvSTT: TextView = itemView.findViewById(R.id.tvSTT)

    @SuppressLint("SetTextI18n")
    fun bind(data: Rank, position: Int) {

        tvSTT.text =(position + 1).toString()
        Glide.with(itemView.context)
            .load(data.photo)
            .circleCrop()
            .error(R.drawable.test_avt)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(RequestOptions.circleCropTransform())
            .into(avatar)

        avatar.scaleType = ImageView.ScaleType.CENTER_CROP

        name.text = data.accountName
        point.text = data.rank.toString() + " Điểm"


    }


}