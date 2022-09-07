package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeListViewModel : ViewModel() {

    private val _shoeList = MutableLiveData<MutableList<Shoe>>(mutableListOf())
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    var newShoeName = ""
    var newShoeSize = ""
    var newShoeCompany = ""
    var newShoeDescr = ""

    init {
        createMockData()
    }

    fun addNewShoe(): Boolean {
        return addShoe(Shoe(newShoeName, newShoeSize.toDouble(), newShoeCompany, newShoeDescr))
    }

    fun clearShoe() {
        newShoeName = ""
        newShoeSize = ""
        newShoeCompany = ""
        newShoeDescr = ""
    }

    private fun addShoe(newShoe: Shoe): Boolean {
        if (validateShoe(newShoe)) {
            _shoeList.value?.add(newShoe)
            return true
        }
        return false
    }

    private fun createMockData() {
        addShoe(Shoe("Pegasus", 6.0, "Nike", "Road/Trail running shoes"))
        addShoe(Shoe("Speedcross 5", 8.0, "Salomon", "Trail running shoes"))
        addShoe(Shoe("All Star", 9.0, "Converse", "Casual wear"))
    }

    private fun validateShoe(newShoe: Shoe): Boolean {
        return !(newShoe.name?.isBlank() ?: false ||
                newShoe.size == null ||
                newShoe.company?.isBlank() ?: false ||
                newShoe.description?.isBlank() ?: false
                )
    }


}