package com.mandrid.omdb

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName="movie_table")
@Parcelize
@Serializable
    data class MovieModel(
        @SerializedName("actor")
        var actor: String = "",
        @SerializedName("critics")
        var critics: Double = 0.0,
        @SerializedName("director")
        var director: String = "",
        @SerializedName("genre")
        var genre: String = "",
        @SerializedName("length")
        var length: Int = 0,

       @PrimaryKey(autoGenerate = true)
        @SerializedName("movieId")
        var movieId: Int = 0,
        @SerializedName("mpaRating")
        var mpaRating: String = "",
        @SerializedName("shortDescription")
        var shortDescription: String = "",
        @SerializedName("studio")
        var studio: String = "",
        @SerializedName("thumbnail")
        var thumbnail: String = "https://thumbs.dreamstime.com/b/no-image-photo-template-gray-background-empty-photography-mockup-picture-available-thumbnail-placeholder-blank-sign-237351983.jpg",
        @SerializedName("title")
        var title: String = "",
        @SerializedName("writer")
        var writer: String = "",
        @SerializedName("year")
        var year: Int = 0


    ):Parcelable


@Serializable
@Parcelize
data class Movie(
    @SerialName("imdbID")
    var imdbID: String,
    @SerialName("Poster")
    var poster: String,
    @SerialName("Title")
    var title: String,
    @SerialName("Type")
    var type: String,
    @SerialName("Year")
    var year: String
): Parcelable

@Serializable
data class SearchMovie(
    @SerialName("Response")
    var response: String,
    @SerialName("Search")
    var search: List<Movie>,
    @SerialName("totalResults")
    var totalResults: String
) {

}

@Serializable
data class MovieDetail(
    @SerialName("Actors")
    var actors: String,
    @SerialName("Awards")
    var awards: String,
    @SerialName("BoxOffice")
    var boxOffice: String,
    @SerialName("Country")
    var country: String,
    @SerialName("DVD")
    var dVD: String,
    @SerialName("Director")
    var director: String,
    @SerialName("Genre")
    var genre: String,
    @SerialName("imdbID")
    var imdbID: String,
    @SerialName("imdbRating")
    var imdbRating: String,
    @SerialName("imdbVotes")
    var imdbVotes: String,
    @SerialName("Language")
    var language: String,
    @SerialName("Metascore")
    var metascore: String,
    @SerialName("Plot")
    var plot: String,
    @SerialName("Poster")
    var poster: String,
    @SerialName("Production")
    var production: String,
    @SerialName("Rated")
    var rated: String,
    @SerialName("Ratings")
    var ratings: List<Rating>,
    @SerialName("Released")
    var released: String,
    @SerialName("Response")
    var response: String,
    @SerialName("Runtime")
    var runtime: String,
    @SerialName("Title")
    var title: String,
    @SerialName("Type")
    var type: String,
    @SerialName("Website")
    var website: String,
    @SerialName("Writer")
    var writer: String,
    @SerialName("Year")
    var year: String
) {
    @Serializable
    data class Rating(
        @SerialName("Source")
        var source: String,
        @SerialName("Value")
        var value: String
    )
}
