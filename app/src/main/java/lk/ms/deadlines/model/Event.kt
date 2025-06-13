package lk.ms.deadlines.model

import java.time.LocalDateTime

data class Event(
    val name: String,
    val priority: String,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
    val description: String,
    val location: String,
    val remindMe: Boolean,
)