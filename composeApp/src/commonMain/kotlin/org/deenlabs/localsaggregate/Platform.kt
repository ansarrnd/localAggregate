package org.deenlabs.localsaggregate

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform