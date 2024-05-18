package org.qid.di

actual fun createGuid(): String {
    return java.util.UUID.randomUUID().toString()
}