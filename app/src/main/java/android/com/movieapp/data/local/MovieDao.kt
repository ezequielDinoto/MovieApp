package android.com.movieapp.data.local

import android.com.movieapp.data.model.MovieEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao //con el Dao hacemos creamos la BD
interface MovieDao {

    @Query("SELECT * FROM movieentity")//Obtenemos las pel de la BD
    suspend fun getAllMovies(): List<MovieEntity>
//creamos esa inform en la BD
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movie: MovieEntity)

}