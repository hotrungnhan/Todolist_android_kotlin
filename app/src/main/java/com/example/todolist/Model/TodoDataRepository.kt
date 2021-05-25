package com.example.todolist.Model

import androidx.lifecycle.LiveData

class TodoDataRepository(private val todo: TodoDao) {
    var readAlldata: LiveData<MutableList<TodoData>> = todo.getAll()
    suspend fun addTodo(data: TodoData) {
        todo.insertOne(data)
    }

    suspend fun updateTodo(data: TodoData) {
        todo.updateOne(data)
    }

    suspend fun deleteOne(data: TodoData) {
        todo.deleteOne(data)
    }

    fun countUndone(): Int {
        return todo.countUnDone()
    }

    fun countDone(): Int {
        return todo.countDone()
    }
}