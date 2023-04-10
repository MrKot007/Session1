package com.example.trainings1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.trainings1.databinding.BoardingFragmentBinding

class BoardingFragment(val text: String, val pic: Int) : Fragment() {
    private lateinit var binding: BoardingFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BoardingFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.par.text = text
        binding.img.setImageResource(pic)
    }
}