package daniel.rivero.homematters.domain.exception


class HttpException(httpError: Int) : RuntimeException("Http error: $httpError")