package uz.uni_team.robo_brain.presentation.ui.help

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.uni_team.robo_brain.R
import uz.uni_team.robo_brain.databinding.ListItemHelpBinding
import uz.uni_team.robo_brain.repository.models.HelpData

// Created by Jamshid Isoqov an 9/4/2022
class HelpAdapter : RecyclerView.Adapter<HelpAdapter.ViewHolder>() {

    private var helpList: List<HelpData> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<HelpData>) {
        helpList = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ListItemHelpBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
            val data = helpList[absoluteAdapterPosition]
            binding.apply {
                tvTitle.text = data.title
                tvDescription.text = data.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ListItemHelpBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_help, parent, false)
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()

    override fun getItemCount(): Int = helpList.size

}