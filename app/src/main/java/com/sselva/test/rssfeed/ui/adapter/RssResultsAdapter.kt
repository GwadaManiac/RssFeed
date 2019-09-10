package com.sselva.test.rssfeed.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import android.util.SparseArray

import com.sselva.test.rssfeed.ui.fragment.RssResultsFragment

import com.sselva.test.rssfeed.ui.fragment.RssResultsFragment.Companion.EXTRA_BUNDLE_POSITION

class RssResultsAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private var mFragmentMap: SparseArray<Fragment>? = null

    private fun getResultsFragmentInstance(position: Int): Fragment {
        val fragment = RssResultsFragment()
        val bundle = Bundle()
        bundle.putInt(EXTRA_BUNDLE_POSITION, position)
        fragment.arguments = bundle
        return fragment
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

    override fun getItem(position: Int): Fragment {
        if (mFragmentMap == null) {
            mFragmentMap = SparseArray()
        }
        if (mFragmentMap!!.get(position) == null) {
            mFragmentMap!!.put(position, getResultsFragmentInstance(position))
        }
        return mFragmentMap!!.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return PAGES[position]
    }

    override fun getCount(): Int {
        return PAGES.size
    }

    companion object {
        private val PAGES = arrayOf("Une", "Techno", "Sport", "Immo", "Guides")
    }
}
