package com.example.intelligent_shopping_cart.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.intelligent_shopping_cart.model.UserProfileData
import com.example.intelligent_shopping_cart.ui.screens.personal.getCurrentLoginUserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class UserIntent {
    data class UpdateAge(val age: Int) : UserIntent()
    data class SwitchGenders(val gender: Boolean) : UserIntent()
    data class UpdatePhone(val phone: String) : UserIntent()
    data class UpdateEmail(val email: String) : UserIntent()
}

@HiltViewModel
class UserViewModel @Inject constructor() : ViewModel() {
    private var _uiState = mutableStateOf(getCurrentLoginUserProfile())
    val uiState: State<UserProfileData> = _uiState

    fun dispatch(intent: UserIntent) = reducer(_uiState.value, intent)

    private fun reducer(model: UserProfileData, intent: UserIntent) {
        return when (intent) {
            is UserIntent.UpdateAge -> emit(model.copy(age = intent.age))
            is UserIntent.SwitchGenders -> emit(model.copy(gender = if (intent.gender) "男" else "女"))
            is UserIntent.UpdateEmail -> emit(model.copy(email = intent.email))
            is UserIntent.UpdatePhone -> emit(model.copy(phone = intent.phone))
        }
    }

    private fun emit(state: UserProfileData) {
        _uiState.value = state
    }
}