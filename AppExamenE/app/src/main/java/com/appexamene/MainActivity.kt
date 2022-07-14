package com.appexamene

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appexamene.databinding.ActivityMainBinding
import com.appexamene.databinding.FragmentHomeBinding

class MainActivity : AppCompatActivity() {

    //variable para acceder a los elementos de la pantalla activity_main.xml
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        //Se establece el enlace con la vista xml mediante el objeto binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        }
}