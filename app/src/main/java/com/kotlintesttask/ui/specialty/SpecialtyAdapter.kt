package com.kotlintesttask.ui.specialty

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlintesttask.R
import com.kotlintesttask.networking.model.SpecialtyModel
import kotlinx.android.synthetic.main.item_specialty.view.*

class SpecialtyAdapter(
    private val listener: OnSpecialtyClickListener
) : RecyclerView.Adapter<SpecialtyAdapter.SpecialtyViewHolder>() {

    interface OnSpecialtyClickListener {
        fun onSpecialtyClick(specialtyId: Int)
    }

    private val items: ArrayList<SpecialtyModel> = ArrayList()

    fun changeDataSet(videos: List<SpecialtyModel>, isClear: Boolean = true) {
        if (isClear)
            items.clear()

        items.addAll(videos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SpecialtyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_specialty, parent, false)
        return SpecialtyViewHolder(itemView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SpecialtyViewHolder, position: Int) {
        holder.bindSpecialty(items[position])
    }

    inner class SpecialtyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindSpecialty(specialty: SpecialtyModel) {
            with(itemView) {
                item_specialty_title_tv.text = specialty.name

                setOnClickListener {
                    listener.onSpecialtyClick(specialty.specialtyId)
                }
            }
        }

    }


}