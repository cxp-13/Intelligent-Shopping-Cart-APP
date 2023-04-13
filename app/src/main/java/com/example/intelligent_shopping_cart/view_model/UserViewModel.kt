package com.example.intelligent_shopping_cart.view_model

import android.net.Uri
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.intelligent_shopping_cart.logic.repository.UserRepository
import com.example.intelligent_shopping_cart.logic.utils.UserFactory
import com.example.intelligent_shopping_cart.model.User
import com.example.intelligent_shopping_cart.ui.components.AppScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


sealed class LoginStatus() {
    object Error : LoginStatus()
    object SignIn : LoginStatus()
}

sealed class UserIntent {
    data class UpdateAge(val age: Int) : UserIntent()
    data class SwitchGenders(val gender: Boolean) : UserIntent()
    data class UpdatePhone(val phone: String) : UserIntent()
    data class UpdateEmail(val email: String) : UserIntent()
    data class RegisteredUsers(val username: String, val password: String) : UserIntent()
    data class InputUserName(val username: String) : UserIntent()
    data class InputPassword(val password: String) : UserIntent()
    data class InputRepeatPassword(val repeatPassword: String) : UserIntent()
    object SwitchPwdHidden : UserIntent()
    data class SwitchOpenDropMenu @OptIn(ExperimentalComposeUiApi::class) constructor(var keyboardController: SoftwareKeyboardController) :
        UserIntent()

    data class LoginBtnClick(val navController: NavHostController) : UserIntent()
    data class RegisterBtnClick(val navController: NavHostController) : UserIntent()
    data class NavToRegisterBtnClick(val navController: NavHostController) : UserIntent()
    data class LoadImageUri(var uri: Uri) : UserIntent()
    object CloseDropMenu : UserIntent()
    data class DropMenuItemClick(val user: User) : UserIntent()
}

data class UserUiState constructor(
    var user: User = User(),
    var users: List<User> = emptyList(),
    var openDropMenu: Boolean = false,
    var username: String = "",
    var password: String = "",
    var passwordHidden: Boolean = true,
    val repeatPassword: String = "",
    val imageUri: Uri? = null,
) {
    val isUserHasExistByName: Boolean
        get() {
            for (user in users) {
                if (user.nickname == username) {
                    return true
                }
            }
            return false
        }

    val isUserHasExist: Boolean
        get() {
            if (username.isEmpty() or password.isEmpty()) return true
            for (user in users) {
                if (user.nickname == username && user.password == password) {
                    return true
                }
            }
            return false
        }

    val isPwdInconsistent: Boolean = password == repeatPassword

}

@HiltViewModel
class UserViewModel @Inject constructor(val userRepository: UserRepository) : ViewModel() {
    private var _uiState = MutableStateFlow(UserUiState())
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val userList = UserFactory.createDummyUsers(5)
            userRepository.insertAllUsers(userList)
            userRepository.getAllUsers().collect {
                _uiState.value.users = it
            }
        }
    }

    fun dispatch(intent: UserIntent) = reducer(_uiState.value, intent)


    private fun UserUiState.update(block: UserUiState.() -> UserUiState) {
        _uiState.value = this@update.block()
    }

    @OptIn(ExperimentalComposeUiApi::class)
    private fun reducer(uiState: UserUiState, intent: UserIntent) {
        return when (intent) {
            is UserIntent.UpdateAge -> uiState.update {
                copy(user = uiState.user.copy(age = intent.age))
            }
            is UserIntent.SwitchGenders -> uiState.update {
                copy(user = uiState.user.copy(gender = if (intent.gender) "male" else "female"))
            }
            is UserIntent.UpdateEmail -> uiState.update {
                copy(user = uiState.user.copy(email = intent.email))
            }
            is UserIntent.UpdatePhone -> uiState.update {
                copy(user = uiState.user.copy(phone = intent.phone))
            }
            is UserIntent.RegisteredUsers -> uiState.update {
                copy(
                    user = uiState.user.copy(
                        nickname = intent.username,
                        password = intent.password
                    )
                )
            }
            is UserIntent.InputPassword -> uiState.update {
                copy(password = intent.password)
            }
            is UserIntent.InputUserName -> uiState.update {
                copy(username = intent.username)
            }
            is UserIntent.SwitchOpenDropMenu -> uiState.update {
                intent.keyboardController.hide()
                copy(openDropMenu = !_uiState.value.openDropMenu)
            }
            UserIntent.SwitchPwdHidden -> uiState.update {
                copy(passwordHidden = !passwordHidden)
            }
            is UserIntent.InputRepeatPassword -> uiState.update {
                copy(repeatPassword = intent.repeatPassword)
            }
            is UserIntent.LoadImageUri -> uiState.update {
                copy(imageUri = intent.uri)
            }

            UserIntent.CloseDropMenu -> uiState.update {
                copy(openDropMenu = false)
            }
            is UserIntent.LoginBtnClick -> loginBtnClick(intent.navController)
            is UserIntent.NavToRegisterBtnClick -> navToRegisterBtnClick(intent.navController)

            is UserIntent.RegisterBtnClick -> registerBtnClick(intent.navController)

            is UserIntent.DropMenuItemClick -> uiState.update {
                copy(username = intent.user.nickname, password = intent.user.password)
            }
        }
    }

    private fun navToRegisterBtnClick(navController: NavHostController) {
        navController.navigate(AppScreen.register)
    }


    private fun loginBtnClick(navController: NavHostController) {
        if (uiState.value.isUserHasExist) {
            navController.navigate(AppScreen.home) {
                popUpTo(AppScreen.login) { inclusive = true }
            }
        }
    }

    private fun registerBtnClick(navController: NavHostController) {
        if (!uiState.value.isUserHasExist && uiState.value.isPwdInconsistent) {
            navController.navigate(AppScreen.login)
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    val user = UserFactory.createDummyUsers(1)[0]
                    userRepository.insertUser(
                        user.copy(
                            nickname = _uiState.value.username,
                            password = _uiState.value.password
                        )
                    )
                }

            }
        }
    }

    fun clear() {
        _uiState.value = _uiState.value.copy(username = "", password = "", repeatPassword = "")
    }
}

