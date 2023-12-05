package alisys.robotica.randomusercodetest.view.utils.extensions

import android.content.Context
import android.content.res.Configuration
import androidx.annotation.StringRes
import java.util.Locale

fun Context.getLocalizedString(@StringRes id: Int, locale: Locale): String {
    val configuration: Configuration = resources.configuration
    configuration.setLocale(locale)
    return createConfigurationContext(configuration).resources.getString(id)
}