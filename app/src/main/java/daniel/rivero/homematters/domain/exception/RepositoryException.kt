package daniel.rivero.homematters.domain.exception


open class RepositoryException : RuntimeException {

    constructor() : super()

    constructor(message: String) : super(message)
}