package com.example.flixster

import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import java.util.Locale

class DetailActivity : AppCompatActivity() {

    private lateinit var ivBackdropDetail: ImageView
    private lateinit var ivPosterDetail: ImageView
    private lateinit var tvTitleDetail: TextView
    private lateinit var tvReleaseDateDetail: TextView
    private lateinit var tvRatingDetail: TextView
    private lateinit var tvOverviewDetail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        ivBackdropDetail = findViewById(R.id.ivBackdropDetail)
        ivPosterDetail = findViewById(R.id.ivPosterDetail)
        tvTitleDetail = findViewById(R.id.tvTitleDetail)
        tvReleaseDateDetail = findViewById(R.id.tvReleaseDateDetail)
        tvRatingDetail = findViewById(R.id.tvRatingDetail)
        tvOverviewDetail = findViewById(R.id.tvOverviewDetail)

        @Suppress("DEPRECATION")
        val movie = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(MOVIE_EXTRA, Movie::class.java)
        } else {
            intent.getSerializableExtra(MOVIE_EXTRA) as? Movie
        }

        if (movie != null) {
            title = movie.title
            tvTitleDetail.text = movie.title
            tvOverviewDetail.text = movie.overview
            tvReleaseDateDetail.text = String.format("Release Date: %s", movie.releaseDate)
            tvRatingDetail.text = String.format(
                Locale.US,
                "⭐ %.1f / 10 (%d votes)",
                movie.voteAverage,
                movie.voteCount,
            )

            // Load Backdrop
            Glide.with(this)
                .load(movie.backdropImageUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(ivBackdropDetail)

            // Load Poster with rounded corners
            val radiusInPx = (16 * resources.displayMetrics.density).toInt()
            Glide.with(this)
                .load(movie.posterImageUrl)
                .transform(CenterCrop(), RoundedCorners(radiusInPx))
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(ivPosterDetail)
        }
    }
}
