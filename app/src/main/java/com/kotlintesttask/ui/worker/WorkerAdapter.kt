package com.kotlintesttask.ui.worker

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlintesttask.R
import com.kotlintesttask.extensions.*
import com.kotlintesttask.networking.model.WorkerModel
import kotlinx.android.synthetic.main.item_worker.view.*

class WorkerAdapter(
    private val listener: OnWorkerClickListener
) : RecyclerView.Adapter<WorkerAdapter.WorkerViewHolder>() {

    interface OnWorkerClickListener {
        fun onWorkerClick(worker: WorkerModel)
    }

    private val items: ArrayList<WorkerModel> = ArrayList()

    fun changeDataSet(videos: List<WorkerModel>, isClear: Boolean = true) {
        if (isClear)
            items.clear()

        items.addAll(videos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WorkerViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_worker, parent, false)
        return WorkerViewHolder(itemView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: WorkerViewHolder, position: Int) {
        holder.bindWorker(items[position])
    }

    inner class WorkerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bindWorker(worker: WorkerModel) {
            with(itemView) {
                item_worker_full_name.text =
                    "${worker.firstName.capitalizeFirstLetter()} ${worker.lastName.capitalizeFirstLetter()} "

                val birthday = worker.birthday
                when {
                    birthday.isNullOrBlank() -> item_worker_age.text = "««"
                    isValidFormat(PATTERN_DD_MM_YYYY, birthday) -> item_worker_age.text =
                        "Возраст: " + getAge(
                            simpleDateFormat(
                                PATTERN_DD_MM_YYYY, birthday
                            )
                        )
                    isValidFormat(PATTERN_YYYY_MM_DD, birthday) -> item_worker_age.text =
                        "Возраст: " + getAge(
                            simpleDateFormat(
                                PATTERN_YYYY_MM_DD, birthday
                            )
                        )
                }

                setOnClickListener {
                    listener.onWorkerClick(worker)
                }
            }
        }

    }


}