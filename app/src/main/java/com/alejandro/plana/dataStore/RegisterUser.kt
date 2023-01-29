package com.alejandro.plana.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RegisterUser (private val context: Context){

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("UserName")
        val USER_NAME_KEY = stringSetPreferencesKey("user_name")
        private val Context.dataStore1: DataStore<Preferences> by preferencesDataStore("UserEmail")
        val USER_EMAIL_KEY = stringSetPreferencesKey("user_email")
        private val Context.dataStore2: DataStore<Preferences> by preferencesDataStore("UserPassword")
        val USER_PASSWORD_KEY = stringSetPreferencesKey("user_password")
        private val Context.dataStore3: DataStore<Preferences> by preferencesDataStore("UserPostalCode")
        val USER_POSTAL_CODE_KEY = stringSetPreferencesKey("user_postal_code")
    }

    //Almacenar los datos del usuario

    suspend fun saveName(name: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = setOf(name)
        }
    }

    suspend fun saveEmail(email: String) {
        context.dataStore1.edit { preferences ->
            preferences[USER_EMAIL_KEY] = setOf(email)
        }
    }

    suspend fun savePassword(password: String) {
        context.dataStore2.edit { preferences ->
            preferences[USER_PASSWORD_KEY] = setOf(password)
        }
    }

    suspend fun savePostalCode(postalCode: String) {
        context.dataStore3.edit { preferences ->
            preferences[USER_POSTAL_CODE_KEY] = setOf(postalCode)
        }
    }

    //Obtener los datos del usuario

    val getName: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[USER_NAME_KEY]?.firstOrNull()
    }

    val getEmail: Flow<String?> = context.dataStore1.data.map { preferences ->
        preferences[USER_EMAIL_KEY]?.firstOrNull()
    }

    val getPassword: Flow<String?> = context.dataStore2.data.map { preferences ->
        preferences[USER_PASSWORD_KEY]?.firstOrNull()
    }

    val getPostalCode: Flow<String?> = context.dataStore3.data.map { preferences ->
        preferences[USER_POSTAL_CODE_KEY]?.firstOrNull()
    }

}