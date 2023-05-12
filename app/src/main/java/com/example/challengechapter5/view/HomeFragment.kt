package com.example.challengechapter5.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengechapter5.R
import com.example.challengechapter5.databinding.FragmentHomeBinding
import com.example.challengechapter5.view.adapter.MovieAdapter
import com.example.challengechapter5.viewmodel.MovieViewModel
import com.example.challengechapter5.viewmodel.UserViewModel


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val movieViewModel:MovieViewModel by viewModels()
    private val userViewModel:UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel.getUsername()
        userViewModel.username.observe(viewLifecycleOwner){
            binding.tvHome.text = "Hello, Welcome!, $it"
        }

        showDataPopularMovie()
        showdataTopRatedMovies()
        showDataUpcomingMovies()

        binding.ivProfile.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }
    }

    fun showDataPopularMovie(){
        movieViewModel.callGetPopularMovies()
        movieViewModel.popularMovies.observe(viewLifecycleOwner, Observer {
            if (it != null){
                binding.rvPopularMovies.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                binding.rvPopularMovies.adapter = MovieAdapter(it)
            }
            else{
                Toast.makeText(context, "null", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun showdataTopRatedMovies(){
        movieViewModel.callGetTopRatedMovies()
        movieViewModel.movieTopRated.observe(viewLifecycleOwner, Observer {
            if (it != null){
                binding.rvTopRatedMovies.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                binding.rvTopRatedMovies.adapter = MovieAdapter(it)
            }
        })
    }

    fun showDataUpcomingMovies(){
        movieViewModel.callGetUpcomingMovies()
        movieViewModel.upcomingMovies.observe(viewLifecycleOwner, Observer {
            if (it != null){
                binding.rvUpcomingMovies.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                binding.rvUpcomingMovies.adapter = MovieAdapter(it)
            }
        })
    }

}