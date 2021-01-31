package android.com.movieapp.data.local

import android.com.movieapp.data.model.MovieEntity
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//esto es para acceder a los datos abstractos de la Database, q es el Dao

//esto es un array de entidades,
@Database(entities = [MovieEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao
    //un singleton
    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context):AppDatabase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "movie_table"

            ).build()
            return INSTANCE!!
        }

            fun destroyInstance(){
                INSTANCE = null




        }

    }

}