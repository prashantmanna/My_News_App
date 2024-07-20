package com.example.mynewsapp

import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var bottomNav :BottomNavigationView
    lateinit var frame : FrameLayout
    lateinit var toolbar : MaterialToolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        bottomNav = findViewById(R.id.bottomNav)
        frame = findViewById(R.id.frame)
        toolbar = findViewById(R.id.materialToolbar)
        val fragmentManager =  supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame,home())
        fragmentTransaction.commit()

        bottomNav.setOnItemSelectedListener {

            when(it.itemId){
                R.id.home -> replaceFragment(home())
                R.id.sports -> replaceFragment(sports())
                R.id.entertainment -> replaceFragment(entertainment())
                R.id.international -> replaceFragment(international())
                else -> replaceFragment(national())

            }
            true
        }
    }

    fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame,fragment)
        fragmentTransaction.commit()

    }
}