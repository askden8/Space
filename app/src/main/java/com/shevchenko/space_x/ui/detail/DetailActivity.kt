package com.shevchenko.space_x.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.shevchenko.space_x.R
import com.shevchenko.space_x.data.LaunchModel
import com.shevchenko.space_x.data.RocketModel
import kotlinx.android.synthetic.main.activity_detail.description
import kotlinx.android.synthetic.main.activity_detail.graph
import kotlinx.android.synthetic.main.activity_detail.launchList
import com.jjoe64.graphview.DefaultLabelFormatter
import java.text.NumberFormat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_detail.listTitle

class DetailActivity : AppCompatActivity() {

    private val listItem = mutableListOf<Item>()
    private val rocket: RocketModel by lazy {
        intent.getSerializableExtra(
            SELECTED_ROCKET
        ) as RocketModel
    }
    private val launches by lazy {
        intent.getParcelableArrayListExtra<LaunchModel>(
            SELECTED_LAUNCHES
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        description.text = rocket.description
        if (launches.isNullOrEmpty()) {
            listTitle.visibility = View.GONE
            return
        }

        launches.sortBy { it.launchYear }

        val map = mutableMapOf<Double, Double>()
        var currentHeader = ""
        launches.forEach {
            val currentYear = it.launchYear
            if (currentHeader != currentYear) {
                listItem += HeaderItem(currentYear)
                currentHeader = currentYear
            }
            listItem += LaunchItem(it)
            val year = currentYear.toDouble()
            val value: Double = map[year] ?: 0.0
            map[year] = value.plus(1)
        }

        initGraph(map)

        initList(listItem)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    private fun initList(listItem: List<Item>) {
        val adapter = LaunchesAdapter(listItem)
        launchList.adapter = adapter
        launchList.addItemDecoration(StickHeaderItemDecoration(adapter))
        launchList.layoutManager = LinearLayoutManager(this)
    }

    private fun initGraph(map: MutableMap<Double, Double>) {
        var graphData = emptyArray<DataPoint>()
        map.forEach { graphData += DataPoint(it.key, it.value) }

        val series = LineGraphSeries(graphData)
        graph.gridLabelRenderer.numHorizontalLabels = graphData.size

        graph.addSeries(series)
        graph.visibility = View.VISIBLE
        val nf = NumberFormat.getInstance()
        nf.isGroupingUsed = false
        graph.gridLabelRenderer.labelFormatter = DefaultLabelFormatter(nf, nf)
    }

    companion object {
        private const val SELECTED_ROCKET = "rocket"
        private const val SELECTED_LAUNCHES = "launches"

        fun createIntent(context: Context, model: RocketModel, list: ArrayList<LaunchModel>) =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(SELECTED_ROCKET, model)
                putParcelableArrayListExtra(SELECTED_LAUNCHES, list)
                flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
    }
}
