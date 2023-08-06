package com.example.shapovalov

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun formatDate(date: String): String{
	val inputFormatter = DateTimeFormatter.ISO_DATE_TIME
	val outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

	val dateTime = LocalDateTime.parse(date, inputFormatter)
	return dateTime.format(outputFormatter)
}

fun formatDateTime(dateTime: String): String {
	val inputFormatter = DateTimeFormatter.ISO_DATE_TIME
	val outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")

	val parsedDateTime = LocalDateTime.parse(dateTime, inputFormatter)
	return parsedDateTime.format(outputFormatter)
}