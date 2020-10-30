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

import org.junit.Assert.assertTrue
import org.junit.Test

class MapSortersTest {
    @Test
    fun `test sorting a map of ints`() {
        val map: Map<Int, String> = mapOf(3 to "4", 5 to "6", 2 to "2")
        //after sorting, should be mapOf(2 to '2', 3 to '4', 5 to '6')
        val sortedMap: LinkedHashMap<Int, String> = map.sortByValue() as LinkedHashMap<Int, String>
        println(sortedMap)
        sortedMap.onEachIndexed { index: Int, entry: Map.Entry<Int, String> ->
            if (index == 0) {
                assertTrue(entry.value == "2")
            }
            if (index == 2) {
                assertTrue(entry.value == "6")
            }
        }
    }
}