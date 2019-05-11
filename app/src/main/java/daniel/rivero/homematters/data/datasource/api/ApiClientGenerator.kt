package daniel.rivero.homematters.data.datasource.api

import kotlin.reflect.KClass

interface ApiClientGenerator {
    fun <T: Any> generateApi(service: KClass<T>): T
}