package com.milkflo.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.setFragmentResultListener
import com.milkflo.app.fragments.*


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        val homeFragment = HomeFragment()
        val lacListFragment = LacListFragment()
        val milestonesFragment = Milestones.getInstance()
        val profileFragment = profileFragment()
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        makeCurrentFragment(homeFragment)

        bottomNavigation.setOnItemSelectedListener{
            when(it.itemId){
                R.id.ic_home -> makeCurrentFragment(homeFragment)
                R.id.ic_laclist -> makeCurrentFragment(lacListFragment)
                R.id.ic_milestones -> makeCurrentFragment(milestonesFragment)
                R.id.ic_profile -> makeCurrentFragment(profileFragment)
            }
            true
        }

        }

    private fun makeCurrentFragment(fragment : Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
}