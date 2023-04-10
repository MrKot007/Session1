package com.example.session1try2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlin.concurrent.fixedRateTimer

class BoardingFragmentAdapter(fa: FragmentActivity, val fragments: List<Pair<String, Int>>) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return BoardingFragment(fragments[position].first, fragments[position].second)
    }

}