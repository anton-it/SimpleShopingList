package com.ak87.simpleshopinglist.domain

data class ShopItem(
    val name: String,
    val count: Int,
    val enabled: Boolean,
    var id: Int = UNDEFINED_ID
) {
    //вынесем -1  константу т.к нельзя оставлять жестко закодированные числа
//-1 означает что iD еще не установлен и ему нужно присвоить значение при добавлении в коллекцию.
    companion object {
        const val UNDEFINED_ID = -1
    }
}


