package com.example.lembretes

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_tarefas.view.*

class TarefaAdaptador(

    private val  todos: MutableList<Tarefas>
) : RecyclerView.Adapter<TarefaAdaptador.TarefaViewHolder>() {

    class TarefaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {

        return TarefaViewHolder(

            LayoutInflater.from(parent.context).inflate(
                R.layout.item_tarefas,
                parent,
                false
            )
        )
    }


    fun addTodo(todo: Tarefas){
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteTodo(){
        todos.removeAll{todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }



    private fun toggleStrikeThrough(tvTarefaTitle: TextView, isChecked: Boolean){

        if(isChecked){
            tvTarefaTitle.paintFlags = tvTarefaTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else{
            tvTarefaTitle.paintFlags = tvTarefaTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }



    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {

        val curTodo = todos[position]
        holder.itemView.apply {
            tvTarefaTitle.text = curTodo.title
            cbDone.isChecked = curTodo.isChecked
            toggleStrikeThrough(tvTarefaTitle, curTodo.isChecked)
            cbDone.setOnCheckedChangeListener { _ , isChecked ->
                toggleStrikeThrough(tvTarefaTitle, isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }
        }
    }
    override fun getItemCount(): Int {
        return todos.size
    }
}
