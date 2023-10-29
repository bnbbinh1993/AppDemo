package vn.bn.teams.appdemo.core.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import vn.bn.teams.appdemo.R
import vn.bn.teams.appdemo.data.models.DataAlphabetFollow


class SwipeReadAdapter(var context: Context, var list: ArrayList<DataAlphabetFollow>): PagerAdapter() {

    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as View
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.item_swipe_read,container,false)
        val imgSwipeRead: ImageView = view.findViewById(R.id.img_optionA)

        getImageId(list[position].image, context)?.let { imgSwipeRead.setImageResource(it) }
        container.addView(view)
        return view
    }
    override fun destroyItem(parent: ViewGroup, position: Int, `object`: Any) {
        // Remove the view from view group specified position
        parent.removeView(`object` as View)
    }
    private fun getImageId(name: String?, context: Context): Int? {
        return if (name == null) {
            null
        } else {
            context.resources.getIdentifier(name, "drawable", context.packageName)
        }
    }
}