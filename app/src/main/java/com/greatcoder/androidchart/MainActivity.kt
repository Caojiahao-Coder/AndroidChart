package com.greatcoder.androidchart

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        //创建一个数据源
//        val lsData: MutableList<PieDataModel> = ArrayList();
//
//        val random = Random()
//
//        for (i in 0..5) {
//            val data = PieDataModel("GDG", random.nextInt(300))
//            lsData.add(data)
//        }
//
//        //绑定到饼图
//        val pieChart: PieChart = findViewById(R.id.pieChart)
//        pieChart.bindDataSource(lsData)
//
//        //绑定到柱状图
//        val barChart: BarChart = findViewById(R.id.barChart)
//        barChart.bindDataSource(lsData)
//        barChart.setShowLabel(true)

        this.findViewById<Button>(R.id.btnGoToPieChart).setOnClickListener {
            startActivity(
                Intent().setClass(
                    this,
                    PieChartActivity::class.java
                )
            )
        }

        this.findViewById<Button>(R.id.btnGoToBarChart).setOnClickListener {
            startActivity(
                Intent().setClass(this, BarChartActivity::class.java)
            )
        }
    }
}