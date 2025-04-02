package carlo.garcia.sanchez.practica1modulo6.data.db

import androidx.room.TypeConverter
import java.util.Date

class Converters {
    @TypeConverter
    fun toDate(dateLong:Long): Date {
        return Date(dateLong)
    }

    @TypeConverter
    fun fromDate(date: Date):Long{
        return date.time;
    }
}