package com.project.sql_delight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.project.sql_delight.databinding.ActivityAddBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddActivity : AppCompatActivity() {
    private var binding:ActivityAddBinding?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

binding!!.savebtn.setOnClickListener {
    val title=binding!!.edtTitle.text.toString()
    val desc=binding!!.edtDesc.text.toString()
    if (validated()){
        SaveDatas(title,desc)


    }


}

    }

    private fun validated():Boolean{
        if (binding!!.edtTitle.text.toString().equals("")){
            binding!!.edtTitle.setError("enter tittle")
            return false
        }
        else if (binding!!.edtDesc.text.toString().equals("")){
            binding!!.edtDesc.setError("enter description")
            return false
        }
        return true


    }
    private fun SaveDatas(title:String,desc:String){
        val viewModel:NotesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.insertNote(title, desc)
            startActivity(Intent(this@AddActivity, MainActivity::class.java))
            finish()
        }
    }
}