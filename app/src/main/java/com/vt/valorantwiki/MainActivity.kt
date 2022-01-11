package com.vt.valorantwiki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vt.valorantwiki.databinding.ActivityMainBinding
import com.vt.valorantwiki.view.AgentsFragment
import com.vt.valorantwiki.view.MainFragment

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.my_fragment_container, MainFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
}