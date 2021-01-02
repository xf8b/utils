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
 *
 * @param version the version to parse a semantic version from
 * @throws IllegalArgumentException if the version passed in does not follow semantic versioning
 * @since 1.0.0-alpha4
 * @author xf8b
 */
public class SemanticVersion(version: String) {
    /**
     * The major version (*x*.1.0) of this semantic version, as defined in [the Semantic Versioning website](https://semver.org/).
     */
    public val majorVersion: Int

    /**
     * The minor version (0.*x*.0) of this semantic version, as defined in [the Semantic Versioning website](https://semver.org/).
     */
    public val minorVersion: Int

    /**
     * The patch version (0.1.*x*) of this semantic version, as defined in [the Semantic Versioning website](https://semver.org/).
     */
    public val patchVersion: Int

    /**
     * The pre-release label of this semantic version, as defined in [the Semantic Versioning website](https://semver.org/).
     */
    public val preRelease: String

    /**
     * The build metadata label of this semantic version, as defined in [the Semantic Versioning website](https://semver.org/).
     */
    public val buildMetadata: String

    init {
        if (version matches SEMVER_REGEX.toRegex()) {
            val matchResult = SEMVER_REGEX.toRegex().find(version)!!
            matchResult.destructured.apply {
                majorVersion = component1().toInt()
                minorVersion = component2().toInt()
                patchVersion = component3().toInt()
                preRelease = component4()
                buildMetadata = component5()
            }
        } else {
            throw IllegalArgumentException("Version '$version' does not conform to SemVer!")
        }
    }

    public companion object {
        /**
         * Regex for SemVer parsing and validating.
         *
         * Taken from [the Semantic Versioning website](https://semver.org/#is-there-a-suggested-regular-expression-regex-to-check-a-semver-string).
         */
        public const val SEMVER_REGEX: String =
            "^(0|[1-9]\\d*)\\.(0|[1-9]\\d*)\\.(0|[1-9]\\d*)(?:-((?:0|[1-9]\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*)(?:\\.(?:0|[1-9]\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*))*))?(?:\\+([0-9a-zA-Z-]+(?:\\.[0-9a-zA-Z-]+)*))?$"
    }

    /**
     * Converts this [SemanticVersion] back to the original parsed [String] version.
     */
    public fun toStringVersion(): String {
        var stringVersion = "$majorVersion.$minorVersion.$patchVersion"

        if (preRelease.isNotBlank()) {
            stringVersion += "-$preRelease"
        }

        if (buildMetadata.isNotBlank()) {
            stringVersion += "+$buildMetadata"
        }

        return stringVersion
    }

    public override fun equals(other: Any?): Boolean {
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

    public override fun hashCode(): Int {
        var result = majorVersion.hashCode()
        result = 31 * result + minorVersion.hashCode()
        result = 31 * result + patchVersion.hashCode()
        result = 31 * result + preRelease.hashCode()
        result = 31 * result + buildMetadata.hashCode()

        return result
    }

    public override fun toString(): String = "SemanticVersion(" +
            "majorVersion=$majorVersion, " +
            "minorVersion=$minorVersion, " +
            "patchVersion=$patchVersion, " +
            "preRelease='$preRelease', " +
            "buildMetadata='$buildMetadata'" +
            ")"
}