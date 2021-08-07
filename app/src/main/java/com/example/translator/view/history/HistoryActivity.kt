package com.example.translator.view.history

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.translator.R
import com.example.translator.databinding.ActivityHistoryBinding
import com.example.translator.model.data.AppState
import com.example.translator.view.base.View
import org.koin.android.ext.android.get

class HistoryActivity : AppCompatActivity(), View {


    private var adapter: HistoryAdapter? = null
    val model: HistoryViewModel = get()
    private val observer = Observer<AppState> { renderData(it) }
    private var _binding: ActivityHistoryBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model.subscribe().observe(this, observer)
    }

    override fun onResume() {
        super.onResume()
        model.getData("", false)
    }
    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data
                if (dataModel == null || dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.error_empty_response))
                } else {
                    showViewSuccess()
                    if (adapter == null) {
                        binding.historyActivityRv.layoutManager =
                            LinearLayoutManager(applicationContext)
                        binding.historyActivityRv.adapter = HistoryAdapter(dataModel)
                    } else {
                        adapter!!.setData(dataModel)
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
            }
            is AppState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.errorTextView.text = error ?: getString(R.string.error_undefined)
        binding.reloadButton.setOnClickListener { model.getData("Hi", true) }
    }

    private fun showViewSuccess() {
        binding.successLinearLayout.visibility = VISIBLE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewLoading() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = VISIBLE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewError() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = VISIBLE
    }
}