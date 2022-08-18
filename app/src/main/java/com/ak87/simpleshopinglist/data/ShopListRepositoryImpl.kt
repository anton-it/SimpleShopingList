package com.ak87.simpleshopinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ak87.simpleshopinglist.domain.ShopItem
import com.ak87.simpleshopinglist.domain.ShopListRepository
import java.lang.RuntimeException
import kotlin.random.Random

object ShopListRepositoryImpl : ShopListRepository{

    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private val shopList = sortedSetOf<ShopItem>({o1, o2 -> o1.id.compareTo(o2.id)})

    //переменная хранения ID
    private var autoIncrementId = 0

    //init блок это код который вополнится при создании объекта - заполнится значениями список
    init {
        for(i in 0 until 1000) {
            val item = ShopItem("Name $i", i, Random.nextBoolean())
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        //нужно удалить старый объект и положить новый но в качестве параметра у нас уже новый параметр
        //если мы попробуем его удалить то элемент найден не будет, поэтому нужно найти старый объект
        //по его id а затем удалить новый.
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        //Ищем элемент по ID с помощью метода find и возвращаем его.
        //если вернулся null то брросим исключение
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Element with id $shopItemId not found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        //возвращать новую коллекцию неправильно т.к мы сможем из других мест в программе
        //олучить к ней доступ. Сделаем копию с помощью метода toList()
        return shopListLD
    }

    //обнолвение объекта liveData
    private fun updateList() {
        shopListLD.value = shopList.toList()
    }
}