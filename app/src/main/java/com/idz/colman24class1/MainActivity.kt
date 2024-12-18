package com.idz.colman24class1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    var fragmentOne: StudentsListFragment? = null
    var fragmentTwo: BlueFragment? = null
    var fragmentThree: BlueFragment? = null
    var fragmentFour: BlueFragment? = null

    var buttonOne: Button? = null
    var buttonTwo: Button? = null
    var buttonThree: Button? = null
    var buttonFour: Button? = null

    var fragment: BlueFragment? = null
    var inDisplayFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // TODO: 1 - Set MainActivity Launcher ✅
        // TODO: 2 - Create fragment from xml ✅
        // TODO: 3 - Create a fragment programmatically ✅
        // TODO: 4 - Manage nav args ✅
        // TODO: 5 - Create a tab bar with multiple fragments ✅
        // TODO: 6 - Refactor students list
        // TODO: 7 - GPS



        fragmentOne = StudentsListFragment()
        fragmentTwo = BlueFragment.newInstance("2️⃣")
        fragmentThree = BlueFragment.newInstance("3️⃣")
        fragmentFour = BlueFragment.newInstance("4️⃣")

        buttonOne = findViewById(R.id.main_acitivity_button_one)
        buttonTwo = findViewById(R.id.main_acitivity_button_two)
        buttonThree = findViewById(R.id.main_acitivity_button_three)
        buttonFour = findViewById(R.id.main_acitivity_button_four)

        buttonOne?.setOnClickListener {
            addFragment(fragmentOne)
        }

        buttonTwo?.setOnClickListener {
            addFragment(fragmentTwo)
        }

        buttonThree?.setOnClickListener {
            addFragment(fragmentThree)
        }

        buttonFour?.setOnClickListener {
            addFragment(fragmentFour)
        }

        addFragment(fragmentOne)

    }

    fun addFragment(fragment: Fragment?) {
        fragment?.let {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.main_activity_frame_layout, it)

                inDisplayFragment?.let {
                    remove(it)
                }

                addToBackStack("TAG")
                commit()
            }
        }
        inDisplayFragment = fragment
    }

    fun removeFragment() {
        fragment?.let {
            supportFragmentManager.beginTransaction().apply {
                remove(it)
                commit()
            }
        }
        fragment = null
    }
}