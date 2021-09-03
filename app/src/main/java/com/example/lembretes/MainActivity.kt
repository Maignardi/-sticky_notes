package com.example.lembretes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var tarefaAdaptador: TarefaAdaptador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tarefaAdaptador = TarefaAdaptador(mutableListOf())
        rvTarefas.adapter = tarefaAdaptador
        rvTarefas.layoutManager = LinearLayoutManager(this)
        btnAdd.setOnClickListener{
            val todoTitle = etTarefas.text.toString()
            if(todoTitle.isNotEmpty()){
                val todo = Tarefas(todoTitle)
                tarefaAdaptador.addTodo(todo)
                etTarefas.text.clear()
            }
        }
        btnDelete.setOnClickListener{
            tarefaAdaptador.deleteTodo()
        }
    }
}
