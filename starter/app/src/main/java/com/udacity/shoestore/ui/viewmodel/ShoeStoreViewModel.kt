package com.udacity.shoestore.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeStoreViewModel : ViewModel() {

    var shoe: Shoe? = null

    private var shoesTemp = mutableListOf<Shoe>()

    private val _shoes = MutableLiveData<List<Shoe>>()
    val shoes: LiveData<List<Shoe>>
        get() = _shoes

    private val _navigateBack = MutableLiveData<Boolean>()
    val navigateBack: LiveData<Boolean>
        get() = _navigateBack

    private val _hasMissingFields = MutableLiveData<Boolean>()
    val hasMissingFields: LiveData<Boolean>
        get() = _hasMissingFields


    fun addShoeToList() {
        shoe?.let {

            if (it.hasEmptyField()) {
                _hasMissingFields.value = true
            } else {
                shoesTemp.add(it)
                navBack()
                _shoes.value = shoesTemp
            }
        }
    }

    fun shoeAddedSucessfully() {
        _navigateBack.value = false
        _hasMissingFields.value = false
    }

    fun navBack() {
        _navigateBack.value = true
    }

     fun initializeShoe() {
        shoe = Shoe("", 0.0, "", "")
    }
}