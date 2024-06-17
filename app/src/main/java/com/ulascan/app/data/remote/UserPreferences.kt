package com.ulascan.app.data.remote

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")

data class UserModel(val token: String, val isLoggedIn: Boolean = false)

class UserPreferences private constructor(private val dataStore: DataStore<Preferences>) {

  suspend fun saveToken(token: String) {
    dataStore.edit { preferences ->
      preferences[TOKEN_KEY] = token
      preferences[IS_LOGGED_IN] = true
    }
  }

  fun getUserSession(): Flow<UserModel> {
    return dataStore.data.map { preferences ->
      UserModel(
          token = preferences[TOKEN_KEY] ?: "", isLoggedIn = preferences[IS_LOGGED_IN] ?: false)
    }
  }

  suspend fun clearToken() {
    dataStore.edit { preferences -> preferences.clear() }
  }

  companion object {
    @Volatile private var INSTANCE: UserPreferences? = null

    private val TOKEN_KEY = stringPreferencesKey("token")
    private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")

    fun getInstance(dataStore: DataStore<Preferences>): UserPreferences {
      return INSTANCE
          ?: synchronized(this) {
            val instance = UserPreferences(dataStore)
            INSTANCE = instance
            instance
          }
    }
  }
}
