package com.ak87.simpleshopinglist.domain

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository){
    fun deleteShopItem(shopItem: ShopItem) {
        shopListRepository.deleteShopItem(shopItem)
    }
}