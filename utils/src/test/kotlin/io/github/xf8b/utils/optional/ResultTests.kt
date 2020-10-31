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

package io.github.xf8b.utils.optional

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ResultTests {
    @Test
    fun `test result isX`() {
        val result: Result<Number> = Result.success(2.0f)

        assertFalse(result.isFailure())
        assertFalse(result.isPass())
        assertTrue(result.isSuccess())
    }

    @Test
    fun `test static factory methods`() {
        assertTrue(Result.success(0).resultType == Result.ResultType.SUCCESS)
        assertTrue(Result.pass<Number>().resultType == Result.ResultType.PASS)
        assertTrue(Result.failure<Number>("bruh").resultType == Result.ResultType.FAILURE)
    }
}