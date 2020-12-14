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

/**
 * A object which represents a possible [T] result.
 *
 * Result types include:
 * * [ResultType.SUCCESS]: when there is no error, and the result is present.
 * * [ResultType.PASS]: when there is no error, but there is no result present.
 * * [ResultType.FAILURE]: when there is an error, and there is no result present.
 *
 * **Getting the [result] or the [errorMessage] without checking the [resultType]
 * will cause a [NoSuchElementException].**
 *
 * The [resultType] is always present.
 *
 * The recommended way to create a [Result] is to use one of the following **static** methods:
 * * [success]: when the result has been computed and there are no errors
 * * [pass]: when there is no result computed but no error
 * * [failure]: when an error has happened during the computation of the result
 *
 * **Note: When using [success], the [result] passed in may not be null.**
 *
 * @property result the possibly null result
 * @property errorMessage the possibly null error message
 * @property resultType the non-null result type
 * @constructor recommended to use [success], [failure] or [pass] instead of the constructor
 * @since 1.0.0-alpha1
 * @author xf8b
 */
class Result<out T>(result: T?, errorMessage: String?, val resultType: ResultType) {
    /**
     * The result. **May be null**.
     *
     * Check that the [resultType] is [ResultType.SUCCESS] or use [isSuccess] before accessing this.
     *
     * Getter returns a **non-null** value.
     * Safe to use non-null asserted call or [java.util.Objects.requireNonNull].
     *
     * @throws NoSuchElementException when no result is present
     */
    val result: T? = result
        get() = field ?: throw NoSuchElementException("No result is present!")

    /**
     * The error message, if there was any error. **May be null**.
     *
     * Check that the [resultType] is [ResultType.FAILURE] or use [isFailure] before accessing this.
     *
     * Intended for showing an error message to the user when there is an error, or for an exception message.
     *
     * Getter returns a **non-null** value.
     * Safe to use non-null asserted call or [java.util.Objects.requireNonNull].
     *
     * @throws NoSuchElementException when no error message is present.
     */
    val errorMessage: String? = errorMessage
        get() = field ?: throw NoSuchElementException("No error message is present!")

    /**
     * Getter for if this result's type is a [ResultType.SUCCESS].
     *
     * Useful for code that should be executed when there is a successful result.
     */
    fun isSuccess() = resultType == ResultType.SUCCESS

    /**
     * Getter for if this result's type is a [ResultType.FAILURE].
     *
     * Useful for code that should be executed when there is a failed result.
     */
    fun isFailure() = resultType == ResultType.FAILURE

    /**
     * Getter for if this result's type is a [ResultType.PASS].
     *
     * Useful for code that should be executed when there is a passed result.
     */
    fun isPass() = resultType == ResultType.PASS

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Result<*>

        if (result != other.result) return false
        if (errorMessage != other.errorMessage) return false
        if (resultType != other.resultType) return false

        return true
    }

    override fun hashCode(): Int {
        var result = result?.hashCode() ?: 0
        result = 31 * result + (errorMessage?.hashCode() ?: 0)
        result = 31 * result + resultType.hashCode()
        return result
    }

    override fun toString(): String = "Result(" +
            "result=$result, " +
            "errorMessage=$errorMessage, " +
            "resultType=$resultType" +
            ")"

    companion object {
        /**
         * Static method to create a [Result] that represents a successful computation.
         */
        @JvmStatic
        fun <T> success(result: T) = Result<T>(result, null, ResultType.SUCCESS)

        /**
         * Static method to create a [Result] that represents a failure of computing the result.
         */
        @JvmStatic
        fun <T> failure(errorMessage: String) = Result<T>(null, errorMessage, ResultType.FAILURE)

        /**
         * Static method to create a [Result] that represents a no error but also no result computation.
         */
        @JvmStatic
        fun <T> pass() = Result<T>(null, null, ResultType.PASS)
    }

    enum class ResultType {
        /**
         * Result type for success. Use when there is no error and the result has been computed.
         */
        SUCCESS,

        /**
         * Result type for passing. Use when there is no error but there has been no result computed.
         */
        PASS,

        /**
         * Result type for failure. Use when there is an error while computing the result.
         */
        FAILURE;
    }
}