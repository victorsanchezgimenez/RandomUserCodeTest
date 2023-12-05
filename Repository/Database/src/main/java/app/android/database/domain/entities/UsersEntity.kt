package app.android.database.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.android.database.utils.Constants.TABLE_USERS

@Entity(tableName = TABLE_USERS)
data class UsersEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "gender")
    val gender: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "first_name")
    val firstName: String,

    @ColumnInfo(name = "last_name")
    val lastName: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "dob")
    val dateOfBirth: Long, // Considera almacenar como Long para fechas

    @ColumnInfo(name = "registered")
    val registeredDate: Long, // Considera almacenar como Long para fechas

    @ColumnInfo(name = "phone")
    val phone: String,

    @ColumnInfo(name = "cell")
    val cell: String,

    @ColumnInfo(name = "nat")
    val nat: String,

    @ColumnInfo(name = "large_picture")
    val largePicture: String,

    @ColumnInfo(name = "medium_picture")
    val mediumPicture: String,

    @ColumnInfo(name = "thumbnail_picture")
    val thumbnailPicture: String
)