package carlo.garcia.sanchez.practica1modulo6.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import carlo.garcia.sanchez.practica1modulo6.data.db.model.AstronomyEntity

/**
 * @author Carlo García Sánchez
 *
 * Clase abstracta para especificar la base de datos extendiendo de RoomDatabase
 * Creación del DAO
 *
 * Basado en recomendación de google
 * https://developer.android.com/codelabs/android-room-with-a-view-kotlin?hl=es-419
 */

@TypeConverters(Converters::class)
@Database(entities = [AstronomyEntity::class], version = 1, exportSchema = true)
abstract class AstronomyDatabase: RoomDatabase() {
    // DAO
    abstract fun astronomyDao(): AstronomyDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AstronomyDatabase? = null

        fun getDatabase(context: Context): AstronomyDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AstronomyDatabase::class.java,
                    "database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}