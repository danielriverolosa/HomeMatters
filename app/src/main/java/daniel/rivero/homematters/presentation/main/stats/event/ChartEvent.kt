package daniel.rivero.homematters.presentation.main.stats.event

import daniel.rivero.homematters.presentation.base.Event

sealed class ChartEvent: Event {
    object Initialize: ChartEvent()
}