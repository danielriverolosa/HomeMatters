package daniel.rivero.homematters.domain.exception


open class ConnectivityException : RepositoryException {
    constructor() : super()
    constructor(message: String) : super(message)
}
