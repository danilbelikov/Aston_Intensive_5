package com.example.aston5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import com.example.aston5.Constants.FIRST_FRAGMENT_BUNDLE_KEY
import com.example.aston5.Constants.FIRST_FRAGMENT_BUNDLE_KEY_INT
import com.example.aston5.Constants.FIRST_FRAGMENT_RESULT_KEY
import com.example.aston5.Constants.FIRST_FRAGMENT_RESULT_KEY_INT
import com.example.aston5.Constants.KEY_INT
import com.example.aston5.Constants.KEY_NAME
import com.example.aston5.Constants.KEY_TELEPHONE
import com.example.aston5.Constants.TELEPHONE_NUMBER_BUNDLE_KEY
import com.example.aston5.Constants.TELEPHONE_NUMBER_RESULT_KEY
import com.example.aston5.databinding.FragmentDetailsBinding



class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = this.arguments

        val inputData = args?.getString(KEY_NAME)
        val inputData2 = args?.getString(KEY_TELEPHONE)
        val position = args?.getInt(KEY_INT)
        binding.tvNameContact.text = inputData.orEmpty()
        binding.tvNumberContact.text = inputData2.orEmpty()
        if (inputData?.isNotBlank() == true) binding.bEdit.visibility = View.VISIBLE

        binding.bEdit.setOnClickListener {
            with(binding) {
                edNumber.visibility = View.VISIBLE
                edName.visibility = View.VISIBLE
                bEdit.visibility = View.GONE
                bSave.visibility = View.VISIBLE
            }
        }
        binding.bSave.setOnClickListener {
            setFragmentResult(
                FIRST_FRAGMENT_RESULT_KEY_INT,
                bundleOf(FIRST_FRAGMENT_BUNDLE_KEY_INT to position)
            )
            setFragmentResult(
                FIRST_FRAGMENT_RESULT_KEY,
                bundleOf(FIRST_FRAGMENT_BUNDLE_KEY to binding.edName.text.toString())
            )
            setFragmentResult(
                TELEPHONE_NUMBER_RESULT_KEY,
                bundleOf(TELEPHONE_NUMBER_BUNDLE_KEY to binding.edNumber.text.toString())
            )
            parentFragmentManager.popBackStack()
        }
    }

    companion object {

    }
}