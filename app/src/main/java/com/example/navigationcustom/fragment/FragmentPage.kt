package com.example.navigationcustom.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.navigationcustom.databinding.FragmentPageBinding
import kotlin.random.Random

class FragmentPage : Fragment() {

    private var _binding: FragmentPageBinding? = null
    private val binding get() = requireNotNull(_binding) {
        "View was destroyed"
    }

    private var pageText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageText = savedInstanceState?.getString(PAGE_KEY) ?: "Page: ${Random.nextInt(0, 20)}"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentPageBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.description.text = pageText
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(PAGE_KEY, pageText)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // according to lifecycle onSaveInstanceState will be called when fragment destroyed
        // save current text to local variable to save it into bundle when configuration changed
        pageText = binding.description.text.toString()
        _binding = null
    }

    companion object {
        private const val PAGE_KEY = "page_key"
    }
}