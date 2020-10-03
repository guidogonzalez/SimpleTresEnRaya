package es.widoapps.simpletresenraya.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import es.widoapps.simpletresenraya.R

class FinPartidaDialog : DialogFragment() {

    private var rootView: View? = null
    private var activity: MainActivity? = null
    private var ganador: String? = null

    companion object {

        fun newInstance(activity: MainActivity, ganador: String): FinPartidaDialog? {

            val finPartidaDialog = FinPartidaDialog()
            finPartidaDialog.activity = activity
            finPartidaDialog.ganador = ganador

            return finPartidaDialog
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        rootView = LayoutInflater.from(activity?.applicationContext)
            .inflate(R.layout.dialog_fin_partida, null)

        // Aplicamos el ganador
        (rootView!!.findViewById<View>(R.id.tvGanador) as TextView).text = ganador

        val alertDialog = AlertDialog.Builder(context)
            .setView(rootView)
            .setCancelable(false)
            .setPositiveButton("HECHO") { dialog, which -> nuevaPartida() }
            .create()
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.setCancelable(false)

        return alertDialog
    }

    private fun nuevaPartida() {

        dismiss()
        activity?.iniciarDataBinding()
    }
}