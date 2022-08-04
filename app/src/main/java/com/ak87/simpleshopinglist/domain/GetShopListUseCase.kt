package com.ak87.simpleshopinglist.domain

class GetShopListUseCase(private val shopListRepository: ShopListRepository){
    fun getShopList(): List<ShopItem>  {
        return shopListRepository.getShopList()
    }
}