package com.example.todolist.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todolist.Model.TodoData
import com.example.todolist.R

interface TodoListAdapterListener {
    fun onDataChange(data: TodoData)
    fun onDataRemove(data: TodoData)
}

class TodoListAdapter() :
    RecyclerView.Adapter<TodoListAdapter.TodoItemViewHolder>() {
    private var listdata: MutableList<TodoData> = mutableListOf()
    private var Listener: TodoListAdapterListener? = null;

    class TodoItemViewHolder(itemView: View) : ViewHolder(itemView) {
        var CheckBox: CheckBox;
        var Text: TextView;

        init {
            Text = itemView.findViewById(R.id.todo_item_textview)
            CheckBox = itemView.findViewById(R.id.todo_item_checkbox)
        }
    }

    fun setOnDataChange(l: TodoListAdapterListener) {
        Listener = l;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_todolist, parent, false);
        return TodoItemViewHolder(view);
    }

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        holder.Text.setText(listdata[position].Text);
        holder.CheckBox.isChecked = listdata[position].IsDone;
        holder.CheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            listdata[position].IsDone = isChecked;
            Listener?.onDataChange(listdata[position]);
        }
    }


    fun setData(list: MutableList<TodoData>) {
        listdata = list;
        notifyDataSetChanged();
    }

    fun removeData(position: Int) {
        Listener?.onDataRemove(listdata[position]);
    }

    override fun getItemCount(): Int = listdata.size;
}