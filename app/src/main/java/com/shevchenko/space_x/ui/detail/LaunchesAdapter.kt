package com.shevchenko.space_x.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shevchenko.space_x.R
import com.shevchenko.space_x.data.LaunchModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.header_item_recycler.view.title
import kotlinx.android.synthetic.main.list_item_launch.view.data
import kotlinx.android.synthetic.main.list_item_launch.view.name
import kotlinx.android.synthetic.main.list_item_launch.view.patch
import kotlinx.android.synthetic.main.list_item_launch.view.success

class LaunchesAdapter(
    private var list: List<Item>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    StickHeaderItemDecoration.StickyHeaderInterface {
    override fun bindHeaderData(header: View, headerPosition: Int) {
        header.findViewById<TextView>(R.id.title).text = (list[headerPosition] as? HeaderItem)?.date
    }

    override fun getHeaderPositionForItem(position: Int): Int =
    (position downTo 0)
    .map { Pair(isHeader(it), it) }
    .firstOrNull { it.first }?.second ?: RecyclerView.NO_POSITION

    override fun getHeaderLayout(headerPosition: Int): Int {
        return R.layout.header_item_recycler
    }

    override fun isHeader(itemPosition: Int): Boolean {
        return list[itemPosition] is HeaderItem
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position] is LaunchItem) return 999 else 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 999) {
            LaunchHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_launch, parent, false)
            )
        } else {
            TitleHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.header_item_recycler, parent, false)
            )
        }
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is LaunchHolder -> (list[position] as? LaunchItem)?.launchModel?.let { holder.display(it) }
            is TitleHolder -> (list[position] as? HeaderItem)?.date?.let { holder.display(it) }
            else -> throw IllegalArgumentException("Unknown holder type $holder")
        }
    }

    inner class LaunchHolder(item: View) : RecyclerView.ViewHolder(item) {

        fun display(model: LaunchModel) {
            itemView.name.text = itemView.context.getString(R.string.mission, model.missionName)
            itemView.data.text =
                itemView.context.getString(R.string.date, model.launchDateUtc?.take(10))
            itemView.success.text = itemView.context.getString(
                if (model.launchSuccess == true) R.string.success else
                    R.string.notSuccess
            )
            Picasso.get().load(model.links?.missionPatch).resize(150, 150)
                .centerCrop().into(itemView.patch)
        }
    }

    inner class TitleHolder(item: View) : RecyclerView.ViewHolder(item) {

        fun display(title: String) {
            itemView.title.text = title
        }
    }
}
