package com.aplicandolistview.listview_app_example

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

data class ItemData(val nombre: String, val apellido: String, val cargo: String, val salario: String)


class CustomAdapter(context: Context, private val dataList: List<ItemData>) : ArrayAdapter<ItemData>(context, R.layout.list_item, dataList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)

        val textViewNombre: TextView = view.findViewById(R.id.textViewNombre)
        val textViewApellido: TextView = view.findViewById(R.id.textViewApellido)
        val textViewCargo: TextView = view.findViewById(R.id.textViewCargo)
        val textViewSalario: TextView = view.findViewById(R.id.textViewSalario)

        val item = getItem(position)

        textViewNombre.text = item?.nombre
        textViewApellido.text = item?.apellido
        textViewCargo.text = item?.cargo
        textViewSalario.text = item?.salario

        return view
    }
}
