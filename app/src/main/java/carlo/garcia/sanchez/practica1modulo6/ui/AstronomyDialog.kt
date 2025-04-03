package carlo.garcia.sanchez.practica1modulo6.ui

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import carlo.garcia.sanchez.practica1modulo6.R
import carlo.garcia.sanchez.practica1modulo6.application.AstronomyDBApp
import carlo.garcia.sanchez.practica1modulo6.data.AstronomyRepository
import carlo.garcia.sanchez.practica1modulo6.data.db.model.AstronomyEntity
import carlo.garcia.sanchez.practica1modulo6.databinding.DialogAstronomyBinding
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date


/**
 * @author Carlo García Sánchez
 *
 * Dialogo para capturar y modificar un objeto astronómico
 */

class AstronomyDialog(
    private var newAstroObject: Boolean = true,

    private var astroObject: AstronomyEntity = AstronomyEntity(
        name = "",
        type = 0,
        date = Date(),
        discoverer_name = ""
    ),
    private val updateUI: () -> Unit,
    private val message: (String) -> Unit
): DialogFragment() {
    // NOTA: Al aplicar los Bindings a Fragments de recomuenta hacer referencia mutable y hacerlo nulo al destroy
    private var _binding: DialogAstronomyBinding? = null
    private val binding get() = _binding!!
    private lateinit var dialog: Dialog
    private val calendario: Calendar = Calendar.getInstance()

    // NOTA: Truco para acceder al boton de guardar
    private var saveButton: Button? = null
    // Repositorio de DB
    private lateinit var repository: AstronomyRepository

    // Se crea y configura el dialogo
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogAstronomyBinding.inflate(requireActivity().layoutInflater)

        // Inicia Spinner
        initSpinner()

        // Inicia DatePicker
        initDatePicker()

        binding.btnDate.setOnClickListener {
            mostrarDatePicker()
        }

        dialog = if (newAstroObject) {
            // Nuevo objeto
            buildDialog(
                getString(R.string.dialog_save),
                getString(R.string.dialog_cancel),
                {
                    astroObject.name = binding.tietTitle.text.toString()
                    astroObject.type = binding.cmbType.selectedItemPosition
                    astroObject.date = getSelectedDate()
                    astroObject.discoverer_name = binding.tietDeveloper.text.toString()

                    try {
                        lifecycleScope.launch {
                            // Ejecuta asyncronamente la inserción
                            val result = async {
                                repository.insertGame(astroObject)
                            }
                            result.await()

                            message(getString(R.string.dialog_object_saved))

                            updateUI()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        message(getString(R.string.dialog_save_error))
                    }
                },
                {
                    dismiss()
                }
            )
        } else {
            // Actualiza objeto
            buildDialog(
                getString(R.string.dialog_update),
                getString(R.string.dialog_delete),
                {
                    astroObject.name = binding.tietTitle.text.toString()
                    astroObject.type = binding.cmbType.selectedItemPosition
                    astroObject.date = getSelectedDate()
                    astroObject.discoverer_name = binding.tietDeveloper.text.toString()

                    try {
                        lifecycleScope.launch {
                            // Ejecuta asyncronamente la actualización
                            val result = async {
                                repository.updateGame(astroObject)
                            }
                            result.await()

                            message(getString(R.string.dialog_updated))

                            updateUI()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        message(getString(R.string.dialog_update_error))
                    }
                },
                {
                    // Conexto
                    val conext = requireContext()

                    // Confirma la eliminación
                    AlertDialog.Builder(requireContext())
                        .setTitle(getString(R.string.dialog_delete_title))
                        .setMessage(getString(R.string.dialog_delete_confirmation, astroObject.name))
                        .setPositiveButton(getString(R.string.dialog_delete_button)) { _, _ ->
                            try {
                                lifecycleScope.launch {
                                    // Ejecuta asyncronamente la eliminación
                                    val result = async {
                                        repository.deleteGame(astroObject)
                                    }
                                    result.await()

                                    // OJO: Aqui se utiliza el context original porque es un context anidado
                                    message(conext.getString(R.string.dialog_delete_object))

                                    updateUI()
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                                message(getString(R.string.dialog_delete_error))
                            }
                        }
                        .setNegativeButton(conext.getString(R.string.dialog_cancel)) { _, _ ->
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                }
            )
        }

        return dialog
    }

    private fun getSelectedDate(): Date {
        val date_part = binding.lblDate.text.split("/")
        val year = date_part[2].toInt()
        val month = date_part[1].toInt() - 1
        val day = date_part[0].toInt()

        val cal = Calendar.getInstance()
        cal.set(year, month, day)

        return cal.time
    }

    /*
        Inicia DatePicker
     */
    private fun initDatePicker() {
        val year: Int = calendario.get(Calendar.YEAR)
        val month: Int = calendario.get(Calendar.MONTH)
        val day: Int = calendario.get(Calendar.DAY_OF_MONTH)
        val today = day.toString() + "/" + (month + 1) + "/" + year

        binding.lblDate.setText(today)
    }
    private fun mostrarDatePicker() {
        val date_part = binding.lblDate.text.split("/")
        val year = date_part[2].toInt()
        val month = date_part[1].toInt() - 1
        val day = date_part[0].toInt()

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            object : OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    val fechaSeleccionada = dayOfMonth.toString() + "/" + (month + 1) + "/" + year
                    binding.lblDate.setText(fechaSeleccionada)
                }
            },
            year, month, day
        )
        // datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000;
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis();

        datePickerDialog.show()
    }
    /*
        Inicia el contenido del SPinner
     */
    private fun initSpinner() {
        // Recupera el array de opciones de values/arrays.xml
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.astro_types,
            android.R.layout.simple_spinner_item
        )
        // Definir el layout para el dropdown
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Asignar el adapter al spinner
        binding.cmbType.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    // Se llama después de que se muestra el dialogo
    override fun onStart() {
        super.onStart()

        repository = (requireContext().applicationContext as AstronomyDBApp).repository

        // Referencia boton guardar
        saveButton = (dialog as AlertDialog).getButton(Dialog.BUTTON_POSITIVE)
        saveButton?.isEnabled = false


        binding.apply {
            setupTextWatcher(
                tietTitle,
                tietTitle,
                tietDeveloper
            )
        }
    }

    private fun validateFields(): Boolean =
        binding.tietTitle.text.toString().isNotEmpty()
                && binding.tietTitle.text.toString().isNotEmpty()
                && binding.tietDeveloper.text.toString().isNotEmpty()

    private fun setupTextWatcher(vararg textFields: TextInputEditText){
        val textWatcher = object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                saveButton?.isEnabled = validateFields()
            }
        }

        textFields.forEach { it.addTextChangedListener(textWatcher) }
    }

    private fun buildDialog(
        btn1Text: String,
        btn2Text: String,
        positiveButton: () -> Unit,
        negativeButton: () -> Unit
    ) : Dialog =
        AlertDialog.Builder(requireContext()).setView(binding.root)
            .setTitle(getString(R.string.dialog_title))
            .setPositiveButton(btn1Text) {_, _ ->
                positiveButton()
            }
            .setNegativeButton(btn2Text) { _, _ ->
                negativeButton()
            }
            .create()
}
