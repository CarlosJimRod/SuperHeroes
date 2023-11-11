package com.cjimenezro.superheroes.features.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.cjimenezro.superheroes.R
import com.cjimenezro.superheroes.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun showMessage() {
        Snackbar.make(binding.root, getString(R.string.app_name), Snackbar.LENGTH_SHORT).show()
    }
}
