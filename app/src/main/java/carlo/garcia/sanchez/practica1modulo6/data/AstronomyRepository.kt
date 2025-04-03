package carlo.garcia.sanchez.practica1modulo6.data

import carlo.garcia.sanchez.practica1modulo6.data.db.AstronomyDao
import carlo.garcia.sanchez.practica1modulo6.data.db.model.AstronomyEntity

/**
 * @author Carlo García Sánchez
 *
 * Clase que vuelve a encapsular los métodos Crud del DAO
 */

class AstronomyRepository ( private val astronomyDao: AstronomyDao ) {

    suspend fun getAllAtroObjects(): MutableList<AstronomyEntity> = astronomyDao.getAllAstroObjects()

    suspend fun insertAstrObject(game: AstronomyEntity) {
        astronomyDao.insertAstroObject(game)
    }
    suspend fun updateAstroObject(game: AstronomyEntity) {
        astronomyDao.updateAstroObject(game)
    }
    suspend fun deleteAstroObject(game: AstronomyEntity) {
        astronomyDao.deleteAstroObject(game)
    }
}