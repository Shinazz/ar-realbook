package com.shinaz.newapp.room

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.shinaz.newapp.model.ar.UserData
import com.shinaz.newapp.room.util.LocationTypeConverter

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(UserData::class), version = 1, exportSchema = false)
@TypeConverters(LocationTypeConverter::class)
public abstract class UserRoomDB : RoomDatabase() {

    abstract fun userDao(): UserDao



    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: UserRoomDB? = null

        /**
         * Override the onOpen method to populate the database.
         * For this sample, we clear the database every time it is created or opened.
         */
        private val sRoomDatabaseCallback: RoomDatabase.Callback = object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                UserRoomDB.INSTANCE?.let { PopulateDbAsync(it).execute() }
            }
        }
        /**
         * Populate the database in the background.
         * If you want to start with more words, just add them.
         */
        private class PopulateDbAsync internal constructor(db: UserRoomDB) : AsyncTask<Void?, Void?, Void?>() {
            private val mDao: UserDao

            init {
                mDao = db.userDao()
            }

            override fun doInBackground(vararg params: Void?): Void? {
                // Start the app with a clean database every time.
                // Not needed if you only populate on creation.
                mDao.deleteAll()
                return null
            }
        }


        fun getDatabase(context: Context): UserRoomDB {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserRoomDB::class.java,
                        "shinaz_database",
                )
                    .addTypeConverter(LocationTypeConverter())
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}