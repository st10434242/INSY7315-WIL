package com.example.insy7315_wil_.data

import android.content.Context

class SessionManager(context: Context) {
    private val preferences = context.applicationContext.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)

    val isLoggedIn get() = preferences.getBoolean(KEY_LOGGED_IN, false)
    val isGuest get() = preferences.getBoolean(KEY_GUEST, false)
    val displayName get() = preferences.getString(KEY_NAME, "Sgula member").orEmpty()
    val email get() = preferences.getString(KEY_EMAIL, "").orEmpty()

    var sharesAnonymousInsights: Boolean
        get() = preferences.getBoolean(KEY_ANONYMOUS_INSIGHTS, true)
        set(value) = preferences.edit().putBoolean(KEY_ANONYMOUS_INSIGHTS, value).apply()

    var remindersEnabled: Boolean
        get() = preferences.getBoolean(KEY_REMINDERS, true)
        set(value) = preferences.edit().putBoolean(KEY_REMINDERS, value).apply()

    var activityProgressVisible: Boolean
        get() = preferences.getBoolean(KEY_PROGRESS_VISIBLE, true)
        set(value) = preferences.edit().putBoolean(KEY_PROGRESS_VISIBLE, value).apply()

    fun signIn(email: String, name: String? = null) {
        val resolvedName = name?.takeIf { it.isNotBlank() } ?: displayName.ifBlank { "Sgula member" }
        preferences.edit()
            .putBoolean(KEY_LOGGED_IN, true)
            .putBoolean(KEY_GUEST, false)
            .putString(KEY_NAME, resolvedName)
            .putString(KEY_EMAIL, email)
            .apply()
    }

    fun continueAsGuest() {
        preferences.edit().putBoolean(KEY_LOGGED_IN, false).putBoolean(KEY_GUEST, true).apply()
    }

    fun logout() {
        preferences.edit().putBoolean(KEY_LOGGED_IN, false).putBoolean(KEY_GUEST, false).apply()
    }

    private companion object {
        const val PREFERENCES = "sgula_session"
        const val KEY_LOGGED_IN = "logged_in"
        const val KEY_GUEST = "guest"
        const val KEY_NAME = "name"
        const val KEY_EMAIL = "email"
        const val KEY_ANONYMOUS_INSIGHTS = "anonymous_insights"
        const val KEY_REMINDERS = "reminders"
        const val KEY_PROGRESS_VISIBLE = "progress_visible"
    }
}
