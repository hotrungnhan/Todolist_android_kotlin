package com.example.todolist.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.Model.TodoData
import com.example.todolist.Model.TodoDataRepository
import com.example.todolist.helpers.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoListViewModel(app: Application) : AndroidViewModel(app) {
    val ReadAllData: LiveData<MutableList<TodoData>>;
    private val repository: TodoDataRepository

    init {
        val todoDao = AppDatabase.getInstance(app).todoDao();
        repository = TodoDataRepository(todoDao);
        ReadAllData = repository.readAlldata
    }

    fun countDone(): Int {
        return repository.countDone();
    }

    fun countUndone(): Int {
        return repository.countUndone();
    }

    fun updateTodo(data: TodoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTodo(data);
        }
    }

    fun addTodo(data: TodoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTodo(data);
        }
    }

    fun deleteTodo(data: TodoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteOne(data);
        }
    }
}