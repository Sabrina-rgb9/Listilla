package com.sabrina.listilla

import android.app.AlertDialog
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    // Model
    data class Record(var intents: Int, var nom: String)

    private val records = ArrayList<Record>()
    private lateinit var recyclerAdapter: RecordAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Dades d'exemple
        records.add(Record(33, "Manolo"))
        records.add(Record(12, "Pepe"))
        records.add(Record(42, "Laura"))

        // RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recordsView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = RecordAdapter(records, this)
        recyclerView.adapter = recyclerAdapter

        // Botó Afegir
        val buttonAfegir = findViewById<Button>(R.id.button)
        buttonAfegir.setOnClickListener {
            mostrarDialogAfegir()
        }

        // Botó Ordenar (Exercici 4)
        val buttonOrdenar = findViewById<Button>(R.id.buttonOrdenar)
        buttonOrdenar.setOnClickListener {
            records.sortBy { it.intents }
            recyclerAdapter.notifyDataSetChanged()
        }
    }

    private fun mostrarDialogAfegir() {
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(32, 32, 32, 32)

        val inputNom = EditText(this)
        inputNom.hint = "Nom"
        layout.addView(inputNom)

        val inputIntents = EditText(this)
        inputIntents.hint = "Rècord"
        inputIntents.inputType = android.text.InputType.TYPE_CLASS_NUMBER
        layout.addView(inputIntents)

        AlertDialog.Builder(this)
            .setTitle("Afegir nou rècord")
            .setView(layout)
            .setPositiveButton("Afegir") { dialog, _ ->
                val nom = inputNom.text.toString().ifEmpty { "Anònim" }
                val intents = inputIntents.text.toString().toIntOrNull() ?: 0
                records.add(Record(intents, nom))
                recyclerAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            .setNegativeButton("Cancel·lar") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
