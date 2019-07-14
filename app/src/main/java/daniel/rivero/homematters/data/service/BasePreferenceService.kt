package daniel.rivero.homematters.data.service

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import timber.log.Timber
import java.util.HashMap


open class BasePreferenceService constructor(private val preferences: SharedPreferences, private val cacheInMemory: Boolean = false) {

    companion object {
        private const val EMPTY_STRING = ""
    }

    protected val gson: Gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()

    private val cache = HashMap<PreferencesKey, Any?>()

    protected open fun cacheEnabled(key: PreferencesKey): Boolean = cacheInMemory

    protected open fun setString(key: PreferencesKey, value: String?) {
        if (cacheEnabled(key)) {
            Timber.d("setting cache preference$key -> $value")
            cache[key] = value
        }
        Timber.d("setting preference $key -> $value")
        preferences.edit().putString(key.keyString(), value).apply()
    }

    protected open fun setBoolean(key: PreferencesKey, value: Boolean?) {
        if (cacheEnabled(key)) {
            Timber.d("setting cache preference$key -> $value")
            cache[key] = value
        }
        Timber.d("setting preference $key -> $value")
        preferences.edit().putBoolean(key.keyString(), value!!).apply()
    }

    protected open fun <T> setObject(key: PreferencesKey, value: T) {
        if (cacheEnabled(key)) {
            Timber.d("setting cache preference$key -> $value")
            cache[key] = value
        }
        Timber.d("setting preference $key -> $value")
        setString(key, gson.toJson(value))
    }

    protected open fun setInt(key: PreferencesKey, value: Int) {
        if (cacheEnabled(key)) {
            Timber.d("setting cache preference$key -> $value")
            cache[key] = value
        }
        Timber.d("setting preference $key -> $value")
        preferences.edit().putInt(key.keyString(), value).apply()
    }

    protected open fun setFloat(key: PreferencesKey, value: Float) {
        if (cacheEnabled(key)) {
            Timber.d("setting cache preference$key -> $value")
            cache[key] = value
        }
        Timber.d("setting preference $key -> $value")
        preferences.edit().putFloat(key.keyString(), value).apply()
    }

    protected open fun <T> getObject(key: PreferencesKey, objectClass: Class<T>): T? {
        var result: T? = null
        try {
            if (cacheEnabled(key) && cache.containsKey(key)) {
                result = cache[key] as T?
                Timber.d("getting persisted cache preference $key <- $result")
            } else {
                val stringObject = getStringNullable(key, null)
                if (!stringObject.isNullOrBlank()) {
                    result = gson.fromJson(stringObject, objectClass)
                }
                Timber.d("getting persisted preference $key <- $result")
            }
        } catch (e: Exception) {
            Timber.e(e.message)
        }
        return result
    }

    protected open fun <T> getList(key: PreferencesKey, objectClass: Class<Array<T>>): List<T>? {
        var result: List<T>? = null
        try {
            if (cacheEnabled(key) && cache.containsKey(key)) {
                result = cache[key] as List<T>?
                Timber.d("preference cache list size $key <- ${result?.size ?: 0}")
            } else {
                val stringObject = getStringNullable(key, null)
                if (!stringObject.isNullOrBlank()) {
                    result = gson.fromJson(stringObject, objectClass).toList()
                }
                Timber.d("preference list size $key <- ${result?.size ?: 0}")
            }
        } catch (e: Exception) {
            Timber.e(e.message)
        }
        return result
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    protected open fun getString(key: PreferencesKey, defValue: String = EMPTY_STRING): String {
        return getStringNullable(key, defValue) ?: EMPTY_STRING
    }

    protected open fun getStringNullable(key: PreferencesKey, defValue: String? = null): String? {
        val result: String?
        if (cacheEnabled(key) && cache.containsKey(key)) {
            result = cache[key] as String? ?: defValue
            Timber.d("getting persisted cache preference $key <- $result")
        } else {
            result = preferences.getString(key.keyString(), defValue)
            Timber.d("getting persisted preference $key <- $result")
        }
        return result
    }

    protected open fun getBoolean(key: PreferencesKey, defValue: Boolean = false): Boolean {
        val result: Boolean?
        if (cacheEnabled(key) && cache.containsKey(key)) {
            result = cache[key] as Boolean? ?: defValue
            Timber.d("getting persisted cache preference $key <- $result")
        } else {
            result = preferences.getBoolean(key.keyString(), defValue)
            Timber.d("getting persisted preference $key <- $result")
        }
        return result
    }

    protected open fun getInt(key: PreferencesKey, defValue: Int): Int {
        val result: Int?
        if (cacheEnabled(key) && cache.containsKey(key)) {
            result = cache[key] as Int? ?: defValue
            Timber.d("getting persisted cache preference $key <- $result")
        } else {
            result = preferences.getInt(key.keyString(), defValue)
            Timber.d("getting persisted preference $key <- $result")
        }
        return result
    }

    protected open fun getFloat(key: PreferencesKey, defValue: Float): Float {
        val result: Float?
        if (cacheEnabled(key) && cache.containsKey(key)) {
            result = cache[key] as Float? ?: defValue
            Timber.d("getting persisted cache preference $key <- $result")
        } else {
            result = preferences.getFloat(key.keyString(), defValue)
            Timber.d("getting persisted preference $key <- $result")
        }
        return result
    }

    protected open fun clear() {
        preferences.edit().clear().apply()
    }

    protected open fun clearKey(key: PreferencesKey) {
        preferences.edit().remove(key.keyString()).apply()
    }

    interface PreferencesKey {
        fun keyString(): String
    }
}