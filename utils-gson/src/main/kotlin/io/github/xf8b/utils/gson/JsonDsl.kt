/*
 * Copyright (c) 2020 xf8b.
 *
 * This file is part of Utils.
 *
 * Utils is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Utils is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Utils.  If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.xf8b.utils.gson

import com.google.gson.*

class JsonDsl {
    private val properties: MutableMap<String, JsonElement> = mutableMapOf()

    fun `object`(name: String, apply: JsonDsl.() -> Unit) {
        val dsl = JsonDsl()
        apply(dsl)
        properties[name] = dsl.toJsonElement()
    }

    fun array(name: String, vararg elements: JsonElement) {
        properties[name] = elements.toJsonArray()
    }

    fun array(name: String, apply: JsonArray.() -> Unit) {
        properties[name] = JsonArray().apply(apply)
    }

    private fun internalProperty(key: String, value: Any?) {
        when {
            value == null -> properties[key] = JsonNull.INSTANCE
            Boolean::class.isInstance(value) -> properties[key] = JsonPrimitive(value as Boolean)
            Number::class.isInstance(value) -> properties[key] = JsonPrimitive(value as Number)
            String::class.isInstance(value) -> properties[key] = JsonPrimitive(value as String)
            Char::class.isInstance(value) -> properties[key] = JsonPrimitive(value as Char)
            JsonElement::class.isInstance(value) -> properties[key] = value as JsonElement
            else -> error("Internal function was called incorrectly - is someone reflecting to access?")
        }
    }

    fun property(key: String, value: JsonElement) {
        internalProperty(key, value)
    }

    fun property(key: String, value: Boolean?) {
        internalProperty(key, value)
    }

    fun property(key: String, value: Number?) {
        internalProperty(key, value)
    }

    fun property(key: String, value: String?) {
        internalProperty(key, value)
    }

    fun property(key: String, value: Char?) {
        internalProperty(key, value)
    }

    fun nullProperty(key: String) {
        internalProperty(key, null)
    }

    fun toJsonElement() = JsonObject().apply { properties.forEach(this::add) }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is JsonDsl) return false

        if (properties != other.properties) return false

        return true
    }

    override fun hashCode() = properties.hashCode()

    override fun toString() = this.toJsonElement().toString()
}

fun json(apply: JsonDsl.() -> Unit) = JsonDsl().apply(apply)
