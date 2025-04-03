package carlo.garcia.sanchez.practica1modulo6.ui

import android.R
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import carlo.garcia.sanchez.practica1modulo6.application.AstronomyDBApp
import carlo.garcia.sanchez.practica1modulo6.data.AstronomyRepository
import carlo.garcia.sanchez.practica1modulo6.data.db.model.AstronomyEntity
import carlo.garcia.sanchez.practica1modulo6.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

/**
 * @author Carlo García Sánchez
 *
 * Activity principal que muestra lol elementos en una
 * lista, utilizando RecycleView
 */

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // Listado de objectos celestes
    private var astroObjects: MutableList<AstronomyEntity> = mutableListOf()
    // Repositorio
    private lateinit var respository: AstronomyRepository

    // Game Adapter para RecycleView
    private lateinit var astronomyAdapter: AstronomyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicia y crea DB
        respository = (application as AstronomyDBApp).repository

        // Instancia el AtronomyAdapter
        astronomyAdapter = AstronomyAdapter { selectedAtroObject ->
            val dialog = AstronomyDialog(
                newAstroObject = false,
                astroObject = selectedAtroObject,
                updateUI = {updateUI()},
                message = {message(it)}
            )

            dialog.show(supportFragmentManager, "dlgAtroUpdate")
        }

        binding.apply {
            lstAtroObjects.layoutManager = LinearLayoutManager(this@MainActivity)
            lstAtroObjects.adapter = astronomyAdapter
        }

        // Actualiza lista
        updateUI()
    }

    // Actuliza lista de datos
    private fun updateUI() {
        lifecycleScope.launch {
            // Recupera los datos de la base de datos
            astroObjects = respository.getAllAtroObjects()

            // para que se ejecute en el Thread principal
            binding.lblSinRegistros.visibility = if (astroObjects.isEmpty()) View.VISIBLE else View.INVISIBLE
            astronomyAdapter.updateAstroObjects(astroObjects)
        }
    }

    // Es otra forma de hacer algo en el click del boton en el layout hay onClick="click"
    fun click(view: View) {
        val dialog = AstronomyDialog(updateUI = {updateUI()}, message = {message(it)})
        dialog.show(supportFragmentManager, "dlgAstronomy")
    }

    private fun message(text: String) {
        // Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT)
            .setTextColor(getColor(R.color.white))
            // .setBackgroundTint(getColor(R.color.holo_red_dark)) // #9E1734
            .setBackgroundTint(Color.parseColor("#3657B1")) // #9E1734
            .show()
    }
}