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

import java.util.*
import java.util.Map.Entry.comparingByValue

// credit: https://stackoverflow.com/questions/109383/sort-a-mapkey-value-by-values
// modified to be in kotlin and be an extension function
fun <K, V : Comparable<V>> Map<K, V>.sortByValue(): Map<K, V> {
    val list = ArrayList(this.entries)
    list.sortWith(comparingByValue())
    val result = LinkedHashMap<K, V>()
    list.forEach { result[it.key] = it.value }

    return result
}
