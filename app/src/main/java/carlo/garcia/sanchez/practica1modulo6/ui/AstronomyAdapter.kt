package carlo.garcia.sanchez.practica1modulo6.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import carlo.garcia.sanchez.practica1modulo6.data.db.model.AstronomyEntity
import carlo.garcia.sanchez.practica1modulo6.databinding.ElementAstronomyBinding

class AstronomyAdapter(
    private val onAtroClicked: (AstronomyEntity) -> Unit
): RecyclerView.Adapter<AstronomyViewHolder>() {
    private var astroObjects: List<AstronomyEntity> = emptyList()

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ): AstronomyViewHolder {
        val binding = ElementAstronomyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AstronomyViewHolder(binding)
    }

    override fun onBindViewHolder( holder: AstronomyViewHolder, position: Int ) {
        val game = astroObjects[position]
        holder.bind(game)
        holder.itemView.setOnClickListener {
            // Click on game
            onAtroClicked(game)
        }
    }

    fun updateAstroObjects(list: MutableList<AstronomyEntity>) {
        astroObjects = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = astroObjects.size
}