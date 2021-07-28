package com.example.translator.view.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.translator.databinding.ActivityMainItemBinding
import com.example.translator.model.data.DataModel

class MainAdapter(private var data: List<DataModel>) :
    RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {

    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            ActivityMainItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data.get(position))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(val view: ActivityMainItemBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(data: DataModel) {
            view.headerTextviewRv.text = data.text
            view.descriptionTextviewRv.text = data.meanings?.get(0)?.translation?.translation
            view.transcriptionTextviewRv.text = data.meanings?.get(0)?.transcription
        }
    }
}