package com.example.intelligent_shopping_cart.ui.screens.login

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.intelligent_shopping_cart.R
import com.example.intelligent_shopping_cart.ui.components.CenterRow
import com.example.intelligent_shopping_cart.ui.components.HeightSpacer
import com.example.intelligent_shopping_cart.ui.components.WidthSpacer
import com.example.intelligent_shopping_cart.utils.LocalNavController
import com.example.intelligent_shopping_cart.view_model.UserIntent
import com.example.intelligent_shopping_cart.view_model.UserViewModel


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun Login(userViewModel: UserViewModel) {

    val uiState by userViewModel.uiState

    val usernameInteractionSource = remember { MutableInteractionSource() }
    val passwordInteractionSource = remember { MutableInteractionSource() }

    val usernameFocusedAsState by usernameInteractionSource.collectIsPressedAsState()
    val passwordFocusedAsState by passwordInteractionSource.collectIsPressedAsState()

    LaunchedEffect(key1 = usernameFocusedAsState, key2 = passwordFocusedAsState, block = {
    })

    DisposableEffect(key1 = Unit, effect = {
        onDispose {
            userViewModel.clear()
        }
    })

    val transition =
        updateTransition(targetState = uiState.isUserHasExist, label = "transition")

    val loginBtnColor by transition.animateColor(label = "loginBtnColor") { state ->
        if (!state) {
            MaterialTheme.colorScheme.error
        } else {
            MaterialTheme.colorScheme.primary
        }
    }

    val loginBtnOnColor by transition.animateColor(label = "loginBtnOnColor") { state ->
        if (!state) {
            MaterialTheme.colorScheme.onError
        } else {
            MaterialTheme.colorScheme.onPrimary
        }
    }

    val navController: NavHostController = LocalNavController.current
    val keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current
//    var openDropMenu by remember { mutableStateOf(false) }
//
//    var username by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var passwordHidden by rememberSaveable { mutableStateOf(true) }

//    val loginBtnInteractionSource = remember {
//        MutableInteractionSource()
//    }
//
//    val loginBtnIsPressed by loginBtnInteractionSource.collectIsPressedAsState()

//    LaunchedEffect(key1 = loginBtnInteractionSource.interactions, block = {
//        loginBtnInteractionSource.interactions.collect {
//            Log.d("test", "Login: $it")
//        }
//    })
//
//    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
//            .padding(WindowInsets.statusBars.asPaddingValues()),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 35.dp),
        ) {
            Text(
                text = "ShoppingCart",
                style = MaterialTheme.typography.displaySmall
            )
            HeightSpacer(value = 20.dp)
            OutlinedTextField(
                interactionSource = usernameInteractionSource,
                value = uiState.username,
                onValueChange = {
                    userViewModel.dispatch(UserIntent.InputUserName(it))
//                    username = it
                    Log.d("test", "Login: ${uiState.username}")
                },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedLabelColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    containerColor = Color.Transparent
                ),
                label = {
                    Text("用户名")
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            userViewModel.dispatch(UserIntent.SwitchOpenDropMenu(keyboardController!!))
//                            keyboardController?.hide()
//                            openDropMenu = !openDropMenu
                        }
                    ) {
                        Icon(painterResource(R.drawable.expand), null)
                    }
                },
                singleLine = true,
                isError = !uiState.isUserHasExist
            )
            HeightSpacer(value = 10.dp)
            OutlinedTextField(
                interactionSource = passwordInteractionSource,
                isError = !uiState.isUserHasExist,
                value = uiState.password,
                onValueChange = {
                    userViewModel.dispatch(UserIntent.InputPassword(it))
//                    password = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedLabelColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    containerColor = Color.Transparent
                ),
                label = {
                    Text("密码")
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                visualTransformation = if (uiState.passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
                trailingIcon = {
                    IconButton(
                        onClick = {
                            userViewModel.dispatch(UserIntent.SwitchPwdHidden)
//                            passwordHidden = !passwordHidden
                        }
                    ) {
                        Icon(painterResource(id = R.drawable.visibility), null)
                    }
                },
                singleLine = true
            )
            HeightSpacer(value = 20.dp)
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = loginBtnColor,
                    contentColor = loginBtnOnColor
                ),
                onClick = {
                    userViewModel.dispatch(UserIntent.LoginBtnClick(navController))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
                shape = MaterialTheme.shapes.medium,
            ) {
                Crossfade(!uiState.isUserHasExist) {
                    if (it) {
                        Icon(Icons.Outlined.Warning, null)
                    } else {
                        Icon(painterResource(R.drawable.login), null)
                    }
                }
                WidthSpacer(5.dp)
                Crossfade(!uiState.isUserHasExist) {
                    if (it) {
                        Text("用户名或密码错误")
                    } else {
                        Text("登入")
                    }
                }
            }
            HeightSpacer(value = 15.dp)
            CenterRow {
                Text(
                    text = "忘记密码？",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "注册账号",
                    style = MaterialTheme.typography.labelLarge,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable(
                        onClick = {
                            userViewModel.dispatch(UserIntent.NavToRegisterBtnClick(navController))
                        },
                        indication = null,
                        interactionSource = MutableInteractionSource()
                    ),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

