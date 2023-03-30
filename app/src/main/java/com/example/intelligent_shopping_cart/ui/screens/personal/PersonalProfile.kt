package com.example.intelligent_shopping_cart.ui.screens.personal

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.intelligent_shopping_cart.R
import com.example.intelligent_shopping_cart.model.User
import com.example.intelligent_shopping_cart.ui.components.AppScreen
import com.example.intelligent_shopping_cart.ui.components.CenterRow
import com.example.intelligent_shopping_cart.ui.components.HeightSpacer
import com.example.intelligent_shopping_cart.utils.LocalNavController
import com.example.intelligent_shopping_cart.view_model.UserViewModel
import com.example.intelligent_shopping_cart.view_model.usersMock


@Composable
fun PersonalProfile(viewModel: UserViewModel) {
//    val viewModel = ViewModelProvider(LocalViewModelStoreOwner.current!!)[UserViewModel::class.java]
//    val viewModel : UserViewModel = viewModel()
    val currentUser = viewModel.uiState.value.user

    LaunchedEffect(key1 = currentUser, block = {
        Log.d("test", "PersonalProfile: ${currentUser}")
        Log.d("test", "PersonalProfile: ${viewModel}")
    })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(200.dp)
                .paint(
                    painterResource(id = R.drawable.google_bg),
                    contentScale = ContentScale.FillBounds
                ),
            contentAlignment = Alignment.BottomStart
        ) {
            PersonalProfileHeader(currentUser)
        }
        HeightSpacer(value = 10.dp)
//        中间的用户信息
        PersonalProfileDetail(currentUser)
        Box(
            modifier = Modifier.fillMaxHeight(),
            contentAlignment = Alignment.BottomCenter
        ) {
            BottomSettingIcons()
        }
    }
}


fun getCurrentLoginUserProfile(): User {
    return usersMock[0]
}

@Composable
fun PersonalProfileHeader(currentUser: User) {
//    val currentUser = getCurrentLoginUserProfile()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(10.dp)
    ) {
        val (portraitImageRef, usernameTextRef, desTextRef) = remember { createRefs() }
        Image(
            painter = painterResource(id = currentUser.avatarRes),
            contentDescription = "portrait",
            modifier = Modifier
                .constrainAs(portraitImageRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
                .clip(CircleShape)
        )
        Text(
            text = currentUser.nickname,
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Left,
            color = Color.White,
            modifier = Modifier
                .constrainAs(usernameTextRef) {
                    top.linkTo(portraitImageRef.top, 5.dp)
                    start.linkTo(portraitImageRef.end, 10.dp)
                    width = Dimension.preferredWrapContent
                }
        )
        Text(
            text = currentUser.motto,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier
                .constrainAs(desTextRef) {
                    top.linkTo(usernameTextRef.bottom, 10.dp)
                    start.linkTo(portraitImageRef.end, 10.dp)
//                    end.linkTo(parent.end)
                    width = Dimension.preferredWrapContent
                }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalProfileDetail(currentUser: User) {
//    var user = getCurrentLoginUserProfile()
    val navController = LocalNavController.current
//    val chattyColors = MaterialTheme.chattyColors
    val scope = rememberCoroutineScope()


//  给用户信息的枚举类赋值上当前用户的信息
    val personalProfileItems = remember(currentUser) {
        PersonalProfileItem.UID.badge = currentUser.uid
        PersonalProfileItem.SEX.badge = currentUser.gender.toString()
        PersonalProfileItem.AGE.badge = currentUser.age.toString()
        PersonalProfileItem.PHONE.badge = currentUser.phone.toString()
        PersonalProfileItem.EMAIL.badge = currentUser.email.toString()
        PersonalProfileItem.values()
    }


    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp)
    ) {
        CenterRow {
            Text(
                text = "个人信息",
                fontSize = 25.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.weight(1f)
            )
//            IconButton(
//                onClick = { chattyColors.toggleTheme() }
//            ) {
//                Icon(
//                    imageVector = if (chattyColors.isLight)Icons.Rounded.DarkMode else Icons.Rounded.LightMode,
//                    contentDescription = null,
//                    tint = MaterialTheme.colorScheme.onBackground
//                )
//            }
        }
        Spacer(Modifier.padding(vertical = 12.dp))

        personalProfileItems.forEach { item ->
            NavigationDrawerItem(
                label = {
                    Text(item.label, style = MaterialTheme.typography.titleMedium)
                },
                selected = true,
                badge = {
                    item.badge?.let {
                        Text(it)
                    }
                },
                icon = {
                    Icon(item.icon, null)
                },
                onClick = {
                    when (item) {
                        PersonalProfileItem.SEX -> navController.navigate("${AppScreen.profileEdit}/gender")
                        PersonalProfileItem.AGE -> navController.navigate("${AppScreen.profileEdit}/age")
                        PersonalProfileItem.PHONE -> navController.navigate("${AppScreen.profileEdit}/phone")
                        PersonalProfileItem.EMAIL -> navController.navigate("${AppScreen.profileEdit}/email")
                        PersonalProfileItem.QRCODE -> navController.navigate("${AppScreen.profileEdit}/qrcode")
//                        PersonalProfileItem.UID -> scope.launch {
////                            scaffoldState.snackbarHostState.showSnackbar(user.uid)
//                            snackbarHostState.showSnackbar(user.uid)
//                        }
                        else -> {}
                    }
                }
            )
            HeightSpacer(value = 8.dp)
        }


    }
}

enum class PersonalProfileItem(
    val label: String,
    var badge: String,
    val icon: ImageVector
) {
    UID("ID号", "-1", Icons.Rounded.Android),
    SEX("性别", "null", Icons.Rounded.Male),
    AGE("年龄", "-1", Icons.Rounded.Circle),
    PHONE("手机号", "-1", Icons.Rounded.Call),
    EMAIL("电子邮箱", "xxxxxxxx@qq.com", Icons.Rounded.Mail),
    QRCODE("二维码", "null", Icons.Rounded.QrCode)
}


//val personalProfileItemIcon = listOf(
//    Icons.Rounded.Male,
//    Icons.Rounded.Circle,
//    Icons.Rounded.Call,
//    Icons.Rounded.Mail,
//    Icons.Rounded.QrCode
//)

@Composable
fun BottomSettingIcons() {
    val navController = LocalNavController.current
    CenterRow(
        Modifier
            .fillMaxWidth()
            .padding(18.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.clickable(
                onClick = {

                },
                indication = null,
                interactionSource = MutableInteractionSource()
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Rounded.Settings, contentDescription = null)
            Text(text = "设置", fontSize = 15.sp)
        }
        Column(
            modifier = Modifier.clickable(
                onClick = {
                    navController.navigate(AppScreen.login) {
                        popUpTo(AppScreen.main) { inclusive = true }
                    }
                },
                indication = null,
                interactionSource = MutableInteractionSource()
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Rounded.Logout, contentDescription = null)
            Text(text = "注销", fontSize = 15.sp, fontWeight = FontWeight.Bold)
        }
    }
}