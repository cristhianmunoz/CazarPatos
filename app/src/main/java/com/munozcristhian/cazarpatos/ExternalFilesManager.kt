package com.munozcristhian.cazarpatos

import android.app.Activity
import android.os.Environment
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class ExternalFilesManager(val actividad: Activity): FileHandler{
    fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }
    override fun SaveInformation(datosAGrabar: Pair<String, String>) {
        if (isExternalStorageWritable()) {
            FileOutputStream(
                File(
                    actividad.getExternalFilesDir(null),
                    "FileEncriptado"
                )
            ).bufferedWriter().use { outputStream ->
                outputStream.write("dato1")
                outputStream.write(System.lineSeparator())
                outputStream.write("dato2")
            }
        }
    }
    fun isExternalStorageReadable(): Boolean {
        return Environment.getExternalStorageState() in
                setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY)
    }

    override fun ReadInformation(): Pair<String, String> {
        if (isExternalStorageReadable()) {
            FileInputStream(
                File(
                    actividad.getExternalFilesDir(null),
                    "FileEncriptado"
                )
            ).bufferedReader().use {
                val datoLeido = it.readText()
                val textArray = datoLeido.split(System.lineSeparator())
                val texto1 = textArray[0]
                val texto2 = textArray[1]
                println(textArray)
                return (texto1 to texto2)
            }
        }
        return ("" to "")
    }

}