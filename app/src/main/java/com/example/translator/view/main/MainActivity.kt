package com.example.translator.view.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.BaseActivity
import com.example.historyscreen.view.history.HistoryActivity
import com.example.translator.R
import com.example.translator.databinding.ActivityMainBinding
import com.example.translator.model.data.AppState
import com.example.translator.model.data.DataModel
import com.example.translator.view.descriptionscreen.DescriptionActivity
import com.example.translator.view.main.adapter.MainAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<AppState>() {

    private var adapter: MainAdapter? = null
    override val model: MainViewModel by viewModel()
    private val observer = Observer<AppState> { renderData(it) }
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                startActivity(
                    DescriptionActivity.getIntent(
                        this@MainActivity,
                        data.text!!,
                        data.meanings!![0].translation?.translation.toString(),
                        data.meanings!![0].imageUrl
                    )
                )
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchButton.setOnClickListener {
            model.getData(binding.searchEditText.text.toString(), true)
        }
        model.subscribe().observe(this, observer)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.history_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.menu_history -> {
                startActivity(Intent(this, HistoryActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showErrorScreen(error: String?) {
        showViewError()
        binding.errorTextView.text = error ?: getString(R.string.error_undefined)
        binding.reloadButton.setOnClickListener { model.getData("Hi", true) }
    }

    override fun showViewSuccess() {
        binding.successLinearLayout.visibility = VISIBLE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = GONE
    }

    override fun showViewLoading() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = VISIBLE
        binding.errorLinearLayout.visibility = GONE
    }

    override fun showViewError() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = VISIBLE
    }

    override fun onLoadingSuccess(appState: AppState.Success) {
        if (adapter == null) {
            binding.mainActivityRv.layoutManager =
                LinearLayoutManager(applicationContext)
            binding.mainActivityRv.adapter = MainAdapter(appState.data!!,onListItemClickListener)
        } else {
            adapter!!.setData(appState.data!!)
        }
    }
}