package com.example.composesubmission.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.composesubmission.data.MonoCharacterRepository
import com.example.composesubmission.model.MonoCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MonoCharViewModel(private val repository: MonoCharacterRepository) : ViewModel() {
    private val _groupedMonoChar = MutableStateFlow(
        repository.getMonoCharacter()
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    )
    val groupedMonoChar: StateFlow<Map<Char, List<MonoCharacter>>> get() = _groupedMonoChar

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        _groupedMonoChar.value = repository.searchMonoCharacter(_query.value)
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    }

    private val _detailChar = MutableStateFlow<MonoCharacter?>(null)
    val detailChar: StateFlow<MonoCharacter?> get() = _detailChar
    fun searchByID(idQuery: String){
        _detailChar.value = repository.getCharacterById(idQuery)
    }
}


class ViewModelFactory(private val repository: MonoCharacterRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MonoCharViewModel::class.java)) {
            return MonoCharViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

}