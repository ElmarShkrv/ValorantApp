package com.chiore.valorantapp.ui.fragments.agentsfragment

data class AgentsActivityState(
    val listType: ListType = ListType.GridLayout
)

enum class ListType() {
    LinearLayout(),
    GridLayout()
}
