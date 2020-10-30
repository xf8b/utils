package io.github.xf8b.utils.optional

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ResultTest {
    @Test
    fun `test result isX`() {
        val result: Result<Number> = Result.success(2.0f)

        assertFalse(result.isErrorMessagePresent())
        assertFalse(result.isPass())
        assertTrue(result.isResultPresent())
    }

    @Test
    fun `test static factory methods`() {
        assertTrue(Result.success(0).resultType == Result.ResultType.SUCCESS)
        assertTrue(Result.pass<Number>().resultType == Result.ResultType.PASS)
        assertTrue(Result.failure<Number>("bruh").resultType == Result.ResultType.FAILURE)
    }
}