package com.example.roompoc

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id : Int? = null

    var name : String? = null

    var abc : String? = null

    var testing : String? = null

    var pop : Int? = null

    var testing22 : String? = null

}