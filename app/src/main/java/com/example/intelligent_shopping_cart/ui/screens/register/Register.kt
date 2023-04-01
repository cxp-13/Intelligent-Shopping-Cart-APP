package com.example.intelligent_shopping_cart.ui.screens.register

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.rounded.Password
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.intelligent_shopping_cart.R
import com.example.intelligent_shopping_cart.ui.components.HeightSpacer
import com.example.intelligent_shopping_cart.utils.LocalNavController
import com.example.intelligent_shopping_cart.view_model.UserIntent
import com.example.intelligent_shopping_cart.view_model.UserViewModel

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun Register(userViewModel: UserViewModel) {

    val uiState by userViewModel.uiState

    val keyboardController = LocalSoftwareKeyboardController.current
    val navController = LocalNavController.current

    DisposableEffect(key1 = Unit, effect = {
        onDispose {
            userViewModel.clear()
        }
    })

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
//                imageUri = it
                userViewModel.dispatch(UserIntent.LoadImageUri(it))
            }
        }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 35.dp, vertical = 48.dp)
        ) {
            Text(
                text = stringResource(id = R.string.register),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )
            HeightSpacer(value = 15.dp)
            Box {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .border(2.dp, Color(0xFF0079D3), CircleShape)
                        .background(color = Color.Gray, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Surface(
                        shape = CircleShape,
                        color = Color.Transparent
                    ) {
                        Image(
                            painter = uiState.imageUri?.let {
                                rememberAsyncImagePainter(it)
                            } ?: run { painterResource(id = R.drawable.ava1) },
                            contentDescription = null,
                            modifier = Modifier.clickable { launcher.launch("image/*") }
                        )
                    }
                }
                Image(
                    painterResource(id = R.drawable.camera), null,
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.BottomEnd)
                )
            }
            HeightSpacer(value = 12.dp)
            OutlinedTextField(
                value = uiState.username,
                onValueChange = {
//                    username = it
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
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                isError = uiState.isUserHasExistByName,
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
            AnimatedVisibility(visible = uiState.isUserHasExistByName) {
                HeightSpacer(value = 4.dp)
                Text(
                    text = stringResource(id = R.string.user_exist_error_message),
                    color = MaterialTheme.colorScheme.error
                )
            }
            HeightSpacer(value = 12.dp)
            OutlinedTextField(
                value = uiState.password,
                onValueChange = {
//                    password = it
                    userViewModel.dispatch(UserIntent.InputPassword(it))
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
                shape = RoundedCornerShape(8.dp),
                visualTransformation = if (uiState.passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
                trailingIcon = {
                    IconButton(
                        onClick = {
//                            passwordHidden = !passwordHidden
                            userViewModel.dispatch(UserIntent.SwitchPwdHidden)
                        }
                    ) {
                        Crossfade(uiState.passwordHidden) {
                            if (it) {
                                Icon(Icons.Rounded.Password, null)
                            } else {
                                Icon(painterResource(id = R.drawable.visibility), null)
                            }
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
            HeightSpacer(value = 12.dp)
            OutlinedTextField(
                value = uiState.repeatPassword,
                onValueChange = {
//                    repeatPassword = it
                    userViewModel.dispatch(UserIntent.InputRepeatPassword(it))
                },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedLabelColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    containerColor = Color.Transparent
                ),
                label = {
                    Text(stringResource(id = R.string.repeat_password))
                },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                trailingIcon = {
                    if (uiState.isPwdInconsistent && uiState.repeatPassword.isNotEmpty()) {
                        Icon(Icons.Filled.Check, null)
                    }
                },
                visualTransformation = if (uiState.passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Go),
                keyboardActions = KeyboardActions(onGo = { keyboardController?.hide() })
            )
            if (!uiState.isPwdInconsistent) {
                HeightSpacer(value = 4.dp)
                Text(
                    text = stringResource(id = R.string.pwd_inconsistent_error_message),
                    color = MaterialTheme.colorScheme.error
                )
            }
            HeightSpacer(value = 22.dp)
            Button(
                onClick = {
//                    navController.navigate(AppScreen.home) {
////                        this.popUpTo(AppScreen.login){
////                            inclusive = true
////                        }
//                        popUpAllBackStackEntry(navController)
//                    }
                    userViewModel.dispatch(UserIntent.RegisterBtnClick(navController))
                },
                enabled = (uiState.isPwdInconsistent && uiState.password.isNotEmpty()),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0079D3)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                elevation = ButtonDefaults.buttonElevation(10.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(stringResource(id = R.string.register), color = Color.White)
            }
        }
    }

//    BackHandler {
//        val b = navController.popBackStack()
//        Log.d("cxp", "RegisterPage: $b")
//    }
}
