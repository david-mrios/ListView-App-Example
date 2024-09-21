package com.aplicandolistview.listview_app_example

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.InputStream
import android.view.LayoutInflater
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.listView)

        // Agregar la cabecera
        val headerView = LayoutInflater.from(this).inflate(R.layout.list_header, listView, false)
        listView.addHeaderView(headerView)

        // Leer archivo CSV desde assets
        val inputStream: InputStream = assets.open("Data.csv")
        val dataList = mutableListOf<ItemData>()

        // Leer y procesar CSV
        csvReader().open(inputStream) {
            readAllAsSequence().forEach { row ->
                val nombre = row[2] // Columna 2: Nombre
                val apellido = row[1] // Columna 1: Apellido
                val cargo = row[5] // Columna 5: Cargo
                val salario = row[6] // Columna 6: Salario
                dataList.add(ItemData(nombre, apellido, cargo, salario))
            }
        }

        // Configurar ListView con el adaptador personalizado
        val adapter = CustomAdapter(this, dataList)
        listView.adapter = adapter
    }
}

