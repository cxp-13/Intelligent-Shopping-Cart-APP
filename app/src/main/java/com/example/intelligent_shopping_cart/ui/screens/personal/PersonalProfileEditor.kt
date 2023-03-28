package com.example.intelligent_shopping_cart.ui.screens.personal

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.intelligent_shopping_cart.R
import com.example.intelligent_shopping_cart.ui.components.*
import com.example.intelligent_shopping_cart.ui.theme.green
import com.example.intelligent_shopping_cart.utils.LocalNavController
import com.example.intelligent_shopping_cart.utils.isValidEmail
import com.example.intelligent_shopping_cart.utils.isValidMobile
import com.example.intelligent_shopping_cart.view_model.UserIntent
import com.example.intelligent_shopping_cart.view_model.UserViewModel


//@Preview
//@Composable
//fun Demo() {
//    PersonalProfileEditor("设置性别", viewModel)
//}

@Composable
fun PersonalProfileEditor(attr: String?, viewModel: UserViewModel) {
//    val viewModel: UserViewModel = viewModel()


    LaunchedEffect(key1 = viewModel, block = {
        Log.d("test", "PersonalProfileEditor: ${viewModel.uiState.value}")
        Log.d("test", "PersonalProfileEditor: ${viewModel}")
    })

    var inputText by remember {
        mutableStateOf("")
    }

    var selectMale by remember {
        mutableStateOf(false)
    }


    val isQRCode = (attr == "qrcode")
    val isGender = (attr == "gender")
    val title = when (attr) {
        "age" -> "输入年龄"
        "phone" -> "输入电话号"
        "email" -> "输入电子邮箱"
        "gender" -> "选择性别"
        else -> "展示二维码"
    }
    val navController = LocalNavController.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopBar(
            start = {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Icon(
                        Icons.Rounded.ArrowBack,
                        null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            },
            center = {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(start = 20.dp)
                )
            },
            end = {
                if (!isQRCode) {
                    Button(
                        onClick = {
                            when (attr) {
                                "age" -> {
                                    viewModel.dispatch(UserIntent.UpdateAge(inputText.toInt()))
                                }
                                "phone" -> {
                                    viewModel.dispatch(UserIntent.UpdatePhone(inputText))
                                }
                                "gender" -> {
                                    viewModel.dispatch(UserIntent.SwitchGenders(selectMale))
                                }
                                "email" -> {
                                    viewModel.dispatch(UserIntent.UpdateEmail(inputText))
                                }
                            }
                            navController.navigate(AppScreen.main)
                        },
                    ) {
                        Text(text = "完成")
                    }
                }
            },
            backgroundColor = MaterialTheme.colorScheme.background
        )
        when {
            isGender -> GenderSelector(selectMale, {
                selectMale = true
            }) {
                selectMale = false
            }
            isQRCode -> QRCodeDisplay()
            else -> ProfileInputField(inputText) {
                inputText = it
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileInputField(inputText: String, changeText: (String) -> Unit) {

    val isTrue by produceState(initialValue = false, key1 = inputText, producer = {
        value = inputText.isValidEmail() || inputText.isValidMobile()
    })


    val focusRequester = remember {
        FocusRequester()
    }

    OutlinedTextField(
        value = inputText,
        onValueChange = {
            changeText(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .focusRequester(focusRequester),
        maxLines = 1,
        textStyle = TextStyle(
            fontSize = 20.sp
        ),
        isError = !isTrue
    )

    Crossfade(!isTrue) {
        if (it) {
            Text(text = "格式错误", color = Color.Red)
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Composable
fun QRCodeDisplay() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable._20230320111907),
                contentDescription = "qr_code"
            )
            HeightSpacer(value = 5.dp)
            Text(text = "使用扫一扫添加我", color = MaterialTheme.colorScheme.onBackground)
        }
    }
}

@Composable
fun GenderSelector(selectMale: Boolean, changeToMale: () -> Unit, changeToFemale: () -> Unit) {
    Column {
        CenterRow(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    changeToMale()
                }
                .padding(horizontal = 20.dp)
                .height(80.dp)
        ) {
            CenterRow {
                Icon(
                    painter = painterResource(id = R.drawable.male),
                    contentDescription = "male",
                    tint = Color.Blue
                )
                WidthSpacer(value = 6.dp)
                Text(
                    text = "男",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            if (selectMale) {
                Icon(
                    painter = painterResource(id = R.drawable.correct),
                    contentDescription = "correct",
                    tint = green
                )
            }
        }
        Divider()
        CenterRow(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    changeToFemale()
                }
                .padding(horizontal = 20.dp)
                .height(80.dp)
        ) {
            CenterRow {
                Icon(
                    painter = painterResource(id = R.drawable.female),
                    contentDescription = "male",
                    tint = Color(0xffd93a7d)
                )
                WidthSpacer(value = 6.dp)
                Text(
                    text = "女",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            if (!selectMale) {
                Icon(
                    painter = painterResource(id = R.drawable.correct),
                    contentDescription = "correct",
                    tint = green
                )
            }
        }
    }
}
