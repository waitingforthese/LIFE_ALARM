package com.mahaesuvidha.chandrapanchangalarm.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings

import com.mahaesuvidha.chandrapanchangalarm.model.LiveMoonCalculator
import com.mahaesuvidha.chandrapanchangalarm.model.LiveSunCalculator
import com.mahaesuvidha.chandrapanchangalarm.model.LivePanchangCalculator
import com.mahaesuvidha.chandrapanchangalarm.settings.AlarmPrefs
import com.mahaesuvidha.chandrapanchangalarm.settings.LocationPrefs

class AlarmScheduler(
    private val context: Context
) {

    private val alarmManager =
        context.getSystemService(
            AlarmManager::class.java
        )

    // ==========================================
    // TEST ALARMS
    // ==========================================

    fun scheduleRashiAlarm(
        delayMillis: Long
    ) {
        schedule(
            id = 101,
            at = System.currentTimeMillis() + delayMillis,
            title = "🌙 चंद्र राशी बदल",
            message = "ही राशी बदलाची टेस्ट सूचना आहे."
        )
    }

    fun scheduleNakshatraAlarm(
        delayMillis: Long
    ) {
        schedule(
            id = 102,
            at = System.currentTimeMillis() + delayMillis,
            title = "🌙 चंद्र नक्षत्र बदल",
            message = "ही नक्षत्र बदलाची टेस्ट सूचना आहे."
        )
    }

    fun scheduleCharanAlarm(
        delayMillis: Long
    ) {
        schedule(
            id = 103,
            at = System.currentTimeMillis() + delayMillis,
            title = "🌙 चंद्र चरण बदल",
            message = "ही चरण बदलाची टेस्ट सूचना आहे."
        )
    }

    fun schedulePraharAlarm(
        delayMillis: Long
    ) {
        schedule(
            id = 104,
            at = System.currentTimeMillis() + delayMillis,
            title = "⌛ प्रहर बदल",
            message = "ही प्रहर बदलाची टेस्ट सूचना आहे."
        )
    }

    fun scheduleLagnaAlarm(
        delayMillis: Long
    ) {
        schedule(
            id = 105,
            at = System.currentTimeMillis() + delayMillis,
            title = "⭐ लग्न बदल",
            message = "ही लग्न बदलाची टेस्ट सूचना आहे."
        )
    }

    // ==========================================
    // SCHEDULE ALL ALARMS
    // ==========================================

    fun scheduleAll() {

        cancelAll()

        val prefs = AlarmPrefs(context)
        val location = LocationPrefs(context)

        // ==========================================
        // MOON ALARMS
        // ==========================================

        if (prefs.moonRashi || prefs.moonNakshatra || prefs.moonCharan) {

            val moon =
                LiveMoonCalculator
                    .getCurrentMoonState()

            if (prefs.moonRashi) {

                schedule(
                    id = 1,
                    at = moon.nextRashiMillis,
                    title = "🌙 चंद्र राशी बदल",
                    message = moon.nextRashi
                )
            }

            if (prefs.moonNakshatra) {

                schedule(
                    id = 2,
                    at = moon.nextNakshatraMillis,
                    title = "🌙 चंद्र नक्षत्र बदल",
                    message = moon.nextNakshatra
                )
            }

            if (prefs.moonCharan) {

                schedule(
                    id = 3,
                    at = moon.nextCharanMillis,
                    title = "🌙 चंद्र चरण बदल",
                    message = moon.nextCharan
                )
            }
        }

        // ==========================================
        // SUN ALARMS
        // ==========================================

        if (prefs.sunRashi || prefs.sunNakshatra || prefs.sunCharan) {

            val sun =
                LiveSunCalculator
                    .getCurrentSunState()

            if (prefs.sunRashi) {

                schedule(
                    id = 11,
                    at = sun.nextRashiMillis,
                    title = "☀️ सूर्य राशी बदल",
                    message = sun.nextRashi
                )
            }

            if (prefs.sunNakshatra) {

                schedule(
                    id = 12,
                    at = sun.nextNakshatraMillis,
                    title = "☀️ सूर्य नक्षत्र बदल",
                    message = sun.nextNakshatra
                )
            }

            if (prefs.sunCharan) {

                schedule(
                    id = 13,
                    at = sun.nextCharanMillis,
                    title = "☀️ सूर्य चरण बदल",
                    message = sun.nextCharan
                )
            }
        }

        // ==========================================
        // PANCHANG ALARMS
        // ==========================================

        if (prefs.tithiAlarm || prefs.yogaAlarm || prefs.karanaAlarm || prefs.pakshaAlarm || prefs.praharAlarm || prefs.lagnaAlarm) {

            val panchang =
                LivePanchangCalculator
                    .getCurrentPanchangState(
                        latitude = location.latitude,
                        longitude = location.longitude
                    )

            if (prefs.tithiAlarm) {

                schedule(
                    id = 21,
                    at = panchang.nextTithiMillis,
                    title = "🔔 तिथी बदल",
                    message =
                        "${panchang.tithi} → ${panchang.nextTithi}"
                )
            }

            if (prefs.yogaAlarm) {

                schedule(
                    id = 22,
                    at = panchang.nextYogaMillis,
                    title = "🔔 योग बदल",
                    message =
                        "${panchang.yoga} → ${panchang.nextYoga}"
                )
            }

            if (prefs.karanaAlarm) {

                schedule(
                    id = 23,
                    at = panchang.nextKaranaMillis,
                    title = "🔔 करण बदल",
                    message =
                        "${panchang.karana} → ${panchang.nextKarana}"
                )
            }

            if (prefs.pakshaAlarm) {

                schedule(
                    id = 24,
                    at = panchang.nextPakshaMillis,
                    title = "🔔 पक्ष बदल",
                    message =
                        "${panchang.paksha} → ${panchang.nextPaksha}"
                )
            }
            // ==========================================
// ==========================================
// PRAHAR ALARM
// ==========================================

if (prefs.praharAlarm) {

    schedule(
        id = 26,
        at = panchang.nextPraharMillis,
        title = "⌛ प्रहर बदल",
        message =
            "${panchang.prahar} → ${panchang.nextPrahar}"
    )
}


// ==========================================
// LAGNA ALARM
// ==========================================

if (prefs.lagnaAlarm) {

    schedule(
        id = 27,
        at = panchang.nextLagnaMillis,
        title = "⭐ लग्न बदल",
        message =
            "${panchang.lagna} → ${panchang.nextLagna}"
    )
}
        }
    }

    // ==========================================
    // GENERAL TEST ALARM
    // ==========================================

    fun scheduleTest(
        type: String
    ) {

        schedule(
            id = 99,
            at = System.currentTimeMillis() + 10_000L,
            title = "🔔 चंद्र सूर्य अलार्म",
            message = "$type Test Alarm आहे."
        )
    }

    // ==========================================
    // MAIN SCHEDULE FUNCTION
    // ==========================================

    private fun schedule(
        id: Int,
        at: Long,
        title: String,
        message: String
    ) {

        // Duplicate-control rule:
        // every real event has one stable ID. Before scheduling it,
        // cancel the existing PendingIntent with the same ID.
        cancel(id)

        // Never schedule an already-expired event.
        val safeAt =
            maxOf(
                at,
                System.currentTimeMillis() + 2_000L
            )

        val exactAllowed =
            Build.VERSION.SDK_INT < Build.VERSION_CODES.S ||
                    alarmManager.canScheduleExactAlarms()

        if (
            !exactAllowed &&
            id != 99 &&
            id !in 101..105
        ) {
            try {
                val intent =
                    Intent(
                        Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM
                    ).apply {
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }

                context.startActivity(intent)
            } catch (_: Exception) {
            }

            return
        }

        val intent =
            Intent(
                context,
                AlarmReceiver::class.java
            )

        intent.putExtra(
            "title",
            title
        )

        intent.putExtra(
            "message",
            message
        )

        intent.putExtra(
            "id",
            id
        )

        val pendingIntent =
            PendingIntent.getBroadcast(
                context,
                id,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or
                        PendingIntent.FLAG_IMMUTABLE
            )

        try {
            if (exactAllowed) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    safeAt,
                    pendingIntent
                )
            } else {
                alarmManager.setAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    safeAt,
                    pendingIntent
                )
            }
        } catch (_: SecurityException) {
            try {
                alarmManager.setAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    at,
                    pendingIntent
                )
            } catch (t: Throwable) {
                android.util.Log.e(
                    "LifeAlarm",
                    "Unable to schedule alarm id=$id",
                    t
                )
            }
        } catch (t: Throwable) {
            android.util.Log.e(
                "LifeAlarm",
                "Unable to schedule alarm id=$id",
                t
            )
        }
    }

    // ==========================================
    // CANCEL ALL ALARMS
    // ==========================================

    fun cancelAll() {

        // All persistent alarms:
        // 1..3   = Moon: Rashi / Nakshatra / Charan
        // 11..13 = Sun:  Rashi / Nakshatra / Charan
        // 21..24 = Panchang: Tithi / Yoga / Karana / Paksha
        // 26     = Prahar
        // 27     = Lagna
        for (id in 1..3) {
            cancel(id)
        }

        for (id in 11..13) {
            cancel(id)
        }

        for (id in 21..27) {
            cancel(id)
        }

        // Test alarms
        cancel(99)
        cancel(101)
        cancel(102)
        cancel(103)
        cancel(104)
        cancel(105)
    }

    // ==========================================
    // CANCEL SINGLE ALARM
    // ==========================================

    private fun cancel(
        id: Int
    ) {

        val pendingIntent =
            PendingIntent.getBroadcast(
                context,
                id,
                Intent(
                    context,
                    AlarmReceiver::class.java
                ),
                PendingIntent.FLAG_NO_CREATE or
                        PendingIntent.FLAG_IMMUTABLE
            )

        if (pendingIntent != null) {
            try {
                alarmManager.cancel(
                    pendingIntent
                )
            } catch (t: Throwable) {
                android.util.Log.e(
                    "LifeAlarm",
                    "Unable to cancel alarm id=$id",
                    t
                )
            }

            try {
                pendingIntent.cancel()
            } catch (_: Throwable) {
            }
        }
    }
}
