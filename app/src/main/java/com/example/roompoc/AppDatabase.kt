package com.example.roompoc

import android.content.Context
import android.telecom.Call
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import java.lang.Exception

@Database(entities = arrayOf(User::class), exportSchema = false, version = 3)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        private const val DB_NAME = "room_db.db"
        @Volatile
        private var INSTANCE: AppDatabase? = null
        @Volatile
        var withMigration = true

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {

//            Log.e("TAG_ROOM_POC", "before synchronized block" + "withMigration = " + withMigration + "instance = " + INSTANCE)


            Log.e(
                "TAG_ROOM_POC",
                "inside synchronized block" + " withMigration = " + withMigration + " instance = " + INSTANCE
            )

            if (INSTANCE == null || withMigration == false) {
                if (withMigration)
                    createInstanceWithMigration(context)
                else
                    createInstanceWithoutMigration(context)
            }

            Log.e(
                "TAG_ROOM_POC",
                "Before setting withMigration to true" + " withMigration = " + withMigration + " instance = " + INSTANCE
            )

            /*if (INSTANCE != null) {
                withMigration = true
            }*/


            Log.e(
                "TAG_ROOM_POC",
                "Before returning instance from getDatabase" + " withMigration = " + withMigration + " instance = " + INSTANCE
            )

            return INSTANCE!!
        }

        private fun createInstanceWithMigration(context: Context): AppDatabase? {

            Log.e(
                "TAG_ROOM_POC",
                "inside createInstanceWithMigration method" + " withMigration = " + withMigration + " instance = " + INSTANCE
            )

            val tempInstance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DB_NAME
            )
                .allowMainThreadQueries()
                .addCallback(object : Callback() {
                    override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
                        super.onDestructiveMigration(db)

                        Log.e(
                            "TAG_ROOM_POC",
                            "inside onDestructiveMigration method in createInstanceWithMigration" + " withMigration = " + withMigration + " instance = " + INSTANCE
                        )

                    }
                })
                .fallbackToDestructiveMigration()
                .addMigrations(object : Migration(2, 3) {
                    override fun migrate(database: SupportSQLiteDatabase) {

                        Log.e(
                            "TAG_ROOM_POC",
                            "enter migrate method" + " withMigration = " + withMigration + " instance = " + INSTANCE
                        )

                        database.execSQL("ALTER TABLE `User` ADD COLUMN 'nnb' TEXT NOT NULL")

                    }
                })
                .build()

            Log.e(
                "TAG_ROOM_POC",
                "Before returning instance from createInstanceWithMigration" + " withMigration = " + withMigration + " instance = " + INSTANCE
            )

//            if (withMigration == true)
            INSTANCE = tempInstance
            return tempInstance

        }

        private fun createInstanceWithoutMigration(context: Context): AppDatabase? {

            Log.e(
                "TAG_ROOM_POC",
                "inside createInstanceWithoutMigration method" + " withMigration = " + withMigration + " instance = " + INSTANCE
            )

            val tempInstance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DB_NAME
            )
                .allowMainThreadQueries()
                .addCallback(object : Callback() {
                    override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
                        super.onDestructiveMigration(db)

                        Log.e(
                            "TAG_ROOM_POC",
                            "inside onDestructiveMigration method in createInstanceWithoutMigration" + " withMigration = " + withMigration + " instance = " + INSTANCE
                        )

                    }
                })
                .fallbackToDestructiveMigration()
                .build()

            Log.e(
                "TAG_ROOM_POC",
                "Before returning instance from createInstanceWithoutMigration" + " withMigration = " + withMigration + " instance = " + INSTANCE
            )

            INSTANCE = tempInstance
            return tempInstance

        }

    }

    abstract fun getUserDao(): UserDao
}