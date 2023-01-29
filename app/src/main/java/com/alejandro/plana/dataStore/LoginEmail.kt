package com.alejandro.plana.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LoginEmail (private val context: Context){
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("Recordarme")
        val RECORDARME_KEY = booleanPreferencesKey("recordame")
    }

    suspend fun saveRemember(recordarme: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[RECORDARME_KEY] = true
        }
    }

    val getRemember: Flow<Boolean?> = context.dataStore.data.map { preferences ->
        preferences[RECORDARME_KEY]
    }
}