package com.greatcoder.androidchart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.collections.ArrayList

class BarChartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_chart)

        val barChart = this.findViewById<BarChart>(R.id.barChart)

        val lsData: MutableList<ChartModelData> = ArrayList()

        val random = Random();

        for (i in 1..6) {
            lsData.add(ChartModelData("GDG", random.nextInt(500)))
        }

        barChart.bindDataSource(lsData)
        barChart.setShowLabel(true)
    }
}