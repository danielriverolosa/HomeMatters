package daniel.rivero.homematters.presentation.common.utils


import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val locale  = Locale("es","ES")

fun Date?.formatDate(): String {
    val formatter = SimpleDateFormat("dd 'de' MMMM 'de' yyyy", locale)
    return formatter.format(this)
}

fun Date?.formatServiceDate(): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", locale)
    return formatter.format(this)
}

fun String?.parseFormalDate(): Date {
    val formatter = SimpleDateFormat("dd 'de' MMMM 'de' yyyy", locale)
    return formatter.parse(this)
}

fun String.parseDate(): Date {
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", locale)
    return formatter.parse(this)
}