package com.example.todolist.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.Model.TodoData
import com.example.todolist.R
import com.example.todolist.ui.adapter.TodoListAdapter
import com.example.todolist.ui.adapter.TodoListAdapterListener
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {

    lateinit var dataviewmodel: TodoListViewModel;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_todolist, container, false);
        //find view
        val recyclerview = root.findViewById<RecyclerView>(R.id.recyclerView);
        val button = root.findViewById<FloatingActionButton>(R.id.floating_add_button)
        //recycle view;
        val adapter = TodoListAdapter();
        adapter.notifyDataSetChanged()
        recyclerview.adapter = adapter;
        adapter.setOnDataChange(object : TodoListAdapterListener {
            override fun onDataChange(data: TodoData) {
                dataviewmodel.updateTodo(data);
            }

            override fun onDataRemove(data: TodoData) {
                dataviewmodel.deleteTodo(data);
            }

        })
        recyclerview.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        dataviewmodel =
            ViewModelProvider(this.requireActivity()).get(TodoListViewModel::class.java);
        dataviewmodel.ReadAllData.observe(viewLifecycleOwner, { todo ->
            adapter.setData(todo);
        })

        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = recyclerview.adapter as TodoListAdapter
                adapter.removeData(viewHolder.adapterPosition);
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerview)
        // float button event
        button.setOnClickListener { v: View ->
            val dialogLayout: View = layoutInflater.inflate(R.layout.dialog_add_todo, null);
            val editext = dialogLayout.findViewById<EditText>(R.id.dialog_add_editext)
            val dialog =
                AlertDialog.Builder(context)
                    .setView(dialogLayout)
                    .setNegativeButton(
                        R.string.todo_dialog_cancel_button
                    ) { dialog, id ->
                    }
                    .setPositiveButton(
                        R.string.todo_dialog_add_button
                    ) { dia, id ->
                        if (editext.text.isNotEmpty()) {
                            var data = TodoData(editext.text.toString());
                            dataviewmodel.addTodo(data);
                            Toast.makeText(this.context, "Success", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Toast.makeText(this.context, "Todos không thể rỗng", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }.setTitle(R.string.dialog_title)
                    .create();
            dialog.show();
        };
        return root
    }
}
