package com.example.composesubmission.data

import com.example.composesubmission.model.MonoCharacter
import com.example.composesubmission.model.MonoCharacterData

class MonoCharacterRepository {
    fun getMonoCharacter(): List<MonoCharacter> {
        return MonoCharacterData.monoChar
    }

    fun searchMonoCharacter(query: String): List<MonoCharacter> {
        return MonoCharacterData.monoChar.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

    fun getCharacterById(query: String): MonoCharacter?{
        return MonoCharacterData.monoChar.firstOrNull{
            it.id == query
        }
    }
}