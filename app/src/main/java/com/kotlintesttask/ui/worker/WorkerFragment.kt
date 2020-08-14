package com.kotlintesttask.ui.worker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlintesttask.R
import com.kotlintesttask.extensions.showLoading
import com.kotlintesttask.networking.model.WorkerModel
import com.kotlintesttask.ui.worker.details.WorkerDetailsFragment

class WorkerFragment : Fragment(), WorkerAdapter.OnWorkerClickListener {

    companion object {
        const val ARG_SPECIALTY_ID = "ARG_SPECIALTY_ID"
    }

    private val viewModel: WorkerViewModel by viewModels()
    private var specialtyId: Int? = null
    private lateinit var mAdapter: WorkerAdapter

    private lateinit var mWorkerRv: RecyclerView
    private lateinit var mLoadingPb: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            specialtyId = it.getInt(ARG_SPECIALTY_ID, 0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mAdapter = WorkerAdapter(this)
        val root = inflater.inflate(R.layout.fragment_worker, container, false)
        with(root) {
            mWorkerRv = findViewById<RecyclerView>(R.id.workers_rv).apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = mAdapter
            }
            mLoadingPb = findViewById(R.id.loading_pb)
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = resources.getString(R.string.title_workers_list)
        }

        mLoadingPb.showLoading(true)
        viewModel.getResponse().observe(viewLifecycleOwner, Observer { resp ->
            mLoadingPb.showLoading(false)
            if (resp != null) {
                val workersBySpecialty: ArrayList<WorkerModel> = arrayListOf()
                for (worker in resp.response) {
                    for (specialty in worker.specialty) {
                        if (specialty.specialtyId == specialtyId) {
                            workersBySpecialty.add(worker)
                        }
                    }
                }
                displayWorkers(workersBySpecialty)
            }
        })
    }

    private fun displayWorkers(items: List<WorkerModel>) {
        mAdapter.changeDataSet(items)
    }

    override fun onWorkerClick(worker: WorkerModel) {
        val bundle = bundleOf(WorkerDetailsFragment.ARG_WORKER to worker)
        Navigation.findNavController(requireView())
            .navigate(R.id.action_navigation_workers_to_navigation_worker_details, bundle)
    }


}