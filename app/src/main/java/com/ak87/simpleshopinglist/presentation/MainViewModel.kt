package com.ak87.simpleshopinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ak87.simpleshopinglist.data.ShopListRepositoryImpl
import com.ak87.simpleshopinglist.domain.*

class MainViewModel : ViewModel(){
    //создаем объекты юскейсов и в параметры передаем репозиторий с которым они работают

    private val repository = ShopListRepositoryImpl
    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    // создаем LiveData для хранения данных.
    val shopList = getShopListUseCase.getShopList()


    //удаление элемента
    fun deleteShopList(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    //меняет состояние enabled. Нужно создать копию объекта ShopItem с противоположным состоянием
    fun  changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }

}