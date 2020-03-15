package com.example.roompoc

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    fun insertUserName(user : User)

    //TODO name in place of *
    @Query("SELECT `id`,`name` FROM `USER`")
    fun getUserName() : List<User>

    @Query("DELETE FROM USER WHERE `name` = :name")
    fun updateUserName(name : String) : Int

}