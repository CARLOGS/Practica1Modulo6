package carlo.garcia.sanchez.practica1modulo6.data

import carlo.garcia.sanchez.practica1modulo6.data.db.AstronomyDao
import carlo.garcia.sanchez.practica1modulo6.data.db.model.AstronomyEntity

/**
 * @author Carlo García Sánchez
 *
 * Clase que vuelve a encapsular los métodos Crud del DAO
 */

class AstronomyRepository ( private val astronomyDao: AstronomyDao ) {

    suspend fun getAllGames(): MutableList<AstronomyEntity> = astronomyDao.getAllAstroObjects()

    suspend fun insertGame(game: AstronomyEntity) {
        astronomyDao.insertAstroObject(game)
    }
    suspend fun updateGame(game: AstronomyEntity) {
        astronomyDao.updateAstroObject(game)
    }
    suspend fun deleteGame(game: AstronomyEntity) {
        astronomyDao.deleteAstroObject(game)
    }
}