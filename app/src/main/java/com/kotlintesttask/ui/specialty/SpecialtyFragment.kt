package com.kotlintesttask.ui.specialty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlintesttask.R
import com.kotlintesttask.extensions.showLoading
import com.kotlintesttask.networking.model.SpecialtyModel
import com.kotlintesttask.ui.worker.WorkerFragment

class SpecialtyFragment : Fragment(), SpecialtyAdapter.OnSpecialtyClickListener {

    private val viewModel: SpecialtyViewModel by viewModels()

    private lateinit var mSpecialtiesRv: RecyclerView
    private lateinit var mLoadingPb: ProgressBar
    private lateinit var mAdapter: SpecialtyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mAdapter = SpecialtyAdapter(this)
        val root = inflater.inflate(R.layout.fragment_specialty, container, false)
        with(root) {
            mSpecialtiesRv = findViewById<RecyclerView>(R.id.specialties_rv).apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = mAdapter
            }
            mLoadingPb = findViewById(R.id.loading_pb)
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mLoadingPb.showLoading(true)
        viewModel.getResponse().observe(viewLifecycleOwner, Observer { resp ->
            mLoadingPb.showLoading(false)
            if (resp != null) {
                val specialties = resp.response.flatMap {
                    it.specialty
                }.distinct()

                displaySpecialties(specialties)
            }
        })
    }

    private fun displaySpecialties(items: List<SpecialtyModel>) {
        mAdapter.changeDataSet(items)
    }

    override fun onSpecialtyClick(specialtyId: Int) {
        val bundle = bundleOf(WorkerFragment.ARG_SPECIALTY_ID to specialtyId)
        Navigation.findNavController(requireView())
            .navigate(R.id.action_navigation_specialties_to_navigation_workers, bundle)
    }

}