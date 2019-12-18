package com.shevchenko.space_x.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.shevchenko.space_x.R
import com.shevchenko.space_x.data.RocketModel
import com.shevchenko.space_x.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.progress
import kotlinx.android.synthetic.main.activity_main.recyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val rocketsViewModel: RocketsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.shevchenko.space_x.R.layout.activity_main)

        val adapter = RocketsAdapter(emptyList(), onItemClicked())
        recyclerView.adapter = adapter

        rocketsViewModel.state.observe(this, Observer {
            when (it) {
                is RocketsListState.SuccessState -> {
                    progress.visibility = View.GONE
                    adapter.list = it.itemList
                    adapter.notifyDataSetChanged()
                }
                is RocketsListState.ErrorState -> {
                    showWelcomeDialog(getString(R.string.error))
                    progress.visibility = View.GONE
                }
                is RocketsListState.LoadingState -> {
                    progress.visibility = View.VISIBLE
                }
                is RocketsListState.ShowDialogState -> showWelcomeDialog(getString(R.string.welcome))
                is RocketsListState.ShowDetail -> {
                    startActivity(
                        DetailActivity.createIntent(this, it.rocket, ArrayList(it.launchList))
                    )
                }
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_filter -> {
                if (item.isChecked) {
                    item.title = getString(R.string.active)
                    item.isChecked = false
                } else {
                    item.title = getString(R.string.all)
                    item.isChecked = true
                }
                rocketsViewModel.showFavorites()
                return true
            }
            R.id.action_refresh -> {
                progress.visibility = View.VISIBLE
                rocketsViewModel.updateDate()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun onItemClicked(): (RocketModel) -> Unit {
        return rocketsViewModel::getLaunch
    }

    private fun showWelcomeDialog(text: String) {
        val builder = AlertDialog.Builder(this)
        with(builder)
        {
            setTitle(text)
            setPositiveButton(
                getString(R.string.ok)
            ) { dialog, _ -> dialog.cancel() }
            show()
        }
    }
}