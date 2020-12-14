# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), and this project adheres
to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

---

## [Unreleased]

---

## [1.0.0-alpha5] - 2020-12-14

### Added

- [`UnexpectedException`](utils/src/main/kotlin/io/github/xf8b/utils/exceptions/UnexpectedException.kt)
- [`toNullable` in `OptionalConversion`](utils/src/main/kotlin/io/github/xf8b/utils/optional/OptionalConversion.kt)

### Changed

- now compiles to Java 8, instead of 14
- kotlin 1.4.10 -> 1.4.21

### Deprecated

- [`toValueOrNull` in `OptionalConversion` - use `toNullable`](utils/src/main/kotlin/io/github/xf8b/utils/optional/OptionalConversion.kt)

### Removed

- [`isErrorMessagePresent` and `isResultPresent` in `Result`](utils/src/main/kotlin/io/github/xf8b/utils/optional/Result.kt)

---

## [1.0.0-alpha4] - 2020-11-2

### Added

- Semantic Versions
- `isPass`, `isFailure`, `isSuccess` in `Result`

### Deprecated

- `isErrorMessagePresent`, `isResultPresent` - scheduled for removal in alpha5

[Unreleased]: https://github.com/xf8b/utils/compare/v1.0.0-alpha5...HEAD

[1.0.0-alpha5]: https://github.com/xf8b/utils/compare/v1.0.0-alpha4...v1.0.0-alpha5

[1.0.0-alpha4]: https://github.com/xf8b/utils/releases/tag/v1.0.0-alpha4