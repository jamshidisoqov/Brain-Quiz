package uz.gita.robo_brain.presentation.ui.sort_math.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.gita.robo_brain.databinding.ListItemSortedMathBinding
import uz.gita.robo_brain.repository.models.SortedMath

// Created by Jamshid Isoqov an 8/22/2022
class SortedAdapter : RecyclerView.Adapter<SortedAdapter.ViewHolder>() {

    var list: List<SortedMath> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<SortedMath>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ListItemSortedMathBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: SortedMath) {
            binding.tvSortedMathItem.text = data.question
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ListItemSortedMathBinding.inflate(LayoutInflater.from(parent.context))
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind(list[position])

    override fun getItemCount(): Int = 5

    fun onMove(from: Int, to: Int) {
        val oldData = list[from]
        val newList = ArrayList<SortedMath>()
        newList.removeAt(from)
        newList.add(to, oldData)
        list = newList
        notifyItemMoved(from, to)
    }

}