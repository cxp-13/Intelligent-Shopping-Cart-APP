package com.example.intelligent_shopping_cart.ui.screens.shopping_cart.mock

import com.example.intelligent_shopping_cart.R
import com.example.intelligent_shopping_cart.model.Commodity

val commoditiesDefault = listOf(
    // 生成一个名为"苹果"，价格为5元，数量为2个的Commodity对象
    Commodity(
        "苹果",
        R.drawable.ava1,
        5,
        "中国",
        "红富士",
        "10斤装",
        "2021-12-31 23:59:59.999",
        "新鲜的苹果",
        appraises,
        2,
        "default"
    ),

// 生成一个名为"香蕉"，价格为3元，数量为1个的Commodity对象
    Commodity(
        "香蕉",
        R.drawable.ava2,
        3,
        "中国",
        "巴西",
        "1kg装",
        "2021-12-31 23:59:59.999",
        "甜美可口的香蕉",
        appraises,
        1,
        "default"
    ),
// 生成一个名为"牛奶"，价格为10元，数量为3瓶的Commodity对象
    Commodity(
        "牛奶",
        R.drawable.ava3,
        10,
        "中国",
        "伊利",
        "500ml装",
        "2022-06-30 23:59:59.999",
        "营养丰富的牛奶",
        appraises,
        3,
        "default"
    )
)