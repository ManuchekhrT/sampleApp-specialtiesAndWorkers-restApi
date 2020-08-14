package com.kotlintesttask.ui.worker.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kotlintesttask.R
import com.kotlintesttask.extensions.*
import com.kotlintesttask.networking.model.WorkerModel
import kotlinx.android.synthetic.main.item_worker.view.*

class WorkerDetailsFragment : Fragment() {

    companion object {
        const val ARG_WORKER = "ARG_WORKER"
    }

    private var mWorker: WorkerModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().apply {
            mWorker = getSerializable(ARG_WORKER) as WorkerModel
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_worker_details, container, false)
        with(root) {
            findViewById<TextView>(R.id.worker_full_name_tv).apply {
                text =
                    mWorker?.firstName?.capitalizeFirstLetter() + " " + mWorker?.lastName?.capitalizeFirstLetter()
            }

            findViewById<TextView>(R.id.worker_age_tv).apply {
                when {
                    mWorker?.birthday.isNullOrBlank() -> this.text = "««"
                    isValidFormat(PATTERN_DD_MM_YYYY, mWorker?.birthday!!) -> this.text =
                        "Возраст: " + getAge(
                            simpleDateFormat(
                                PATTERN_DD_MM_YYYY, mWorker?.birthday!!
                            )
                        )
                    isValidFormat(PATTERN_YYYY_MM_DD, mWorker?.birthday!!) -> this.text =
                        "Возраст: " + getAge(
                            simpleDateFormat(
                                PATTERN_YYYY_MM_DD, mWorker?.birthday!!
                            )
                        )
                }
            }

            findViewById<TextView>(R.id.worker_birthday_tv).apply {
                when {
                    mWorker?.birthday.isNullOrBlank() -> this.text = "««"
                    isValidFormat(PATTERN_DD_MM_YYYY, mWorker?.birthday!!) -> this.text =
                        simpleDateFormat(
                            PATTERN_DD_MM_YYYY, mWorker?.birthday!!
                        ) + " г."

                    isValidFormat(PATTERN_YYYY_MM_DD, mWorker?.birthday!!) -> this.text =
                        simpleDateFormat(
                            PATTERN_YYYY_MM_DD, mWorker?.birthday!!
                        ) + " г."

                }
            }

            findViewById<TextView>(R.id.worker_specialty_tv).apply {
                for (specialty in mWorker!!.specialty) this.text.apply {
                    append("\n" + specialty.name + "\n")
                }
            }
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = resources.getString(R.string.title_worker)
        }
    }

}