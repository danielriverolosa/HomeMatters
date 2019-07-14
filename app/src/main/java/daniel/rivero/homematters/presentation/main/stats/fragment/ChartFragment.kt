package daniel.rivero.homematters.presentation.main.stats.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter
import daniel.rivero.homematters.R
import daniel.rivero.homematters.domain.EffortStat
import daniel.rivero.homematters.infrastructure.ContentView
import daniel.rivero.homematters.infrastructure.di.component.ViewComponent
import daniel.rivero.homematters.presentation.base.BaseViewModelFragment
import daniel.rivero.homematters.presentation.main.stats.event.ChartEvent
import daniel.rivero.homematters.presentation.main.stats.utils.CustomValueFormatter
import daniel.rivero.homematters.presentation.main.stats.viewmodel.ChartViewModel
import daniel.rivero.homematters.presentation.main.stats.viewstate.ChartViewState
import kotlinx.android.synthetic.main.fragment_chart.*

@ContentView(R.layout.fragment_chart)
class ChartFragment : BaseViewModelFragment<ChartViewModel, ChartViewState>() {

    companion object {
        fun getInstance() = ChartFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onEvent(ChartEvent.Initialize)
        setupBarChart()
    }

    private fun setupBarChart() {
        barChart.apply {
            setDrawBorders(false)
            setDrawGridBackground(false)
            isScrollContainer = true
            axisLeft.setDrawAxisLine(true)
            axisLeft.setDrawGridLines(true)
            axisLeft.xOffset = 16f
            axisLeft.axisMinimum = 0f
            axisRight.setDrawLabels(false)
            axisRight.setDrawAxisLine(false)
            axisRight.setDrawGridLines(false)
            xAxis.setDrawAxisLine(false)
            xAxis.setDrawGridLines(false)
            xAxis.isGranularityEnabled = true
            xAxis.granularity = 1f
            description.isEnabled = false
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            description.isEnabled = false
            setPinchZoom(false)

            legend.isEnabled = false
        }
    }

    override fun initializeInjector(viewComponent: ViewComponent) {
        viewComponent.inject(this)
    }

    override fun render(viewState: ChartViewState) {
        when (viewState) {
            is ChartViewState.LoadData -> loadChartData(viewState.stats)
        }
    }

    private fun loadChartData(stats: List<EffortStat>) {
        barChart.data = BarData().apply {
            addDataSet(buildBarDataSet(stats))
        }
        setXAxisParams(stats)
        setYAxisParams()
        barChart.animateY(500, Easing.EaseOutBounce)
    }

    private fun buildBarDataSet(stats: List<EffortStat>): BarDataSet {
        return BarDataSet(
            stats.mapIndexed { index, effortStat -> BarEntry(index.toFloat(), effortStat.effort.toFloat()) }, ""
        ).apply {
            setupBarData(this)
        }
    }

    private fun setupBarData(barDataSet: BarDataSet) {
        context?.let {
            barDataSet.colors = getColorList(it)
            barDataSet.isHighlightEnabled = false
            barDataSet.setDrawValues(false)
        }
    }

    private fun getColorList(context: Context) = listOf(
        ContextCompat.getColor(context, R.color.chart_color_cyan),
        ContextCompat.getColor(context, R.color.chart_color_purple),
        ContextCompat.getColor(context, R.color.chart_color_teal),
        ContextCompat.getColor(context, R.color.chart_color_indigo),
        ContextCompat.getColor(context, R.color.chart_color_green),
        ContextCompat.getColor(context, R.color.chart_color_blue)
    )

    private fun setXAxisParams(stats: List<EffortStat>) {
        barChart.apply {
            xAxis.axisMinimum = -1f
            xAxis.axisMaximum = stats.size.toFloat()
            xAxis.valueFormatter = CustomValueFormatter(stats)

            xAxis.spaceMin = 0.5f

            xAxis.setCenterAxisLabels(false)
        }
    }

    private fun setYAxisParams() {
        barChart.axisLeft.apply {
            valueFormatter = DefaultAxisValueFormatter(0)
        }
    }

}