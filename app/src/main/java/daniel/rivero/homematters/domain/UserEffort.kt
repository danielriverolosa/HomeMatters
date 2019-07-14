package daniel.rivero.homematters.domain

sealed class UserEffort(val value: Int, val description: String) {

    companion object {
        fun getEffortFromValue(value: Int): UserEffort? {
            return UserEffort::class.sealedSubclasses
                .map { it.objectInstance as UserEffort }
                .firstOrNull { it.value == value }
        }
    }

    object XS: UserEffort(15, "XS")
    object S: UserEffort(20, "S")
    object M: UserEffort(30, "M")
    object L: UserEffort(45, "L")
    object XL: UserEffort(60, "XL")
    object XXL: UserEffort(75, "XXL")

}