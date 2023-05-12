package com.example.challengechapter5.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challengechapter5.model.*
import com.example.challengechapter5.networking.ApiService
import com.example.challengechapter5.networking.RetrofitClient
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MovieViewModel():ViewModel() {
    private val _popularMovies = MutableLiveData<List<ResultPopular>>()
    val popularMovies : LiveData<List<ResultPopular>> = _popularMovies

    private val _movieDetail = MutableLiveData<MoviesDetailResponse>()
    val movieDetail : LiveData<MoviesDetailResponse> = _movieDetail

    private val _movieTopRated = MutableLiveData<List<ResultPopular>>()
    val movieTopRated : LiveData<List<ResultPopular>> = _movieTopRated

    private val _upcomingMovies = MutableLiveData<List<ResultPopular>>()
    val upcomingMovies : LiveData<List<ResultPopular>> = _upcomingMovies

    private val _popularTvSeries = MutableLiveData<List<ResultPopularTvSeries>>()
    val popularTvSeries : LiveData<List<ResultPopularTvSeries>> = _popularTvSeries

    private val _onAirTvSeries = MutableLiveData<List<ResultPopularTvSeries>>()
    val trendingTv : LiveData<List<ResultPopularTvSeries>> = _onAirTvSeries

    private val _tvSeriesDetail = MutableLiveData<TvSeriesDetailResponse>()
    val tvSeriesDetail : LiveData<TvSeriesDetailResponse> = _tvSeriesDetail


    fun callGetPopularMovies(){
        RetrofitClient.RetrofitClient.instance.getPopularMovies().enqueue(object :retrofit2.Callback<PopularMoviesResponse>{
            override fun onResponse(
                call: Call<PopularMoviesResponse>,
                response: Response<PopularMoviesResponse>
            ) {
                if (response.isSuccessful){
                    val data = response.body()
                    if (data!= null){
                        _popularMovies.postValue(data.results)
                    }else{
                        Log.e("Error : ", "onFailure : ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<PopularMoviesResponse>, t: Throwable) {
                Log.d("Error : ", "onFailure : ${t.message}")
            }

        })
    }

    fun callGetTopRatedMovies(){
        RetrofitClient.RetrofitClient.instance.getTopRatedMovies().enqueue(object : retrofit2.Callback<PopularMoviesResponse>{
            override fun onResponse(
                call: Call<PopularMoviesResponse>,
                response: Response<PopularMoviesResponse>
            ) {
                if (response.isSuccessful){
                    val data = response.body()
                    if (data!= null){
                        _movieTopRated.postValue(data.results)
                    }else{
                        Log.e("Error : ", "onFailure : ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<PopularMoviesResponse>, t: Throwable) {
                Log.e("Error : ", "onFailure : ${t.message}")
            }

        })
    }

    fun callGetUpcomingMovies(){
        RetrofitClient.RetrofitClient.instance.getUpcomingMovies().enqueue(object : retrofit2.Callback<PopularMoviesResponse>{
            override fun onResponse(
                call: Call<PopularMoviesResponse>,
                response: Response<PopularMoviesResponse>
            ) {
                if (response.isSuccessful){
                    val data = response.body()
                    if (data!= null){
                        _upcomingMovies.postValue(data.results)
                    }else{
                        Log.e("Error : ", "onFailure : ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<PopularMoviesResponse>, t: Throwable) {
                Log.e("Error : ", "onFailure : ${t.message}")
            }

        })
    }

    fun callGetDetailMovie(movie_id:Int){
        RetrofitClient.RetrofitClient.instance.getMovieDetail(movie_id).enqueue(object : retrofit2.Callback<MoviesDetailResponse>{
            override fun onResponse(
                call: Call<MoviesDetailResponse>,
                response: Response<MoviesDetailResponse>
            ) {
                if (response.isSuccessful){
                    val data = response.body()
                    if (data!= null){
                        _movieDetail.postValue(data!!)
                    }else{
                        Log.e("Error : ", "onFailure : ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<MoviesDetailResponse>, t: Throwable) {
                Log.e("Error : ", "onFailure : ${t.message}")
            }

        })
    }

    fun callGetTvSeriesPopular(){
        RetrofitClient.RetrofitClient.instance.getTvPopular().enqueue(object : retrofit2.Callback<PopularTvSeriesResponse>{
            override fun onResponse(
                call: Call<PopularTvSeriesResponse>,
                response: Response<PopularTvSeriesResponse>
            ) {
                if (response.isSuccessful){
                    val data = response.body()
                    if (data!= null){
                        _popularTvSeries.postValue(data.results)
                    }else{
                        Log.e("Error : ", "onFailure : ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<PopularTvSeriesResponse>, t: Throwable) {
                Log.e("Error : ", "onFailure : ${t.message}")
            }

        })
    }

    fun callGetTvSeriesOnAir(){
        RetrofitClient.RetrofitClient.instance.getTvOnTheAir().enqueue(object : retrofit2.Callback<PopularTvSeriesResponse>{
            override fun onResponse(
                call: Call<PopularTvSeriesResponse>,
                response: Response<PopularTvSeriesResponse>
            ) {
                if (response.isSuccessful){
                    val data = response.body()
                    if (data!= null){
                        _onAirTvSeries.postValue(data.results)
                    }else{
                        Log.e("Error : ", "onFailure : ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<PopularTvSeriesResponse>, t: Throwable) {
                Log.e("Error : ", "onFailure : ${t.message}")
            }

        })
    }

    fun callGetTvSeriesDetail(series_id:Int){
        RetrofitClient.RetrofitClient.instance.getTvSeriesDetail(series_id).enqueue(object :retrofit2.Callback<TvSeriesDetailResponse>{
            override fun onResponse(
                call: Call<TvSeriesDetailResponse>,
                response: Response<TvSeriesDetailResponse>
            ) {
                if (response.isSuccessful){
                    val data = response.body()
                    if (data!= null){
                        _tvSeriesDetail.postValue(data!!)
                    }else{
                        Log.e("Error : ", "onFailure : ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<TvSeriesDetailResponse>, t: Throwable) {
                Log.e("Error : ", "onFailure : ${t.message}")
            }

        })
    }
}