package alisys.robotica.randomusercodetest.view.utils.constants

import app.android.network.data.User
import java.net.URLEncoder

object NavUtils {

    fun createUserDetailsRoute(user: User): String {
        return buildString {
            append("DetailsScreen/")
            append(URLEncoder.encode(user.name.first, "UTF-8"))
            append("/")
            append(URLEncoder.encode(user.name.last, "UTF-8"))
            append("/")
            append(URLEncoder.encode(user.email, "UTF-8"))
            append("/")
            append(URLEncoder.encode(user.gender, "UTF-8"))
            append("/")
            append(URLEncoder.encode(user.registered.date.substring(0, 10), "UTF-8"))
            append("/")
            append(URLEncoder.encode(user.phone, "UTF-8"))
            append("/")
            append(URLEncoder.encode(user.picture.thumbnail, "UTF-8"))
        }
    }
}