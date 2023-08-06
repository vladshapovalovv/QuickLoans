package com.example.shapovalov

import android.content.Context
import com.example.shapovalov.component.ui.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun showInstructionsDialog(context: Context) {
    MaterialAlertDialogBuilder(context)
        .setTitle(context.getString(R.string.loan_instructions_title))
        .setMessage(context.getString(R.string.loan_instructions_body))
        .setPositiveButton(context.getString(R.string.loan_instructions_ok)) { dialog, _ ->
            dialog.dismiss()
        }
        .show()
}