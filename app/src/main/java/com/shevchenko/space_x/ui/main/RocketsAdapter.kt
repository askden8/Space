package com.shevchenko.space_x.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shevchenko.space_x.R
import com.shevchenko.space_x.data.RocketModel
import kotlinx.android.synthetic.main.list_item_rocket.view.country
import kotlinx.android.synthetic.main.list_item_rocket.view.engines
import kotlinx.android.synthetic.main.list_item_rocket.view.holder
import kotlinx.android.synthetic.main.list_item_rocket.view.name

class RocketsAdapter(
    var list: List<RocketModel>,
    private val onClick: (RocketModel) -> Unit) :
    RecyclerView.Adapter<RocketsAdapter.RocketHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_rocket, parent, false)
        return RocketHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RocketHolder, position: Int) {
        holder.display(list[position], onClick)
    }

    inner class RocketHolder(item: View) : RecyclerView.ViewHolder(item) {

        fun display(model: RocketModel, onClick: (RocketModel) -> Unit) {
            itemView.holder.setOnClickListener { onClick(model) }
            itemView.name.text = itemView.context.getString(R.string.name, model.rocketId)
            itemView.country.text = itemView.context.getString(R.string.country, model.country)
            itemView.engines.text =
                itemView.context.getString(R.string.engines, model.engines?.number.toString())
        }
    }
}
