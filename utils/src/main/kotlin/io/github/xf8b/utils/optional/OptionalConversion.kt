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

//to optional/null value
fun <T> T?.toOptional() = Optional.ofNullable(this)

fun <T> Optional<T>.toNullable(): T? = this.orElse(null)

@Deprecated(
        message = "Use toNullable! This is scheduled for removal in 1.0.0-alpha6!",
        replaceWith = ReplaceWith("toNullable()")
)
fun <T> Optional<T>.toValueOrNull(): T? = this.toNullable()

/**
 * Note: [T] is the result type, and the [String] is the error message.
 *
 * @return A [Pair] of an Optional<[T]> (the result) and an Optional<[String]> (the error message).
 */
fun <T> Result<T>.toOptional() = result.toOptional() to errorMessage.toOptional()

/**
 * Note: [T] is the result type, and the [String] is the error message.
 *
 * @return A [Result] from the [Pair].
 */
fun <T> Pair<Optional<T>, Optional<String>>.toResult() = when {
    !first.isPresent && !second.isPresent -> Result.pass()
    !first.isPresent -> Result.failure(second.toNullable()!!)
    !second.isPresent -> Result.success(first.toNullable()!!)
    else -> throw UnexpectedException("Unexpected reach of 'else' branch")
}