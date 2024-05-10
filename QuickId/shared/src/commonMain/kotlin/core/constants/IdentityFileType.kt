package core.constants

enum class IdentityFileType {
    PASSPORT {
        override fun toString(): String {
            return "Passport"
        }
    },
    ID {
        override fun toString(): String {
            return "Id"
        }
    },
    DRIVER_LICENSE {
        override fun toString(): String {
            return "License"
        }
    },
    CONTRACT {
        override fun toString(): String {
            return "Contract"
        }
    },
    SOCIAL_SECURITY {
        override fun toString(): String {
            return "Ssn"
        }
    },
    CAR_INSURANCE {
        override fun toString(): String {
            return "Insurance"
        }
    },
    OTHER
}
