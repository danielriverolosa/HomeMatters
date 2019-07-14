package daniel.rivero.homematters.presentation.main.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import daniel.rivero.homematters.R
import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.infrastructure.di.component.ViewComponent
import daniel.rivero.homematters.presentation.base.BaseActivity
import daniel.rivero.homematters.presentation.base.utils.setParamsByClass
import daniel.rivero.homematters.presentation.main.activity.viewmodel.MainViewModel
import daniel.rivero.homematters.presentation.main.activity.viewstate.MainViewState
import daniel.rivero.homematters.presentation.main.activity.event.MainEvent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: BaseActivity<MainViewModel, MainViewState>() {

    private var displayHomeAsUpEnabled = false

    companion object {
        fun getIntent(context: Context, user: User) = Intent(context, MainActivity::class.java).apply {
            setParamsByClass(user)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
        initNavigationView()
        viewModel.onEvent(MainEvent.Initialize)
        viewModel.onEvent(MainEvent.Navigation(R.id.item_calendar))
    }

    override fun initializeInjector(viewComponent: ViewComponent) {
        viewComponent.inject(this)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled)

        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initNavigationView() {
        navigationView.selectedItemId = R.id.item_calendar
        navigationView.setOnNavigationItemSelectedListener {
            if (navigationView.selectedItemId != it.itemId) {
                viewModel.onEvent(MainEvent.Navigation(it.itemId))
                return@setOnNavigationItemSelectedListener true
            }
            return@setOnNavigationItemSelectedListener false
        }
    }

    override fun render(viewState: MainViewState) {
        // nothing to render
    }
}