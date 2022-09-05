package com.example.navigationcustom

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.core.view.isVisible
import com.example.navigationcustom.fragment.BottomNavFragment
import com.example.navigationcustom.fragment.TabFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val buttonsContainer = findViewById<LinearLayout>(R.id.buttons_container)

        val buttonClickListener = View.OnClickListener {
            val fragment = when (it.id) {
                R.id.text_tabs -> TabFragment()
                R.id.text_bottom_nav -> BottomNavFragment()
                else -> return@OnClickListener
            }

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit()
        }

        buttonsContainer
            .children
            .forEach {
                it.setOnClickListener(buttonClickListener)
            }

        supportFragmentManager
            .addOnBackStackChangedListener {
                buttonsContainer.isVisible = supportFragmentManager.backStackEntryCount == 0
            }
    }
}