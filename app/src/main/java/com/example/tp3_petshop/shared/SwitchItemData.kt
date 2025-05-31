package com.example.tp3_petshop.shared

data class SwitchItemData(
    val label: String,
    var isEnabled: Boolean
)

data class SwitchSectionData(
    val title: String,
    val items: List<SwitchItemData>
)
