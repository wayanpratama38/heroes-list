package com.example.herolist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.herolist.data.HeroRepository
import com.example.herolist.models.Hero
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class EpicSevenViewModel(private val repository : HeroRepository): ViewModel() {

    private val _groupedHeroes = MutableStateFlow(
        repository.getHeroes()
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    )

    val groupedHeroes:StateFlow<Map<Char,List<Hero>>> get() = _groupedHeroes

    private val _query = mutableStateOf("")
    val query : State<String> get() = _query

    fun search(newQuery : String){
        _query.value = newQuery
        _groupedHeroes.value = repository.searchHeroes(_query.value)
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    }
}

class ViewModelFactory(private val repository: HeroRepository) : ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T:ViewModel> create(modelClass : Class<T>) : T{
        if(modelClass.isAssignableFrom(EpicSevenViewModel::class.java)){
            return EpicSevenViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class : "+modelClass.name)
    }
}