package com.idz.colman24class1

import android.content.Context
import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

class BlueFragment : Fragment() {

    var textView: TextView? = null
    var title: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = arguments?.let { BlueFragmentArgs.fromBundle(it).studentTitle }
//        arguments?.let {
//            title = it.getString("studentTitle")
//        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_blue, container, false)

        textView = view.findViewById(R.id.blue_fragment_text_view)
        textView?.text = title

        view?.findViewById<Button>(R.id.blue_fragment_back_button)?.setOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }
        return view
    }

    companion object {

        const val TITLE = "TITLE"

        fun newInstance(title: String): BlueFragment {
            return BlueFragment().apply {
                arguments = Bundle().apply {
                    putString(TITLE, title)
                }
            }
        }
    }
}