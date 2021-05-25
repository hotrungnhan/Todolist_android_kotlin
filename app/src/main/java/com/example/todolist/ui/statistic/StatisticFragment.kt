package com.example.todolist.ui.statistic

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.example.todolist.R
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.example.todolist.ui.home.TodoListViewModel


class StatisticFragment : Fragment() {

    lateinit var dataviewmodel: TodoListViewModel;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_statistic, container, false)
        dataviewmodel = ViewModelProvider(this.requireActivity()).get(TodoListViewModel::class.java)
        //any chart
        val pie = AnyChart.pie()
        pie.palette(listOf("#FF6347", "#00BFFF").toTypedArray())
        var list = listOf(
            ValueDataEntry(
                "Done", dataviewmodel.countDone()
            ),
            ValueDataEntry("Undone", dataviewmodel.countUndone())
        )
        pie.data(list);
        val anyChartView =
            root.findViewById(R.id.any_chart_view) as AnyChartView
        anyChartView.setChart(pie);
        return root
    }

}
