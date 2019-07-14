package daniel.rivero.homematters.domain

sealed class TaskEffort(val value: Int, val description: String) {

    companion object {
        fun getEffortFromValue(value: Int): TaskEffort? {
            return TaskEffort::class.sealedSubclasses
                .map { it.objectInstance as TaskEffort }
                .firstOrNull { it.value == value }
        }
    }

    object XS: TaskEffort(2, "XS")
    object S: TaskEffort(5, "S")
    object M: TaskEffort(10, "M")
    object L: TaskEffort(15, "L")
    object XL: TaskEffort(20, "XL")
    object XXL: TaskEffort(30, "XXL")

}