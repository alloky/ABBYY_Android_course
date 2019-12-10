package task_5

import kotlin.reflect.KProperty
import java.lang.IllegalArgumentException

class StorageDelegate<T> {
    private var key: String = ""
    private val storage = Storage()

    constructor(key: String, value: T) {
        this.key = key
        when (value) {
            is Int -> storage.put(key, value)
            is Long -> storage.put(key, value)
            is Char -> storage.put(key, value)
            is Double -> storage.put(key, value)
            is String -> storage.put(key, value)
            else -> throw IllegalArgumentException()
        }
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        storage.put(key, value)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) {
        storage.put(key, value)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Char) {
        storage.put(key, value)
    }
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Double) {
        storage.put(key, value)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        storage.put(key, value)
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        val value: T? = storage.get(key) as? T
        if (value == null) {
            throw IllegalArgumentException()
        }
        return value
    }

}