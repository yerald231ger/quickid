package org.qid

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.qid.core.infrastructure.FileRepository
import org.qid.core.models.IdentityFile
import org.qid.di.AppContainer
import org.qid.di.QuickIdDatabaseFactory

class IndexViewModel(private val repository: FileRepository) : ViewModel() {

    val indexUiState: StateFlow<IndexUiState> =
        combine(repository.getTopFiles(), repository.getRecentFiles()) { topFiles, recentFiles ->
            IndexUiState(topFiles, recentFiles)
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            IndexUiState()
        )

    fun saveFile(identityFile: IdentityFile) {
        viewModelScope.launch {
            repository.saveFile(identityFile)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as Application
                val factory = AppContainer(QuickIdDatabaseFactory(app))
                val repository = factory.fileRepository
                IndexViewModel(repository)
            }
        }
    }
}

data class IndexUiState(
    val topIdentityFiles: List<IdentityFile> = listOf(),
    val recentIdentityFiles: List<IdentityFile> = listOf()
)