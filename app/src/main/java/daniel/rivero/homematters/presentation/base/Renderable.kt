package daniel.rivero.homematters.presentation.base


interface Renderable<VS: ViewState> {
    fun render(viewState: VS)
}