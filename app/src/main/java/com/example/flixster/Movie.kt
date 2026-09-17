package com.example.flixster

import org.json.JSONArray

data class Movie(
    val movieId: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val voteAverage: Double,
) {
    val posterImageUrl: String
        get() = "https://image.tmdb.org/t/p/w500$posterPath"

    val backdropImageUrl: String
        get() = "https://image.tmdb.org/t/p/w500$backdropPath"

    companion object {
        fun fromJsonArray(movieJsonArray: JSONArray): List<Movie> {
            val movies = mutableListOf<Movie>()
            for (i in 0 until movieJsonArray.length()) {
                val movieJson = movieJsonArray.getJSONObject(i)
                movies.add(
                    Movie(
                        movieId = movieJson.optInt("id", 0),
                        title = movieJson.optString("title", "No Title"),
                        overview = movieJson.optString("overview", "No Description"),
                        posterPath = movieJson.optString("poster_path", ""),
                        backdropPath = movieJson.optString("backdrop_path", ""),
                        voteAverage = movieJson.optDouble("vote_average", 0.0),
                    )
                )
            }
            return movies
        }
    }
}
