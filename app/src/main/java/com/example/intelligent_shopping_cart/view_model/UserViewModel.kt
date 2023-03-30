package com.example.intelligent_shopping_cart.view_model

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.intelligent_shopping_cart.R
import com.example.intelligent_shopping_cart.model.User
import com.example.intelligent_shopping_cart.ui.components.AppScreen
import com.example.intelligent_shopping_cart.ui.screens.personal.getCurrentLoginUserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


//val usersMock = listOf(
//    User(
//        R.drawable.ava1,
//        "chen xian ping",
//        "2b吧 哥们",
//        gender = "男",
//        age = 22,
//        phone = "13259934802",
//        email = "1695219012@qq.com",
//    ),
//    User(R.drawable.ava2, "Tony", "冬至"),
//    User(R.drawable.ava3, "Meth", "“做最好的准备  也做最坏的打算”"),
//    User(R.drawable.ava4, "Beatriz", "水母只能在深海度过相对失败的一生"),
//    User(R.drawable.ava5, "香辣鸡腿堡", "请向前走，不要在此停留。konpaku.cn"),
//    User(R.drawable.ava6, "爱丽丝", "逝者如斯夫，不舍昼夜"),
//    User(R.drawable.ava7, "Horizon", "对韭当割，人生几何。"),
//    User(R.drawable.ava8, "鲤鱼", "未曾谋面的也终将会相遇的，慢慢来吧，慢慢约会吧\uD83D\uDC31"),
//    User(
//        R.drawable.ava9,
//        "小太阳",
//        "这个人很懒，什么都没留下。这个人很懒，什么都没留下。这个人很懒，什么都没留下。这个人很懒，什么都没留下。这个人很懒，什么都没留下。这个人很懒，什么都没留下。这个人很懒，什么都没留下。这个人很懒，什么都没留下。这个人很懒，什么都没留下。这个人很懒，什么都没留下。这个人很懒，什么都没留下。这个人很懒，什么都没留下。这个人很懒，什么都没留下。这个人很懒，什么都没留下。这个人很懒，什么都没留下。"
//    ),
//    User(R.drawable.ava10, "时光", "回到过去"),
//    User(R.drawable.ava1, "Bob", "I miss you"),
//    User(R.drawable.ava2, "XinLa", "冬至"),
//    User(R.drawable.ava3, "Nancy", "“做最好的准备  也做最坏的打算”"),
//    User(R.drawable.ava4, "Nini", "水母只能在深海度过相对失败的一生"),
//    User(R.drawable.ava5, "Brain", "请向前走，不要在此停留。konpaku.cn"),
//    User(R.drawable.ava6, "十香", "逝者如斯夫，不舍昼夜"),
//    User(R.drawable.ava7, "Bird", "对韭当割，人生几何。"),
//    User(R.drawable.ava8, "泰拉瑞亚", "未曾谋面的也终将会相遇的，慢慢来吧，慢慢约会吧\uD83D\uDC31"),
//    User(
//        R.drawable.ava9,
//        "奥风",
//        "这个人很懒，什么都没留下。这个人很懒，什么都没留下。这个人很懒，什么都没留下。这个人很懒，什么都没留下。这个人很懒，什么都没留下。这个人很懒，什么都没留下。这个人很懒，什么都没留下。这个人很懒，什么都没留下。这个人很懒，什么都没留下。这个人很懒，什么都没留下。这个人很懒，什么都没留下。这个人很懒，什么都没留下。这个人很懒，什么都没留下。这个人很懒，什么都没留下。这个人很懒，什么都没留下。"
//    ),
//    User(R.drawable.ava10, "0x5f3759df", "科学的尽头是玄学")
//)

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
    private var _uiState = mutableStateOf(UserUiState())
    val uiState: State<UserUiState> = _uiState

    fun dispatch(intent: UserIntent) = reducer(_uiState.value, intent)

    @OptIn(ExperimentalComposeUiApi::class)
    private fun reducer(model: UserUiState, intent: UserIntent) {
        return when (intent) {
            is UserIntent.UpdateAge -> emit(model.user.copy(age = intent.age))
            is UserIntent.SwitchGenders -> emit(model.user.copy(gender = if (intent.gender) "男" else "女"))
            is UserIntent.UpdateEmail -> emit(model.user.copy(email = intent.email))
            is UserIntent.UpdatePhone -> emit(model.user.copy(phone = intent.phone))
            is UserIntent.RegisteredUsers -> emit(
                model.user.copy(
                    nickname = intent.username,
                    password = intent.password
                )
            )
            is UserIntent.InputPassword -> inputPassword(intent.password)
            is UserIntent.InputUserName -> inputUserName(intent.username)
            is UserIntent.SwitchOpenDropMenu -> dropMenuBtnClick(intent.keyboardController)
            UserIntent.SwitchPwdHidden -> passwordHiddenBtnClick()
            is UserIntent.LoginBtnClick -> loginBtnClick(intent.navController)
            is UserIntent.NavToRegisterBtnClick -> navToRegisterBtnClick(intent.navController)
            is UserIntent.InputRepeatPassword -> inputRepeatPassword(intent.repeatPassword)
            is UserIntent.LoadImageUri -> loadImageUri(intent.uri)
            is UserIntent.RegisterBtnClick -> registerBtnClick(intent.navController)
        }
    }

    fun loadImageUri(uri: Uri) {
        _uiState.value = _uiState.value.copy(imageUri = uri)
    }


    private fun inputUserName(username: String) {
        _uiState.value = _uiState.value.copy(username = username)
    }

    private fun inputPassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    private fun inputRepeatPassword(repeatPassword: String) {
        _uiState.value = _uiState.value.copy(repeatPassword = repeatPassword)
    }

    @OptIn(ExperimentalComposeUiApi::class)
    private fun dropMenuBtnClick(keyboardController: SoftwareKeyboardController) {
        keyboardController.hide()
        _uiState.value = _uiState.value.copy(openDropMenu = !_uiState.value.openDropMenu)
    }

    private fun passwordHiddenBtnClick() {
        _uiState.value = _uiState.value.copy(passwordHidden = !_uiState.value.passwordHidden)
    }

    private fun navToRegisterBtnClick(navController: NavHostController) {
        navController.navigate(AppScreen.register)
    }


    private fun loginBtnClick(navController: NavHostController) {
        if (uiState.value.isUserHasExist) {
            navController.navigate(AppScreen.main) {
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

    private fun emit(state: User) {
        _uiState.value.user = state
    }
}