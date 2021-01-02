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

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonNull
import com.google.gson.JsonPrimitive

// thing -> json element

/**
 * Converts this [Boolean] to a [JsonPrimitive], or [JsonNull] if `this` is null.
 */
public fun Boolean?.toJsonElement(): JsonElement = if (this == null) JsonNull.INSTANCE else JsonPrimitive(this)

/**
 * Converts this [Number] to a [JsonPrimitive], or [JsonNull] if `this` is null.
 */
public fun Number?.toJsonElement(): JsonElement = if (this == null) JsonNull.INSTANCE else JsonPrimitive(this)

/**
 * Converts this [String] to a [JsonPrimitive], or [JsonNull] if `this` is null.
 */
public fun String?.toJsonElement(): JsonElement = if (this == null) JsonNull.INSTANCE else JsonPrimitive(this)

/**
 * Converts this [Char] to a [JsonPrimitive], or [JsonNull] if `this` is null.
 */
public fun Char?.toJsonElement(): JsonElement = if (this == null) JsonNull.INSTANCE else JsonPrimitive(this)

/**
 * Converts this [Array] to a [JsonArray].
 */
public fun Array<out JsonElement>.toJsonArray(): JsonArray {
    val array = JsonArray()

    this.forEach(array::add)

    return array
}
