package com.diegitsen.reignchallenge.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.diegitsen.reignchallenge.R
import com.diegitsen.reignchallenge.data.entity.Hit
import com.diegitsen.reignchallenge.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment(), HitAdapter.HitItemClickListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding
    private val hitAdapter = HitAdapter(arrayListOf(), this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        binding.executePendingBindings()
        binding.rvHits.layoutManager = LinearLayoutManager(context)
        binding.rvHits.adapter = hitAdapter
        binding.rvHits.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
        viewModel.getHits()
        viewModel.mHits.observe(viewLifecycleOwner, Observer {
            hitAdapter.replaceData(it)
        })

        swipeRefresh.setOnRefreshListener {
            viewModel.getHits()
            swipeRefresh.isRefreshing = false
        }
    }



    override fun onHitItemClicked(storyUrl: String?) {
        val action = MainFragmentDirections.actionFirstFragmentToSecondFragment()
        findNavController().navigate(action)
    }
}
