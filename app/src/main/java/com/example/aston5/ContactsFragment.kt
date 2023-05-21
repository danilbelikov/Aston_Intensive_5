package com.example.aston5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResultListener
import com.example.aston5.Constants.FIRST_FRAGMENT_BUNDLE_KEY
import com.example.aston5.Constants.FIRST_FRAGMENT_BUNDLE_KEY_INT
import com.example.aston5.Constants.FIRST_FRAGMENT_RESULT_KEY
import com.example.aston5.Constants.FIRST_FRAGMENT_RESULT_KEY_INT
import com.example.aston5.Constants.KEY_INT
import com.example.aston5.Constants.KEY_NAME
import com.example.aston5.Constants.KEY_TELEPHONE
import com.example.aston5.Constants.TELEPHONE_NUMBER_BUNDLE_KEY
import com.example.aston5.Constants.TELEPHONE_NUMBER_RESULT_KEY
import com.example.aston5.databinding.FragmentContactsBinding



class ContactsFragment : Fragment() {
    private lateinit var binding: FragmentContactsBinding
    private val bundle = Bundle()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var positionOfContact = 0
        setFragmentResultListener(FIRST_FRAGMENT_RESULT_KEY_INT) { requestKey, bundle ->
            val positionItem = bundle.getInt(FIRST_FRAGMENT_BUNDLE_KEY_INT)
            positionOfContact = positionItem
        }

        setFragmentResultListener(FIRST_FRAGMENT_RESULT_KEY) { requestKey, bundle ->
            val nameValue = bundle.getString(FIRST_FRAGMENT_BUNDLE_KEY).orEmpty()
            if (nameValue.isNotBlank() && positionOfContact == 1) binding.tvContactFirst.text =
                nameValue
            if (nameValue.isNotBlank() && positionOfContact == 2) binding.tvContactSecond.text =
                nameValue
            if (nameValue.isNotBlank() && positionOfContact == 3) binding.tvContactThird.text =
                nameValue
        }
        setFragmentResultListener(TELEPHONE_NUMBER_RESULT_KEY) { requestKey, bundle ->
            val numberValue = bundle.getString(TELEPHONE_NUMBER_BUNDLE_KEY).orEmpty()
            if (numberValue.isNotBlank() && positionOfContact == 1) binding.tvNumberFirst.text =
                numberValue
            if (numberValue.isNotBlank() && positionOfContact == 2) binding.tvNumberSecond.text =
                numberValue
            if (numberValue.isNotBlank() && positionOfContact == 3) binding.tvNumberThird.text =
                numberValue
        }

        binding.tvCardFirst.setOnClickListener {
            bundle.putInt(KEY_INT, 1)
            bundle.putString(KEY_NAME, binding.tvContactFirst.text.toString())
            bundle.putString(KEY_TELEPHONE, binding.tvNumberFirst.text.toString())

            val detFragment = DetailsFragment()
            detFragment.arguments = bundle
            if (!resources.getBoolean(R.bool.isTablet)) {
                launchDetailsFragment(R.id.main_layout, detFragment)
            } else {
                launchDetailsFragment(R.id.main_layout_details, detFragment)
            }
        }

        binding.tvCardSecond.setOnClickListener {
            bundle.putInt(KEY_INT, 2)
            bundle.putString(KEY_NAME, binding.tvContactSecond.text.toString())
            bundle.putString(KEY_TELEPHONE, binding.tvNumberSecond.text.toString())
            val detFragment = DetailsFragment()
            detFragment.arguments = bundle

            if (!resources.getBoolean(R.bool.isTablet)) {
                launchDetailsFragment(R.id.main_layout, detFragment)
            } else {
                launchDetailsFragment(R.id.main_layout_details, detFragment)
            }
        }
        binding.tvCardThird.setOnClickListener {
            bundle.putInt(KEY_INT, 3)
            bundle.putString(KEY_NAME, binding.tvContactThird.text.toString())
            bundle.putString(KEY_TELEPHONE, binding.tvNumberThird.text.toString())
            val detFragment = DetailsFragment()
            detFragment.arguments = bundle

            if (!resources.getBoolean(R.bool.isTablet)) {
                launchDetailsFragment(R.id.main_layout, detFragment)
            } else {
                launchDetailsFragment(R.id.main_layout_details, detFragment)
            }
        }
    }
    private fun launchDetailsFragment(layout: Int, fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(layout, fragment)
            .addToBackStack(null)
            .commit()
    }
    companion object {
    }
}