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