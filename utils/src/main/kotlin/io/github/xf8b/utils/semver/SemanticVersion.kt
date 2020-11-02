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

/**
 * An **immutable** object which represents a semantic version.
 * @param version the version to parse a semantic version from
 * @property majorVersion the major version, as defined in [https://semver.org/]
 * @property minorVersion the minor version, as defined in [https://semver.org/]
 * @property patchVersion the patch version, as defined in [https://semver.org/]
 * @property preRelease the pre-release label, as defined in [https://semver.org/]
 * @property buildMetadata the build metadata label, as defined in [https://semver.org/]
 * @throws IllegalArgumentException if the version does not follow semantic versioning
 * @since 1.0.0-alpha4
 * @author xf8b
 */
class SemanticVersion(version: String) {
    val majorVersion: Int
    val minorVersion: Int
    val patchVersion: Int
    val preRelease: String
    val buildMetadata: String

    init {
        if (version matches SEMVER_REGEX.toRegex()) {
            val matchResult = SEMVER_REGEX.toRegex().find(version)!!
            majorVersion = matchResult.destructured.component1().toInt()
            minorVersion = matchResult.destructured.component2().toInt()
            patchVersion = matchResult.destructured.component3().toInt()
            preRelease = matchResult.destructured.component4()
            buildMetadata = matchResult.destructured.component5()
        } else {
            throw IllegalArgumentException("Version '$version' does not conform to SemVer!")
        }
    }

    companion object {
        /**
         * Regex for SemVer parsing and validating.
         *
         * Taken from [the Semantic Versioning website](https://semver.org/#is-there-a-suggested-regular-expression-regex-to-check-a-semver-string).
         */
        const val SEMVER_REGEX: String =
            "^(0|[1-9]\\d*)\\.(0|[1-9]\\d*)\\.(0|[1-9]\\d*)(?:-((?:0|[1-9]\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*)(?:\\.(?:0|[1-9]\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*))*))?(?:\\+([0-9a-zA-Z-]+(?:\\.[0-9a-zA-Z-]+)*))?$"
    }

    fun toStringVersion(): String {
        var stringVersion = "$majorVersion.$minorVersion.$patchVersion"

        if (preRelease.isNotBlank()) {
            stringVersion += "-$preRelease"
        }

        if (buildMetadata.isNotBlank()) {
            stringVersion += "+$buildMetadata"
        }

        return stringVersion
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SemanticVersion

        if (majorVersion != other.majorVersion) return false
        if (minorVersion != other.minorVersion) return false
        if (patchVersion != other.patchVersion) return false
        if (preRelease != other.preRelease) return false
        if (buildMetadata != other.buildMetadata) return false

        return true
    }

    override fun hashCode(): Int {
        var result = majorVersion.hashCode()
        result = 31 * result + minorVersion.hashCode()
        result = 31 * result + patchVersion.hashCode()
        result = 31 * result + preRelease.hashCode()
        result = 31 * result + buildMetadata.hashCode()
        return result
    }

    override fun toString(): String = "SemanticVersion(" +
            "majorVersion=$majorVersion, " +
            "minorVersion=$minorVersion, " +
            "patchVersion=$patchVersion, " +
            "preRelease='$preRelease', " +
            "buildMetadata='$buildMetadata'" +
            ")"
}