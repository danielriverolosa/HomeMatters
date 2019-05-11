package daniel.rivero.homematters.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

object ViewModelFactory {
    fun <T: ViewModel> createFor(viewModel: T) = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(viewModel::class.java)) {
                return viewModel as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}