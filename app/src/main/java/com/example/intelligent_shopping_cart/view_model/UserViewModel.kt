package com.example.intelligent_shopping_cart.view_model

import android.net.Uri
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.intelligent_shopping_cart.R
import com.example.intelligent_shopping_cart.model.User
import com.example.intelligent_shopping_cart.ui.components.AppScreen
import com.example.intelligent_shopping_cart.ui.screens.personal.getCurrentLoginUserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


sealed class LoginStatus() {
    object Error : LoginStatus()
    object SignIn : LoginStatus()
}

val usersMock = mutableListOf(
    User(
        R.drawable.ava1,
        "user1",
        "motto1",
        "male",
        18,
        "123456789",
        "user1@example.com",
        password = "123456"
    ),
    User(R.drawable.ava2, "user2", "motto2", "female", 22, "987654321", "user2@example.com"),
    User(R.drawable.ava3, "user3", "motto3", "male", 30, "123123123", "user3@example.com"),
    User(R.drawable.ava4, "user4", "motto4", "female", 25, "456456456", "user4@example.com"),
    User(R.drawable.ava5, "user5", "motto5", "male", 28, "789789789", "user5@example.com")
)

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
    var user: User = getCurrentLoginUserProfile(),
    val users: MutableList<User> = usersMock,
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
class UserViewModel @Inject constructor() : ViewModel() {
    private var _uiState = MutableStateFlow(UserUiState())
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

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
                copy(repeatPassword = repeatPassword)
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
                copy(username = user.nickname, password = user.password)
            }
        }
    }

//    private fun dropMenuItemClick(user: User) {
//        _uiState.value = _uiState.value.copy(username = user.nickname, password = user.password)
//    }

//    private fun closeDropMenu() {
//        _uiState.value = _uiState.value.copy(openDropMenu = false)
//    }

//    private fun loadImageUri(uri: Uri) {
//        _uiState.value = _uiState.value.copy(imageUri = uri)
//    }

//    private fun inputUserName(username: String) {
//        _uiState.value = _uiState.value.copy(username = username)
//    }

//    private fun inputPassword(password: String) {
//        _uiState.value = _uiState.value.copy(password = password)
//    }

//    private fun inputRepeatPassword(repeatPassword: String) {
//        _uiState.value = _uiState.value.copy(repeatPassword = repeatPassword)
//    }

//    @OptIn(ExperimentalComposeUiApi::class)
//    private fun dropMenuBtnClick(keyboardController: SoftwareKeyboardController) {
//        keyboardController.hide()
//        _uiState.value = _uiState.value.copy(openDropMenu = !_uiState.value.openDropMenu)
//    }

//    private fun passwordHiddenBtnClick() {
//        _uiState.value = _uiState.value.copy(passwordHidden = !_uiState.value.passwordHidden)
//    }

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
        }
    }

    fun clear() {
        _uiState.value = UserUiState()
    }

    private fun addUser(): Boolean {
        for (user in _uiState.value.users) {
            if (user.nickname == _uiState.value.username) {
                return false // 用户名已存在，添加失败
            }
        }
        _uiState.value = _uiState.value.apply {
            _uiState.value.users.add(
                User(
                    nickname = _uiState.value.username,
                    password = _uiState.value.password
                )
            )
            copy(user = _uiState.value.user)
        }

        return true // 添加成功
    }


}

