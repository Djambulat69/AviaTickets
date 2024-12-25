package com.isaev.aviatickets

import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.isaev.main.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val systemBarStyleBlack = SystemBarStyle.dark(getColor(R.color.black))
        enableEdgeToEdge(
            statusBarStyle = systemBarStyleBlack,
            navigationBarStyle = systemBarStyleBlack
        )
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, MainFragment())
                .commit()
        }

        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.main_menu_item -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, MainFragment::class.java, null)
                        .commit()
                    true
                }

                else -> false
            }
        }
    }


    private companion object {
        const val TAG = "MainActivity"
    }
}