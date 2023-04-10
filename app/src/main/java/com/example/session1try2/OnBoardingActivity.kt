package com.example.session1try2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.session1try2.databinding.ActivityOnBoardingBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fragments = listOf(
            Pair("Без теории, только практика\n" +
                    "Вы не платите за лекции и теоретический материал, ведь все это можно найти в интернете бесплатно", R.drawable.post1),
            Pair("Без теории, только практика\n" +
                    "Вы не платите за лекции и теоретический материал, ведь все это можно найти в интернете бесплатно", R.drawable.post2),
            Pair("Обучение онлайн из любой точки мира\n" +
                    "Наше обучение изначально создавалось как дистанционное", R.drawable.post3)
        )
        binding.pager.adapter = BoardingFragmentAdapter(this, fragments)
        binding.pager.isUserInputEnabled = false
        binding.pager.orientation = ViewPager2.ORIENTATION_VERTICAL
        TabLayoutMediator(binding.tabs, binding.pager) { tab: TabLayout.Tab, i: Int -> }.attach()
        binding.next.setOnClickListener {
            if (binding.pager.currentItem == 1) {
                binding.nextText.text = "Начать"
            }
            if (binding.pager.currentItem == 2) {
                startActivity(Intent(this@OnBoardingActivity, LogInActivity::class.java))
                finish()
            }
            binding.pager.currentItem += 1
        }


    }
}