package com.hdv.danvoresume

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(activity: FragmentActivity, val fraglist: ArrayList<Fragment>): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return fraglist.size
    }

    override fun createFragment(position: Int): Fragment {
        return fraglist[position]
    }


}