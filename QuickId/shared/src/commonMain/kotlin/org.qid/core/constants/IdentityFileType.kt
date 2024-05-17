package org.qid.core.constants

enum class IdentityFileType(val value: Int) {
    PASSPORT(1) {
        override fun toString(): String {
            return "Passport"
        }
    },
    ID(2) {
        override fun toString(): String {
            return "Id"
        }
    },
    DRIVER_LICENSE(3) {
        override fun toString(): String {
            return "License"
        }
    },
    CONTRACT(4) {
        override fun toString(): String {
            return "Contract"
        }
    },
    SOCIAL_SECURITY(5) {
        override fun toString(): String {
            return "Ssn"
        }
    },
    CAR_INSURANCE(6) {
        override fun toString(): String {
            return "Insurance"
        }
    },
    OTHER(7);

    companion object {
        fun fromInt(value: Int): IdentityFileType {
            return entries.first { it.value == value }
        }
    }
}
