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

import java.util.*

//to optional/null value
fun <T> T?.toOptional(): Optional<T> = Optional.ofNullable(this)

fun <T> Optional<T>.toValueOrNull(): T? = this.orElse(null)

/**
 * Note: [T] is the result type, and the [String] is the error message.
 *
 * @return A [Pair] of an Optional<[T]> (the result) and an Optional<[String]> (the error message).
 */
fun <T> Result<T>.toOptional(): Pair<Optional<T>, Optional<String>> = result.toOptional() to errorMessage.toOptional()

/**
 * Note: [T] is the result type, and the [String] is the error message.
 *
 * @return A [Result] from the [Pair].
 */
fun <T> Pair<Optional<T>, Optional<String>>.toResult(): Result<T> = when {
    first.isEmpty && second.isEmpty -> Result.pass()
    first.isEmpty -> Result.failure(second.get())
    second.isEmpty -> Result.success(first.get())
    else -> throw IllegalStateException("All branches should have been accounted for")
}