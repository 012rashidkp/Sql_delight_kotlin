package com.project.sql_delight

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.sql_delight.databinding.NotesListLayoutBinding
import demo.notesdb.NotesEntity

class NotesAdapter(val context: Context,val items:List<NotesEntity>,val listener:DbDeleteItems):RecyclerView.Adapter<NotesAdapter.MyVIewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVIewHolder {
        val binding=NotesListLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyVIewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyVIewHolder, position: Int) {
    val datas=items[position]
        with(holder){
            binding.txtTitle.text=datas.title
            binding.txtDesc.text=datas.description
            binding.deletebtn.setOnClickListener {
                listener.DeleteByid(datas.id)
            }

        }

    }

    override fun getItemCount(): Int {
  return items.size
    }
    class MyVIewHolder(val binding:NotesListLayoutBinding):RecyclerView.ViewHolder(binding.root) {

    }
}