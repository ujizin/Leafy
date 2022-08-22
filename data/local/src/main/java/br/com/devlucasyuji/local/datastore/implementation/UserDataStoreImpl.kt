package br.com.devlucasyuji.local.datastore.implementation

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import br.com.devlucasyuji.local.datastore.UserDataStore
import br.com.devlucasyuji.local.model.UserStore
import kotlinx.coroutines.flow.first
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


internal class UserDataStoreImpl(
    context: Context,
    private val serializer: Json,
) : UserDataStore {

    private val Context.userStore by preferencesDataStore(name = USER_PREFERENCES_NAME)

    private val userStore: DataStore<Preferences> = context.userStore

    private val userKey = stringPreferencesKey("user_stringify")

    private suspend fun createUser(userStore: UserStore) {
        this.userStore.edit { preferences ->
            preferences[userKey] = userStore.toString()
        }
    }

    override suspend fun getUser(): UserStore {
        val preferences = userStore.data.first()
        val userStore = preferences[userKey] ?: run {
            createUser(UserStore.default())
            return getUser()
        }

        return serializer.decodeFromString(userStore)
    }

    override suspend fun updateUser(userStore: UserStore) {
        this.userStore.edit { preferences ->
            preferences[userKey] = serializer.encodeToString(userStore)
        }
    }

    companion object {
        private const val USER_PREFERENCES_NAME = "user"
    }
}