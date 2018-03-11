package amritansh.tripathi.com.searchflix.network

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import kotlinx.android.parcel.Parcelize
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by amritanshtripathi on 3/5/18.
 */
interface ApiService {

    @GET("movie/popular?api_key=faac2a9ad921dba2511644f8e53b7c01&language=en-US&page=1")
    fun getPopularMovies(): Single<MovieResult>

    @GET("search/movie?api_key=faac2a9ad921dba2511644f8e53b7c01&language=en-US&page=1")
    fun searchMovies(@Query("query") search: String?): Single<MovieResult>

    @GET("movie/{id}/similar?api_key=faac2a9ad921dba2511644f8e53b7c01&language=en-US&page=1")
    fun getSimilarMovies(@Path("id") id: String): Single<SimilarMoviesResult>

}

//Data Classes for api service

data class MovieResult(@SerializedName("results") val result: List<Movie>)

data class SimilarMoviesResult(@SerializedName("results") val result: List<Movie>) :Item

@Parcelize
data class Movie(
        @SerializedName("id") val id: Int,
        @SerializedName("title") val title: String,
        @SerializedName("poster_path") val posterPath: String,
        @SerializedName("overview") val description: String,
        @SerializedName("vote_average") val avgVote: Double,
        @SerializedName("release_date") val releaseDate: String,
        @SerializedName("backdrop_path") val backdropPath: String
) : Parcelable, Item

interface Item{
}