package com.example.session1try3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.session1try3.databinding.BoardingFragmentBinding

class BoardingFragment(val paragraph: String, val image: Int) : Fragment() {
    private lateinit var binding: BoardingFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BoardingFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.textView3.text = paragraph
        binding.imageView.setImageResource(image)
    }
}