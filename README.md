# AndroidChart🤖

## 当前完成的进度😡
- [X] 饼状图
- [x] 柱状图

## 饼状图

![实现效果分层](https://emier-md-pics2.oss-cn-beijing.aliyuncs.com/img/截屏2021-02-12下午3.27.19.png)

![最终效果图](https://emier-md-pics2.oss-cn-beijing.aliyuncs.com/img/截屏2021-02-12下午3.28.23.png)

```kotlin
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
```

