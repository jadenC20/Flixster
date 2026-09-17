package com.example.flixster

import android.content.Context
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.Locale

class MovieAdapter(
    private val context: Context,
    private val movies: List<Movie>,
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movies.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvOverview: TextView = itemView.findViewById(R.id.tvOverview)
        private val tvRating: TextView = itemView.findViewById(R.id.tvRating)
        private val ivPoster: ImageView = itemView.findViewById(R.id.ivPoster)

        fun bind(movie: Movie) {
            tvTitle.text = movie.title
            tvOverview.text = movie.overview
            tvRating.text = String.format(Locale.US, "⭐ %.1f / 10", movie.voteAverage)

            val isLandscape = context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

            val imageUrl = if (isLandscape) {
                movie.backdropImageUrl
            } else {
                movie.posterImageUrl
            }

            // Adjust image view dimensions for landscape vs portrait
            val layoutParams = ivPoster.layoutParams
            if (isLandscape) {
                layoutParams.width = (180 * context.resources.displayMetrics.density).toInt()
                layoutParams.height = (110 * context.resources.displayMetrics.density).toInt()
            } else {
                layoutParams.width = (100 * context.resources.displayMetrics.density).toInt()
                layoutParams.height = (150 * context.resources.displayMetrics.density).toInt()
            }
            ivPoster.layoutParams = layoutParams

            // Glide image loading with placeholder
            Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(ivPoster)
        }
    }
}
