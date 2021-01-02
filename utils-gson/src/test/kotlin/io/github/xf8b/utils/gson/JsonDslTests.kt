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

import com.google.gson.JsonNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class JsonDslTest {
    @Test
    fun `test using json function`() {
        val json = json {
            `object`("ok") {
                array(
                    "okes",
                    2.toJsonElement(),
                    "bean".toJsonElement(),
                    'c'.toJsonElement(),
                    JsonNull.INSTANCE
                )

                nullProperty(key = "hi")

                property(key = "noob", value = true)

                property(key = "year", value = 2021)
            }

            array("pros") {
                add("ag6")
                add("idk")
                add("turty")
                add("bean")
                add("ejrktgjihuareg")
            }

            `object`("pro") {}
        }.toJsonElement()

        println("Json: $json")

        assertTrue(json.has("ok"))
        assertTrue(json.get("ok").asJsonObject.get("okes").asJsonArray.contains(2.toJsonElement()))
        assertTrue(json.get("pro").asJsonObject.size() == 0)
        assertTrue(json.get("ok").asJsonObject.get("noob").asJsonPrimitive.asBoolean)
    }
}