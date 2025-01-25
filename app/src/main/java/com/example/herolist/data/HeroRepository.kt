package com.example.herolist.data

import com.example.herolist.models.Hero
import com.example.herolist.models.HeroesData

class HeroRepository {
    fun getHeroes():List<Hero>{
        return HeroesData.heroes
    }
    
}