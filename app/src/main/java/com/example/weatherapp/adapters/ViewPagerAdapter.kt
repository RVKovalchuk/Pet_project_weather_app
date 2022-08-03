package com.example.weatherapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity : FragmentActivity, private val list: List<Fragment>) : FragmentStateAdapter(fragmentActivity) {
  //возвращаем количество фрагментов, участвующих в ViewPager
    override fun getItemCount(): Int {
        return list.size
    }

    //возвращаем необходимый для показа фрагмент из списка всех фрагментов
    override fun createFragment(position: Int): Fragment {
        return list[position]
    }
}