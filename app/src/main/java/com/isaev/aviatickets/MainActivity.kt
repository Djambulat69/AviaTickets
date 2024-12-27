package com.isaev.aviatickets

import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.isaev.main.MainFragment
import com.isaev.main.TicketsOpener
import com.isaev.search.SearchFragment
import com.isaev.tickets.TicketsFragment

class MainActivity : AppCompatActivity(), SearchFragment.SearchResultListener, TicketsOpener {
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
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, MainFragment::class.java, null, MainFragment.TAG)
                .commit()
        }

        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.main_menu_item -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(
                            R.id.fragment_container,
                            MainFragment::class.java,
                            null,
                            MainFragment.TAG
                        )
                        .commit()
                    true
                }

                else -> false
            }
        }
    }

    override fun onCityPicked(city: String) {
        (supportFragmentManager.findFragmentByTag(MainFragment.TAG) as? MainFragment)
            ?.pickWhereCity(city)
    }

    override fun openTickets(where: String, from: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, TicketsFragment.newInstance(where, from))
            .addToBackStack(null)
            .commit()
    }

    private companion object {
        const val TAG = "MainActivity"
    }
}