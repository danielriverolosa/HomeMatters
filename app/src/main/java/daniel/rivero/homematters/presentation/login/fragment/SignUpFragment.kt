package daniel.rivero.homematters.presentation.login.fragment

import android.os.Bundle
import android.view.View
import daniel.rivero.homematters.R
import daniel.rivero.homematters.infrastructure.ContentView
import daniel.rivero.homematters.infrastructure.di.component.ViewComponent
import daniel.rivero.homematters.presentation.base.BaseViewModelFragment
import daniel.rivero.homematters.presentation.login.event.SignUpEvent
import daniel.rivero.homematters.presentation.login.viewmodel.SignUpViewModel
import daniel.rivero.homematters.presentation.login.viewstate.SignUpViewState
import kotlinx.android.synthetic.main.fragment_sign_up.*

@ContentView(R.layout.fragment_sign_up)
class SignUpFragment: BaseViewModelFragment<SignUpViewModel, SignUpViewState>() {

    companion object {
        fun getInstance() = SignUpFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        continueButton.setOnClickListener {
            viewModel.onEvent(SignUpEvent.Continue(
                nameInput.text.toString(),
                emailInput.text.toString(),
                passwordInput.text.toString(),
                confirmPasswordInput.text.toString()
            ))
        }
    }

    override fun initializeInjector(viewComponent: ViewComponent) {
        viewComponent.inject(this)
    }

    override fun render(viewState: SignUpViewState) {

    }
}