package com.example.tabnav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

private lateinit var tabLayout:TabLayout
private lateinit var tabViewPager: ViewPager2
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout=findViewById(R.id.tabs)
        tabViewPager=findViewById(R.id.pager)

        tabViewPager.adapter= object :FragmentStateAdapter(this){

            override fun createFragment(position: Int): Fragment {

               return when(position){
                   0 -> firstFragment.newInstance(position.toString(),"")
                   1 -> firstFragment.newInstance(position.toString(),"")
                   2 -> firstFragment.newInstance(position.toString(),"")
                   else -> {secondFragment.newInstance("","")}
               }
            }

            override fun getItemCount(): Int {
                return 3
            }
        }


        TabLayoutMediator(tabLayout, tabViewPager){tab , position ->
            tab.text = when(position){
                0 -> "Music"
                1 -> "Visibility"
                2 -> "Notification"
                else -> null
            }
            tab.icon = when(position){
                0 -> getDrawable(R.drawable.ic_music_note_black_24dp)
                1 -> getDrawable(R.drawable.ic_visibility_black_24dp)
                2 -> getDrawable(R.drawable.ic_notifications_active_black_24dp)
                else -> null
            }

            

        }.attach()

        tabViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Toast.makeText(this@MainActivity,"you are in page ${position+1}",Toast.LENGTH_LONG).show()
            }
        })
    }
}
