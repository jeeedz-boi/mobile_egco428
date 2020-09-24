package com.egco.lab07_json.Model

data class RSSObject(val status: String, val feed:Feed, val items: List<Item>)