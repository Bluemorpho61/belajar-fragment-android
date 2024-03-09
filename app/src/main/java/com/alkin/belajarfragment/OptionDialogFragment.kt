package com.alkin.belajarfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment


class OptionDialogFragment : DialogFragment() {

    private lateinit var btnChoose: Button
    private lateinit var btnClose: Button
    private lateinit var rg: RadioGroup
    private lateinit var rbLouis: RadioButton
    private lateinit var rbSaf: RadioButton
    private lateinit var rbJose: RadioButton
    private lateinit var rbDavid: RadioButton
    private var optionDialogListener: OnOptionDialogListener? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_options_fragments_dialog, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnChoose = view.findViewById(R.id.btn_choose)
        btnClose = view.findViewById(R.id.btn_close)
        rg = view.findViewById(R.id.rg_options)
        rbDavid = view.findViewById(R.id.david)
        rbLouis = view.findViewById(R.id.louis)
        rbSaf = view.findViewById(R.id.saf)
        rbJose = view.findViewById(R.id.jose)

        btnChoose.setOnClickListener {
            val checkedRadioButtonId = rg.checkedRadioButtonId
            if (checkedRadioButtonId != -1) {
                var coach: String? = when (checkedRadioButtonId) {
                    R.id.david -> rbDavid.text.toString().trim()
                    R.id.louis -> rbLouis.text.toString().trim()
                    R.id.saf -> rbSaf.text.toString().trim()
                    R.id.jose -> rbJose.text.toString().trim()
                    else -> null
                }
                optionDialogListener?.onOptionChosen(coach)
                dialog?.dismiss()
            }
        }
        btnClose.setOnClickListener {
            dialog?.cancel()
        }
    }

    interface OnOptionDialogListener {
        fun onOptionChosen(text: String?)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val fragment = parentFragment

        if (fragment is DetailCategoryFragment){
            this.optionDialogListener =fragment.optionFragmentDialog
        }
    }

    override fun onDetach() {
        super.onDetach()
        this.optionDialogListener =null
    }

}
