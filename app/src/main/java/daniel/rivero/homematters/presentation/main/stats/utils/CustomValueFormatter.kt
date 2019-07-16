package daniel.rivero.homematters.presentation.main.stats.utils

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter
import daniel.rivero.homematters.domain.EffortStat
import daniel.rivero.homematters.presentation.common.utils.formatDateMonth
import kotlin.math.abs

class CustomValueFormatter(
    private val stats: List<EffortStat>
): ValueFormatter() {

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        val position = abs(value.toInt())
        return if (value.toInt() >= 0 && position < stats.size) {
            stats[position].date.formatDateMonth().toUpperCase()
        } else ""
    }

}