package uz.uni_team.robo_brain.presentation.ui.sort_math.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.uni_team.robo_brain.R
import uz.uni_team.robo_brain.databinding.ListItemSortedMathBinding
import uz.uni_team.robo_brain.repository.models.SortedMath

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
        ListItemSortedMathBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.list_item_sorted_math,parent,false))
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind(list[position])

    override fun getItemCount(): Int = list.size

    fun onMove(from: Int, to: Int) {
        val newList = ArrayList<SortedMath>()
        newList.addAll(list)
        val oldData = newList.removeAt(from)
        newList.add(to, oldData)
        list = newList
        notifyItemMoved(from, to)
    }

}