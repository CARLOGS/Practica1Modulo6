package carlo.garcia.sanchez.practica1modulo6.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import carlo.garcia.sanchez.practica1modulo6.data.db.model.AstronomyEntity
import carlo.garcia.sanchez.practica1modulo6.util.Constants

/**
 * @author Carlo García Sánchez
 *
 * Interfaz para especificar las operaciones CRUD
 */

@Dao
interface AstronomyDao {
    // Insert
    @Insert
    suspend fun insertAstroObject(game: AstronomyEntity)

    @Insert
    suspend fun insertAstroObject(games: MutableList<AstronomyEntity>)

    // Read
    @Query("SELECT * FROM ${Constants.DATABASE_ASTRONOMY_TABLE}")
    suspend fun getAllAstroObjects(): MutableList<AstronomyEntity>

    @Query("SELECT * FROM ${Constants.DATABASE_ASTRONOMY_TABLE} WHERE object_id = :id")
    suspend fun getAStroObjectById(id: Long): AstronomyEntity?

    // Update
    @Update
    suspend fun updateAstroObject(game: AstronomyEntity)

    @Update
    suspend fun updateAstroObject(games: MutableList<AstronomyEntity>)

    // Delete
    @Delete
    suspend fun deleteAstroObject(game: AstronomyEntity)

    @Delete
    suspend fun deleteAStroObject(games: MutableList<AstronomyEntity>)
}