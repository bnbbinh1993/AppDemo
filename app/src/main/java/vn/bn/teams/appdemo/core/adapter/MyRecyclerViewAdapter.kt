package vn.bn.teams.appdemo.core.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import vn.bn.teams.appdemo.core.activities.SwipeReadActivity

import vn.bn.teams.appdemo.R
import vn.bn.teams.appdemo.data.models.DataAlphabetFollow
import kotlin.collections.ArrayList

class MyRecyclerViewAdapter(var context: Context, var arrayList: ArrayList<DataAlphabetFollow>, var mContext: SwipeReadActivity):
    RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemHolder = LayoutInflater.from(parent.context).inflate(R.layout.item_swipe_read_tow, parent, false)
        return ViewHolder(itemHolder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataAlphabetFollow: DataAlphabetFollow = arrayList[position]
        getImageId(dataAlphabetFollow.image, context)?.let { holder.image.setImageResource(it)
        holder.itemView.setOnClickListener {
             mContext.binding.vpswipe.currentItem = position
         }
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var image = itemView.findViewById<ImageView>(R.id.alpha1)
    }
    private fun getImageId(name: String?, context: Context): Int? {
        return if (name == null) {
            null
        } else {
            context.resources.getIdentifier(name, "drawable", context.packageName)
        }
    }

}