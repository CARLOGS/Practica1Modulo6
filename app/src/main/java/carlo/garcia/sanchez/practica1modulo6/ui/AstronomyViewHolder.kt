package carlo.garcia.sanchez.practica1modulo6.ui

import androidx.recyclerview.widget.RecyclerView
import carlo.garcia.sanchez.practica1modulo6.data.db.model.AstronomyEntity
import carlo.garcia.sanchez.practica1modulo6.databinding.ElementAstronomyBinding

class AstronomyViewHolder( private val binding: ElementAstronomyBinding ): RecyclerView.ViewHolder( binding.root ) {

    // Codigo del ViewHolder
    fun bind(astro: AstronomyEntity) {
        binding.apply {
            lblTitle.text = astro.name
            lblGenre.text = astro.discoverer_name
            lblDeveloper.text = astro.name
        }
    }
}