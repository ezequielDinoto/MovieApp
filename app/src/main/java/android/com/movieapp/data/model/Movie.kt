package android.com.movieapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//es codigo, valor. id, value
data class Movie (val id: Int = -1,
                  val adult: Boolean = false,
val backdrop_path: String = "",
val original_title: String ="",
val original_language: String,
val overview: String = "",
val popularity: Double = -1.0,
val poster_path: String = "",
val release_date: String = "",
val video: Boolean = false,
val vote_average: Double = -1.0,
 val vote_count: Int = -1,
                  val movie_type:String =""


)
//se usa para el RemoteMovieDataSource que esta en el data.remote
data class MovieList(val results: List<Movie> = listOf())


//Room
//nos muestra como va a quedar la info en la yabla
@Entity
data class MovieEntity (
    @PrimaryKey
    val id: Int = -1,
    @ColumnInfo(name ="adult")
    val adult: Boolean = false,
    @ColumnInfo(name = "backdrop_path")
    val backdrop_path: String = "",
    @ColumnInfo(name = "original_title")
    val original_title: String ="",
    @ColumnInfo(name = "original_language")
    val original_language: String,
    @ColumnInfo(name = "overview")
    val overview: String = "",
    @ColumnInfo(name = "popularity")
    val popularity: Double = -1.0,
    @ColumnInfo(name = "poster_path")
    val poster_path: String = "",
    @ColumnInfo(name = "release_date")
    val release_date: String = "",
    @ColumnInfo(name = "video")
    val video: Boolean = false,
    @ColumnInfo(name = "vote_average")
    val vote_average: Double = -1.0,
    @ColumnInfo(name = "vote_count")
    val vote_count: Int = -1,
@ColumnInfo(name = "movie_type")
val movie_type:String =""
)
//una lista mutable, que va a ir cambiando de peliculas
fun List<MovieEntity>.toMovieList(): MovieList {
    val resultList = mutableListOf<Movie>()
    this.forEach { movieEntity ->
        resultList.add(movieEntity.toMovie())//toMovie es una extencion fuction
    }
return MovieList(resultList)

}
//el this hace referencia a el MovieEntity para crear una Movie. Creo una pelicula con su atributos
fun MovieEntity.toMovie(): Movie = Movie(
        this.id,
        this.adult,
        this.backdrop_path,
        this.original_title,
        this.original_language,
        this.overview,
        this.popularity,
        this.poster_path,
        this.release_date,
        this.video,
        this.vote_average,
        this.vote_count,
        this.movie_type

)
//transforma la pelicula a una entidad
fun Movie.toMovieEntity(movietype: String): MovieEntity= MovieEntity(
        this.id,
        this.adult,
        this.backdrop_path,
        this.original_title,
        this.original_language,
        this.overview,
        this.popularity,
        this.poster_path,
        this.release_date,
        this.video,
        this.vote_average,
        this.vote_count,
        movie_type = movietype

)