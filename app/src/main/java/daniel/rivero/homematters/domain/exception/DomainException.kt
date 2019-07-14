package daniel.rivero.homematters.domain.exception

import java.lang.RuntimeException

open class DomainException(message: String): RuntimeException(message)