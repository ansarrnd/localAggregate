package org.deenlabs.localsaggregate.utils

/**
 * Formats a Double to a currency string (e.g., $12.34).
 * This is a simple multiplatform-safe implementation.
 */
fun formatPrice(price: Double): String {
    val priceAsLong = (price * 100).toLong()
    val dollars = priceAsLong / 100
    val cents = priceAsLong % 100
    return "\$${dollars}.${cents.toString().padStart(2, '0')}"
}