package org.qid.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.qid.core.infrastructure.FileRepository
import org.qid.core.models.IdentityFile

class IndexViewModel(private val repository: FileRepository) : ViewModel() {

    private val _selectedIdentityFile = MutableStateFlow<IdentityFile?>(null)
    val selectedIdentityFile: StateFlow<IdentityFile?> = _selectedIdentityFile

    val indexUiState: StateFlow<IndexUiState> =
        combine(repository.getTopFiles(), repository.getRecentFiles()) { topFiles, recentFiles ->
            IndexUiState(topFiles, recentFiles)
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            IndexUiState()
        )

    val identityFileUiState: StateFlow<IdentityFileUiState> =
        repository.getFiles(null).map {
            IdentityFileUiState(it)
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            IdentityFileUiState()
        )

    fun saveFile(identityFile: IdentityFile) {
        viewModelScope.launch {
            repository.saveFile(identityFile)
        }
    }

    fun setSelectedIdentityFile(identityFile: IdentityFile) {
        viewModelScope.launch {
            _selectedIdentityFile.emit(identityFile)
        }
    }

}

data class IndexUiState(
    val topIdentityFiles: List<IdentityFile> = listOf(),
    val recentIdentityFiles: List<IdentityFile> = listOf()
)

data class IdentityFileUiState(
    val identityFiles: List<IdentityFile> = listOf()
)
