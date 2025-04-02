package carlo.garcia.sanchez.practica1modulo6.application

import android.app.Application
import carlo.garcia.sanchez.practica1modulo6.data.AstronomyRepository
import carlo.garcia.sanchez.practica1modulo6.data.db.AstronomyDatabase

/**
 * @author Carlo García Sánchez
 *
 * Establece la aplicación principal
 *
 * Instancía la Base de datos
 * Inicia el repositorio DAO
 */
class AstronomyDBApp: Application() {
    private val database by lazy {
        AstronomyDatabase.getDatabase(this@AstronomyDBApp)
    }

    val repository by lazy {
        AstronomyRepository(database.astronomyDao())
    }}