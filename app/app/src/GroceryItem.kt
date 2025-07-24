package com.prisma.app

data class GroceryItem(
    val name: String,
    var quantity: Int = 1,
    var isSelected: Boolean = false
)
