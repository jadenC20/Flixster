package com.example.flixster

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

private const val TAG = "MainActivity"
private const val POPULAR_URL =
    "https://api.themoviedb.org/3/movie/popular?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"
private const val TOP_RATED_URL =
    "https://api.themoviedb.org/3/movie/top_rated?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"

class MainActivity : AppCompatActivity() {

    private val movies = mutableListOf<Movie>()
    private lateinit var rvMovies: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var btnPopular: Button
    private lateinit var btnTopRated: Button
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMovies = findViewById(R.id.rvMovies)
        progressBar = findViewById(R.id.progressBar)
        btnPopular = findViewById(R.id.btnPopular)
        btnTopRated = findViewById(R.id.btnTopRated)

        movieAdapter = MovieAdapter(this, movies)
        rvMovies.adapter = movieAdapter
        rvMovies.layoutManager = LinearLayoutManager(this)

        btnPopular.setOnClickListener {
            updateButtonSelection(btnPopular, btnTopRated)
            fetchMovies(POPULAR_URL)
        }

        btnTopRated.setOnClickListener {
            updateButtonSelection(btnTopRated, btnPopular)
            fetchMovies(TOP_RATED_URL)
        }

        // Default to popular movies endpoint
        updateButtonSelection(btnPopular, btnTopRated)
        fetchMovies(POPULAR_URL)
    }

    private fun updateButtonSelection(active: Button, inactive: Button) {
        active.setBackgroundColor(Color.parseColor("#FF9800"))
        active.setTextColor(Color.WHITE)
        inactive.setBackgroundColor(Color.parseColor("#333333"))
        inactive.setTextColor(Color.parseColor("#B0BEC5"))
    }

    private fun fetchMovies(url: String) {
        progressBar.visibility = View.VISIBLE
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(
            object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e(TAG, "onFailure: Failed to fetch movies", e)
                    runOnUiThread {
                        progressBar.visibility = View.GONE
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    val responseBody = response.body?.string()
                    runOnUiThread {
                        progressBar.visibility = View.GONE
                        if (response.isSuccessful && responseBody != null) {
                            try {
                                val jsonObject = JSONObject(responseBody)
                                val movieJsonArray = jsonObject.getJSONArray("results")
                                movies.clear()
                                val newMovies = Movie.fromJsonArray(movieJsonArray)
                                movies.addAll(newMovies)
                                movieAdapter.notifyDataSetChanged()
                                Log.d(TAG, "Loaded movies count: ${movies.size}")
                            } catch (e: JSONException) {
                                Log.e(TAG, "Hit json exception", e)
                            }
                        } else {
                            Log.e(TAG, "Response failed: code ${response.code}")
                        }
                    }
                }
            },
        )
    }
}
