package com.aplicandolistview.listview_app_example

import android.content.Context
import android.os.Environment
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.File
import java.io.FileInputStream

data class ExcelData(val year: String, val alimentos: Double, val usosDelHogar: Double, val vestuario: Double, val total: Double)

fun readExcelFile(context: Context): List<ExcelData> {
    val excelDataList = mutableListOf<ExcelData>()

    try {
        val file = File(Environment.getExternalStorageDirectory(), "Ejercicios Tecnologia Movil (2).xlsx")
        val inputStream = FileInputStream(file)
        val workbook = WorkbookFactory.create(inputStream)
        val sheet = workbook.getSheetAt(0) // Obtén la primera hoja

        // Itera sobre las filas del archivo Excel (empezando en la fila 4 para evitar encabezados)
        for (row in sheet) {
            if (row.rowNum < 3) continue // Saltar las primeras 3 filas

            val year = row.getCell(0)?.toString() ?: "N/A"
            val alimentos = row.getCell(1)?.numericCellValue ?: 0.0
            val usosDelHogar = row.getCell(2)?.numericCellValue ?: 0.0
            val vestuario = row.getCell(3)?.numericCellValue ?: 0.0
            val total = row.getCell(4)?.numericCellValue ?: 0.0

            val data = ExcelData(year, alimentos, usosDelHogar, vestuario, total)
            excelDataList.add(data)
        }

        inputStream.close()

    } catch (e: Exception) {
        e.printStackTrace()
    }

    return excelDataList
}
