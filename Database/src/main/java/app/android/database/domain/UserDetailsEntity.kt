package app.android.database.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.android.database.utils.Constants.TABLE_USER_DETAILS

@Entity(tableName = TABLE_USER_DETAILS)
data class UserDetailsEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val mediumImageUrl: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val gender: String,
    val registrationDate: String,
    val phone: String,
    val location: String
)