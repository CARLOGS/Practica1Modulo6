package carlo.garcia.sanchez.practica1modulo6.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import carlo.garcia.sanchez.practica1modulo6.databinding.ActivityMainBinding

/**
 * @author Carlo García Sánchez
 *
 * Activity principal que muestra lol elementos en una
 * lista, utilizando RecycleView
 */

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    // Es otra forma de hacer algo en el click del boton en el layout hay onClick="click"
    fun click(view: View) {
//        val dialog = AstronomyDialog(updateUI = {updateUI()}, message = {message(it)})
//        dialog.show(supportFragmentManager, "dlgAstronomy")
    }
}