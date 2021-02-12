package com.greatcoder.androidchart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.collections.ArrayList

class PieChartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pie_chart)

        /*
        pie chart 数据绑定代码
         */
        this.supportActionBar!!.title = "Pie Chart"

        val pieChart = this.findViewById<PieChart>(R.id.pieChart)

        val lsData: MutableList<ChartModelData> = ArrayList()

        val random = Random();

        for (i in 1..6) {
            lsData.add(ChartModelData("GDG$i", random.nextInt(500)))
        }

        pieChart.bindDataSource(lsData)

    }
}