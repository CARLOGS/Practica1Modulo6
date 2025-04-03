package carlo.garcia.sanchez.practica1modulo6.ui

import androidx.recyclerview.widget.RecyclerView
import carlo.garcia.sanchez.practica1modulo6.R
import carlo.garcia.sanchez.practica1modulo6.data.db.model.AstronomyEntity
import carlo.garcia.sanchez.practica1modulo6.databinding.ElementAstronomyBinding
import carlo.garcia.sanchez.practica1modulo6.util.Constants
import java.text.SimpleDateFormat

/**
 * @author Carlo García Sánchez
 *
 * View Holder para asignar los datos a cada elemento
 */
class AstronomyViewHolder( private val binding: ElementAstronomyBinding ): RecyclerView.ViewHolder( binding.root ) {

    // Codigo del ViewHolder
    fun bind(astro: AstronomyEntity) {
        binding.apply {
            val tipos = binding.root.resources.getStringArray(R.array.astro_types)
            // Recupera el ID de la imagen en base al índice en ASTRONOMY_OBJECT
            val imageName = Constants.ASTRONOMY_OBJECT.get(astro.type)
            // Recupera el recurso de la imagen con el ID
            val imageId = binding.root.resources.getIdentifier(imageName, "drawable", binding.root.context.packageName)

            // Asigna datos
            lblAstroName.text = astro.name
            lblAstroType.text = tipos[astro.type]
            lblAstroDate.text = SimpleDateFormat("d/M/yyyy").format(astro.date)
            lblAstroDiscoverer.text = astro.discoverer_name
            imgAstro.setImageResource(imageId)
        }
    }
}