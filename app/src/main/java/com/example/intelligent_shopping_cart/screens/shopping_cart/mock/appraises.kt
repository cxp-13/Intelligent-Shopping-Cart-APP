package com.example.intelligent_shopping_cart.screens.shopping_cart.mock

import com.example.intelligent_shopping_cart.bean.Appraise
import com.example.intelligent_shopping_cart.bean.friends


val appraises = listOf(
    // 生成一个评分为4分，评星为4颗星的Appraise对象
    Appraise(friends[0], "这个商品很不错，值得购买！", "2021-11-11 11:11:11.111", 4),
// 生成一个评分为5分，评星为5颗星的Appraise对象
    Appraise(
        friends[1],
        "非常好的商品，强烈推荐！",
        "2021-12-12 12:12:12.222",
        5
    ),
// 生成一个评分为3分，评星为3颗星的Appraise对象
    Appraise(
        friends[2],
        "这个商品一般般，还有提升的空间。",
        "2022-01-01 00:00:00.333",
        3
    )
)


