package org.qid.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EditIdentityFileViewModel(
    private val nameValidator: NameValidator,
    private val descriptionValidator: DescriptionValidator
) : ViewModel() {

    private val _state = MutableStateFlow(EditIdentityFileViewState())
    val state: StateFlow<EditIdentityFileViewState> = _state

    private fun saveIdentityFile() {
        val nameValidationResult = nameValidator.validate(_state.value.name)
        val descriptionValidationResult = descriptionValidator.validate(_state.value.description)

        val hasErrors = listOf(
            nameValidationResult,
            descriptionValidationResult
        ).any { !it.isValid }


        if (hasErrors) {
            _state.value = _state.value.copy(
                nameError = nameValidationResult.errorMessage,
                descriptionError = descriptionValidationResult.errorMessage,
                isModelValid = true
            )
            viewModelScope.launch {

            }
        }
    }

    fun onEvent(event: EditIdentityFileEvent) {
        when (event) {
            is EditIdentityFileEvent.NameChanged -> {
                _state.value = _state.value.copy(name = event.name)
                val validationResult = nameValidator.validate(event.name)
                if (validationResult.isValid)
                    _state.value = _state.value.copy(
                        nameError = null,
                        isModelValid = true
                    )
                else
                    _state.value = _state.value.copy(
                        nameError = validationResult.errorMessage,
                        isModelValid = false
                    )

            }

            is EditIdentityFileEvent.DescriptionChanged -> {
                _state.value = _state.value.copy(description = event.description)
                val validationResult = descriptionValidator.validate(event.description)
                if (validationResult.isValid) {
                    _state.value = _state.value.copy(
                        descriptionError = null,
                        isModelValid = true
                    )
                } else {
                    _state.value =
                        _state.value.copy(
                            descriptionError = validationResult.errorMessage,
                            isModelValid = false
                        )
                }
            }

            is EditIdentityFileEvent.Save -> {
                saveIdentityFile()
            }
        }
    }
}

sealed class EditIdentityFileEvent {
    data class NameChanged(val name: String) : EditIdentityFileEvent()
    data class DescriptionChanged(val description: String) : EditIdentityFileEvent()
    data object Save : EditIdentityFileEvent()
}

data class EditIdentityFileViewState(
    val name: String = "",
    val nameError: String? = null,
    val description: String = "",
    val descriptionError: String? = null,
    val importance: String = "",
    val importanceError: String? = null,
    val type: String = "",
    val typeError: String? = null,
    val isModelValid: Boolean = false
)

class NameValidator {
    fun validate(name: String): ValidationResult {
        if (name.isBlank()) {
            return ValidationResult(false, "Name cannot be empty")
        }
        if (name.length > 50) {
            return ValidationResult(false, "Name cannot be longer than 16 characters")
        }
        return ValidationResult(true, null)
    }
}

class DescriptionValidator {
    fun validate(description: String): ValidationResult {
        if (description.length > 256) {
            return ValidationResult(false, "Description cannot be longer than 256 characters")
        }

        return ValidationResult(true, null)
    }
}

data class ValidationResult(
    val isValid: Boolean,
    val errorMessage: String?
)