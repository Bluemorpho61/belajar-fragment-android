package com.alkin.belajarfragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment


class DetailCategoryFragment : Fragment() {
    private lateinit var tvCategoryName: TextView
    private lateinit var tvCatDesc: TextView
    private lateinit var btnProfile: Button
    private lateinit var btnShowDialog: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_category, container, false)
    }

    var description: String? = null

    companion object {
        var EXTRA_NAME = "extra_name"
        var EXTRA_DESCRIPTION = "extra_description"
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvCategoryName = view.findViewById(R.id.tv_category)
        tvCatDesc = view.findViewById(R.id.tv_category_description)
        btnProfile = view.findViewById(R.id.btn_profile)
        btnShowDialog = view.findViewById(R.id.btn_show_dialog)

        btnShowDialog.setOnClickListener {
            val optionFragmentDialog =OptionDialogFragment()

            val fragmentManager =childFragmentManager
            optionFragmentDialog.show(fragmentManager,OptionDialogFragment::class.java.simpleName)
        }

        btnProfile.setOnClickListener {
            val intent =Intent(requireActivity(),ProfileActivity::class.java)
            startActivity(intent)
        }

        if (savedInstanceState != null) {
            val descFromBundle = savedInstanceState.getString(EXTRA_DESCRIPTION)
            description = descFromBundle
        }
        if (arguments != null) {
            val categoryName = arguments?.getString(EXTRA_NAME)
            tvCategoryName.text = categoryName
            tvCatDesc.text = description
        }


    }

    internal val optionFragmentDialog: OptionDialogFragment.OnOptionDialogListener =
        object : OptionDialogFragment.OnOptionDialogListener {
            override fun onOptionChosen(text: String?) {
                Toast.makeText(requireActivity(),text,Toast.LENGTH_SHORT).show()
            }
        }

}