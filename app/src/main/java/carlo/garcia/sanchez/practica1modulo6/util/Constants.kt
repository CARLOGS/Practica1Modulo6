package carlo.garcia.sanchez.practica1modulo6.util

/**
 * @author Carlo García Sánchez
 *
 * CONSTANTES
 *
 * Se establece el nombre de la base datos y la tabla principal
 * Nombres de objetos estelares
 */

object Constants {
    const val DATABASE_NAME = "astronomy_db"
    const val DATABASE_ASTRONOMY_TABLE = "astronomy_data_table"
    val ASTRONOMY_OBJECT = listOf<String>(
        "star",
        "galaxy",
        "nebula",
        "asteroid",
        "comet",
        "planet",
        "moon",
        "neutron_star",
        "black_hole",
        "quasar"
    )
}