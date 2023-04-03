package com.example.intelligent_shopping_cart.ui.screens.login

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
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
fun LoginScreen(userViewModel: UserViewModel) {

    val uiState by userViewModel.uiState.collectAsState()

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

    var inputHeight by remember {
        mutableStateOf(0)
    }

    val inputHeightDp = with(LocalDensity.current) {
        inputHeight.toDp()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 35.dp),
        ) {
            Text(
                text = stringResource(id = R.string.shopping_cart),
                style = MaterialTheme.typography.displaySmall,
                fontFamily = FontFamily.Cursive,
                color = MaterialTheme.colorScheme.primary
            )
            HeightSpacer(value = 20.dp)
            Box() {
                OutlinedTextField(
                    value = uiState.username,
                    onValueChange = {
                        userViewModel.dispatch(UserIntent.InputUserName(it))
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        unfocusedLabelColor = MaterialTheme.colorScheme.primary,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                        containerColor = Color.Transparent
                    ),
                    label = {
                        Text(stringResource(id = R.string.username))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned {
                            inputHeight = it.size.height
                        },
                    shape = MaterialTheme.shapes.large,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                userViewModel.dispatch(
                                    UserIntent.SwitchOpenDropMenu(
                                        keyboardController!!
                                    )
                                )
                            }
                        ) {
                            Icon(painterResource(R.drawable.expand), null)
                        }
                    },
                    singleLine = true,
                    isError = !uiState.isUserHasExist
                )
                RecentUserDropdownMenu(
                    modifier = Modifier, uiState.openDropMenu, uiState.users, closeDropMenu = {
                        userViewModel.dispatch(UserIntent.CloseDropMenu)
                    }, itemClick = { user ->
                        userViewModel.dispatch(UserIntent.DropMenuItemClick(user = user))
                    }
                )
            }
            HeightSpacer(value = 10.dp)
            OutlinedTextField(
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
                    Text(stringResource(id = R.string.password))
                },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
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
            TextButton(
                colors = ButtonDefaults.buttonColors(
                    containerColor = loginBtnColor,
                    contentColor = loginBtnOnColor
                ),
                onClick = {
                    userViewModel.dispatch(UserIntent.LoginBtnClick(navController))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(inputHeightDp),

                elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
                shape = MaterialTheme.shapes.large,
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
                        Text(stringResource(id = R.string.username_or_pwd_error_message))
                    } else {
                        Text(stringResource(id = R.string.login))
                    }
                }
            }
            HeightSpacer(value = 15.dp)
            CenterRow {
                Text(
                    text = stringResource(id = R.string.forgot_password),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = stringResource(id = R.string.sign_up),
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

