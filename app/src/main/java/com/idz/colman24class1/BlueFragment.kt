package com.idz.colman24class1

import android.content.Context
import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.idz.colman24class1.adapter.MoviesAdapter
import com.idz.colman24class1.databinding.FragmentBlueBinding
import com.idz.colman24class1.model.Movies

class BlueFragment : Fragment() {
    private val viewModel: BlueViewModel by viewModels()
    private var _binding: FragmentBlueBinding? = null
    private val binding get() = _binding
    private var adapter: MoviesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBlueBinding.inflate(inflater, container, false)

        binding?.recyclerView?.setHasFixedSize(true)
        binding?.recyclerView?.layoutManager = GridLayoutManager(context, 3)

        adapter = MoviesAdapter(null)
        binding?.recyclerView?.adapter = adapter

        viewModel.fetchMovies()
        viewModel.movies.observe(viewLifecycleOwner) {
            adapter?.movies = it
            adapter?.notifyDataSetChanged()
        }

        return _binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}