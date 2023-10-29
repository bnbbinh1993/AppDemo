package vn.bn.teams.appdemo.core.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView


abstract class BaseViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

    abstract fun bind(position: Int)
}