package com.crossborders.studyhard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import java.util.ArrayList

class AytDenemeHesaplaActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ayt_deneme_hesapla)

        val libreFranklinSemibold = R.font.libre_franklin_semibold
        findViewById<TextView>(R.id.ayt_deneme_hesapla_title).typeface =
            ResourcesCompat.getFont(this, libreFranklinSemibold)

        viewPager = findViewById(R.id.ayt_deneme_hesapla_view_pager)
        setupViewPager()

    }

    private fun setupViewPager(){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(AytTurkceFragment(), "Türk Dili ve Sosyal-1")
        adapter.addFragment(AytSosyal2Fragment(), "Sosyal-2")
        adapter.addFragment(AytMatematikFragment(), "Matematik")
        adapter.addFragment(AytFenFragment(), "Fen Bilimleri")
        viewPager.adapter=adapter


    }

    internal class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        private val fragmentList: MutableList<Fragment> = ArrayList()
        private val titleList: MutableList<String> = ArrayList()

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            titleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }
    }

}