package carlo.garcia.sanchez.practica1modulo6.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import carlo.garcia.sanchez.practica1modulo6.R
import carlo.garcia.sanchez.practica1modulo6.databinding.ActivityMainBinding
import carlo.garcia.sanchez.practica1modulo6.databinding.ActivitySplashBinding

/**
 * @author Carlo García Sánchez
 *
 * Pantalla splash de inicio de la App
 */

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        // Delay de 2 segundo
        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)

            // Destruye el SplashActivity
            finish()
        }, 2000)
    }
}