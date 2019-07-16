package daniel.rivero.homematters.presentation.common.utils

import daniel.rivero.homematters.domain.TaskEffort
import daniel.rivero.homematters.domain.UserEffort

object EffortResolver {

    fun getUserEffort(value: Int) = when(value) {
        0 -> UserEffort.XS
        1 -> UserEffort.S
        2 -> UserEffort.M
        3 -> UserEffort.L
        4 -> UserEffort.XL
        else -> UserEffort.XXL
    }

    fun getProgress(effort: UserEffort): Int = when(effort) {
        UserEffort.XS -> 0
        UserEffort.S -> 1
        UserEffort.M -> 2
        UserEffort.L -> 3
        UserEffort.XL -> 4
        UserEffort.XXL -> 5
    }

    fun getTaskEffort(value: Int) = when(value) {
        0 -> TaskEffort.XS
        1 -> TaskEffort.S
        2 -> TaskEffort.M
        3 -> TaskEffort.L
        4 -> TaskEffort.XL
        else -> TaskEffort.XXL
    }

    fun getProgress(effort: TaskEffort): Int = when(effort) {
        TaskEffort.XS -> 0
        TaskEffort.S -> 1
        TaskEffort.M -> 2
        TaskEffort.L -> 3
        TaskEffort.XL -> 4
        TaskEffort.XXL -> 5
    }

}