package carlo.garcia.sanchez.practica1modulo6.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import carlo.garcia.sanchez.practica1modulo6.util.Constants
import java.util.Date

/**
 * @author Carlo García Sánchez
 *
 * Tabla de objetos astronómicos
 *
 * Se incluyo la fecha de descubrimiento para intentar utilizar un
 * nuevo control y tipo de dato
 */

@Entity(tableName = Constants.DATABASE_ASTRONOMY_TABLE)
data class AstronomyEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "object_id")
    var id: Long = 0,
    @ColumnInfo(name = "object_name")
    var name: String,
    @ColumnInfo(name = "object_type")
    var type: Int,
    @ColumnInfo(name = "object_discovery_date")
    var date: Date,
    @ColumnInfo(name = "object_discoverer_name")
    var discoverer_name: String
)
