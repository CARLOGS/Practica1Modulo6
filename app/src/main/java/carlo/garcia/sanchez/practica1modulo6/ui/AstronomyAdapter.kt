package carlo.garcia.sanchez.practica1modulo6.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import carlo.garcia.sanchez.practica1modulo6.data.db.model.AstronomyEntity
import carlo.garcia.sanchez.practica1modulo6.databinding.ElementAstronomyBinding

/**
 * @author Carlo García Sánchez
 *
 * Adaptador para asociar los datos y responde la click
 */

class AstronomyAdapter(
    private val onAtroClicked: (AstronomyEntity) -> Unit
): RecyclerView.Adapter<AstronomyViewHolder>() {
    private var astroObjects: List<AstronomyEntity> = emptyList()

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ): AstronomyViewHolder {
        val binding = ElementAstronomyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AstronomyViewHolder(binding)
    }

    override fun onBindViewHolder( holder: AstronomyViewHolder, position: Int ) {
        val astroObject = astroObjects[position]
        holder.bind(astroObject)
        holder.itemView.setOnClickListener {
            // Click on Astro Object
            onAtroClicked(astroObject)
        }
    }

    fun updateAstroObjects(list: MutableList<AstronomyEntity>) {
        astroObjects = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = astroObjects.size
}