package com.example.statejetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel: ViewModel() {
    // LiveData holds state which is observed by the UI
    // (state flows down from ViewModel)
    private val _firstName = MutableLiveData("")
    private val _lastName = MutableLiveData("")
    private val _age = MutableLiveData("")
    private val _school = MutableLiveData("")
    val firstName: LiveData<String> = _firstName
    val lastName: LiveData<String> = _lastName
    val age: LiveData<String> = _age
    val school: LiveData<String> = _school

    // onNameChange is an event we're defining that the UI can invoke
    // (events flow up from UI)
    fun updateFirstName(newFirstName: String) {
        _firstName.value = newFirstName
    }

    fun updateLastName(newLastName: String){
        _lastName.value = newLastName
    }

    fun updateAge(newAge: String){
        _age.value = newAge
    }

    fun updateSchool(newSchool: String){
        _school.value = newSchool
    }
}