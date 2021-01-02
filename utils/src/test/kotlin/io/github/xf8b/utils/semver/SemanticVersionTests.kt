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

package io.github.xf8b.utils.semver

import io.github.xf8b.utils.semver.SemanticVersion.Companion.SEMVER_REGEX
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SemanticVersionTests {
    @Test
    fun `test semver regex`() {
        assertTrue("1.0.0-alpha1+build.100" matches SEMVER_REGEX.toRegex())
        assertFalse("1.0-SNAPSHOT+build.140" matches SEMVER_REGEX.toRegex())
        assertTrue("1.2.3-------------------alpha.1+build103" matches SEMVER_REGEX.toRegex())
        assertFalse("1.4+beta.4" matches SEMVER_REGEX.toRegex())
    }

    @Test
    fun `test semver parsing`() {
        val version = "1.2.5-beta.3+build.134"
        val semanticVersion = SemanticVersion(version)

        assertTrue(semanticVersion.majorVersion == 1)
        assertTrue(semanticVersion.minorVersion == 2)
        assertFalse(semanticVersion.patchVersion == 4)
        assertFalse(semanticVersion.preRelease == "beta.4")
        assertTrue(semanticVersion.buildMetadata == "build.134")
    }

    @Test
    fun `test semver to string`() {
        val versionsToTest = arrayOf(
            "1.2.5-alpha.6+build.23",
            "4.5.6",
            "6.4.3-rc.1",
            "3.4.5+build.43"
        )

        versionsToTest.map { it to SemanticVersion(it) }.forEach { (stringVersion, semanticVersion) ->
            assertTrue(semanticVersion.toStringVersion() == stringVersion)
        }
    }
}