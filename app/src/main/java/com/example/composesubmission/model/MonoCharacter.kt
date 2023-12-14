package com.example.composesubmission.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MonoCharacter(
    val id: String,
    val name: String,
    val photoUrl: String,
    val description: String,
    val voice: String,
): Parcelable