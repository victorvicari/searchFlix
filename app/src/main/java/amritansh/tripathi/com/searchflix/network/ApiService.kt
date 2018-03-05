package amritansh.tripathi.com.searchflix.network

import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by amritanshtripathi on 3/5/18.
 */
interface ApiService {

    @GET("/movie/popular?api_key={key}&language=en-US&page={page}")
    fun getPopularMovies(@Path("key") apiKey: String = "faac2a9ad921dba2511644f8e53b7c01", @Path("page") page: Int = 1): Single<List<Movie>>

    @GET("/search/movie?api_key={key}&language=en-US&query={search}&page={page}")
    fun searcgMovies(@Path("search") search: String, @Path("key") apiKey: String = "faac2a9ad921dba2511644f8e53b7c01", @Path("page") page: Int = 1): Single<List<Movie>>

}

data class Movie(
        @SerializedName("id") val id:Int,
        @SerializedName("title")val title:String,
        @SerializedName("poster_path")val posterPath:String,
        @SerializedName("overview")val description:String
)