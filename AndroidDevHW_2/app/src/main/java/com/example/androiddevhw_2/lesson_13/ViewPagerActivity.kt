package com.example.androiddevhw_2.lesson_13

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.androiddevhw_2.R
import com.example.androiddevhw_2.lesson_13.viewPager.HistoryFragment
import com.example.androiddevhw_2.lesson_13.viewPager.TransfersFragment

class ViewPagerActivity : FragmentActivity() {
    lateinit var viewPager: ViewPager2
    private val adapter = FragmentAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
        viewPager = findViewById<FrameLayout>(R.id.container).getChildAt(0) as ViewPager2
        viewPager.adapter = adapter
        viewPager.setPageTransformer(ZoomOutPageTransformer())
    }

    companion object {
        private const val FRAGMENT_COUNT = 2
    }

    private inner class FragmentAdapter(activity: FragmentActivity) :
        FragmentStateAdapter(activity) {
        override fun getItemCount(): Int {
            return FRAGMENT_COUNT
        }

        override fun createFragment(position: Int): Fragment = when (position) {
            0 -> TransfersFragment()
            1 -> HistoryFragment()
            else -> throw IllegalArgumentException("Incorrect Fragment count received")
        }
    }

    private inner class ZoomOutPageTransformer : ViewPager2.PageTransformer {
        private val MIN_SCALE = 0.85f
        private val MIN_ALPHA = 0.5f

        override fun transformPage(page: View, position: Float) {
            page.apply {
                val pageWidth = width
                val pageHeight = height
                when {
                    position < -1 -> { // [-Infinity,-1)
                        // This page is way off-screen to the left.
                        alpha = 0f
                    }
                    position <= 1 -> { // [-1,1]
                        // Modify the default slide transition to shrink the page as well
                        val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
                        val vertMargin = pageHeight * (1 - scaleFactor) / 2
                        val horzMargin = pageWidth * (1 - scaleFactor) / 2
                        translationX = if (position < 0) {
                            horzMargin - vertMargin / 2
                        } else {
                            horzMargin + vertMargin / 2
                        }

                        // Scale the page down (between MIN_SCALE and 1)
                        scaleX = scaleFactor
                        scaleY = scaleFactor

                        // Fade the page relative to its size.
                        alpha = (MIN_ALPHA +
                                (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                    }
                    else -> { // (1,+Infinity]
                        // This page is way off-screen to the right.
                        alpha = 0f
                    }
                }
            }
        }
    }
}