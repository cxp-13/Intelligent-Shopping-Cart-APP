package com.chatty.compose.screens.login

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.intelligent_shopping_cart.R
import com.example.intelligent_shopping_cart.ui.components.AppScreen
import com.example.intelligent_shopping_cart.ui.components.CenterRow
import com.example.intelligent_shopping_cart.ui.components.HeightSpacer
import com.example.intelligent_shopping_cart.ui.components.WidthSpacer
import com.example.intelligent_shopping_cart.ui.utils.LocalNavController


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun Login() {

    val navController = LocalNavController.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var openDropMenu by remember { mutableStateOf(false) }

    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordHidden by rememberSaveable { mutableStateOf(true) }


    val loginBtnInteractionSource = remember {
        MutableInteractionSource()
    }

    val loginBtnIsPressed by loginBtnInteractionSource.collectIsPressedAsState()


    LaunchedEffect(key1 = loginBtnInteractionSource.interactions, block = {
        loginBtnInteractionSource.interactions.collect {
            Log.d("test", "Login: $it")
        }
    })


    val coroutineScope = rememberCoroutineScope()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 48.dp),
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
                value = username,
                onValueChange = {
                    username = it
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
                            keyboardController?.hide()
                            openDropMenu = !openDropMenu
                        }
                    ) {
                        Icon(painterResource(R.drawable.expand), null)
                    }
                },
                singleLine = true
            )
            HeightSpacer(value = 10.dp)
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
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
                visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
                trailingIcon = {
                    IconButton(
                        onClick = {
                            passwordHidden = !passwordHidden
                        }
                    ) {
                        Icon(painterResource(id = R.drawable.visibility), null)
                    }
                },
                singleLine = true
            )
            HeightSpacer(value = 20.dp)
            Button(
                colors = ButtonDefaults.buttonColors(),
                onClick = {
                    navController.navigate(AppScreen.main) {
                        popUpTo(AppScreen.login) { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
                shape = MaterialTheme.shapes.medium,
                interactionSource = loginBtnInteractionSource
            ) {
                AnimatedVisibility(visible = loginBtnIsPressed) {
                    Icon(painterResource(R.drawable.login), null)
                    WidthSpacer(5.dp)
                }
                Text("登入")
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
                            navController.navigate(AppScreen.register)
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

