package com.shinaz.newapp.repository

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

private val Context.dataStore by preferencesDataStore("test_shinaz")

class DataStoreRepo(val context: Context) {
    // Note: This is at the top level of the file, outside of any classes.

    private val dataStore = context.dataStore

    companion object {
        val DATA_STORE_USER_EMAIL = stringPreferencesKey("USER_EMAIL")
        val DATA_STORE_PASSWORD = stringPreferencesKey("PASSWORD")
    }
}