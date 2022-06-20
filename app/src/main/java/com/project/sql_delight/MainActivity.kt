package com.project.sql_delight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.sql_delight.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var binding:ActivityMainBinding?=null
    lateinit var adapter: NotesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        binding!!.addbtn.setOnClickListener {
            startActivity(Intent(this@MainActivity,AddActivity::class.java))
        }
        val viewModel:NotesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

        CoroutineScope(Dispatchers.Main).launch {
           viewModel.notes.collect { notes->

               adapter= NotesAdapter(this@MainActivity, notes,object :DbDeleteItems{
                   override fun DeleteByid(id: Long) {
                       viewModel.deleteNote(id)
                   }

               })
               binding!!.noteslist.adapter=adapter
               binding!!.noteslist.layoutManager=LinearLayoutManager(this@MainActivity)


           }
        }

    }
}