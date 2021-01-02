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

package io.github.xf8b.utils.sorting

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class MapSortersTests {
    @Test
    fun `test sorting a map of ints to strings`() {
        val map = mapOf(3 to '4', 5 to '6', 2 to '2')

        // after sorting, should be mapOf(2 to '2', 3 to '4', 5 to '6')
        val sortedMap = map.sortByValue()
        println(sortedMap)

        assertTrue(sortedMap == mapOf(2 to '2', 3 to '4', 5 to '6'))
    }

    @Test
    fun `test sorting a map of chars to strings`() {
        val map = mapOf('b' to "bruh", 'c' to "bean", 'a' to "pro", 'z' to "app").also { println("Original: $it") }

        // after sorting, should be mapOf('z' to "app", 'c' to "bean", 'b' to "bruh", 'a' to "pro")
        val sortedMap = map.toSortedMap()
        println("Sorted: $sortedMap")

        assertTrue(sortedMap == mapOf('z' to "app", 'c' to "bean", 'b' to "bruh", 'a' to "pro"))
    }
}