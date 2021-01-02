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

@file:JvmName("OptionalConversion")

package io.github.xf8b.utils.optional

import io.github.xf8b.utils.exceptions.UnexpectedException
import java.util.*

// to optional/null value

/**
 * Converts a nullable [T] to an [Optional].
 */
public fun <T> T?.toOptional(): Optional<T> = Optional.ofNullable(this)

/**
 * Converts an [Optional] to a nullable [T].
 */
public fun <T> Optional<T>.toNullable(): T? = this.orElse(null)

/**
 * Converts a [Result] to a [Pair], the first value being the [optional result][Optional] and the second being the [optional error message][Optional].
 */
public fun <T> Result<T>.toOptional(): Pair<Optional<T>, Optional<String>> =
    result.toOptional() to errorMessage.toOptional()

/**
 * Converts a [Pair] (the first value being the [optional result][Optional] and the second being the [optional error message][Optional]) to a [Result].
 */
public fun <T> Pair<Optional<T>, Optional<String>>.toResult(): Result<T> = when {
    !first.isPresent && !second.isPresent -> Result.pass()
    !first.isPresent -> Result.failure(second.get())
    !second.isPresent -> Result.success(first.get()!!)
    else -> throw UnexpectedException("Unexpected reach of 'else' branch")
}