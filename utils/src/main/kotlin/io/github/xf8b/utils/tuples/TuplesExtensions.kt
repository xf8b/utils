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

package io.github.xf8b.utils.tuples

/**
 * Alias for [to].
 */
public infix fun <A, B> A.and(that: B): Pair<A, B> = this to that

/**
 * "Adds" [that] to this [Pair], creating a [Triple].
 */
public infix fun <A, B, C> Pair<A, B>.and(that: C): Triple<A, B, C> = Triple(first, second, that)
