package com.egco428.qz3291.Models

class Point(val lat: Double, val lon: Double, val color: Boolean) {
    constructor(): this(0.toDouble(), 0.toDouble(), false)
}