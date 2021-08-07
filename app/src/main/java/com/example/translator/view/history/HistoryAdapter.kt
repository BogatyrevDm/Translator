package com.example.translator.view.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.translator.databinding.ActivityHistoryItemBinding
import com.example.translator.model.data.DataModel

class HistoryAdapter(private var data: List<DataModel>) :
    RecyclerView.Adapter<HistoryAdapter.RecyclerItemViewHolder>() {

    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            ActivityHistoryItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data.get(position))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(val view: ActivityHistoryItemBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(data: DataModel) {
            view.wordTextviewRv.text = data.text
        }
    }
}