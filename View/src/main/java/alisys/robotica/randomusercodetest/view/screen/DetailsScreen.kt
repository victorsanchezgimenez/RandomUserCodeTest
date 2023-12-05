package alisys.robotica.randomusercodetest.view.screen

import alisys.robotica.randomusercodetest.R
import alisys.robotica.randomusercodetest.view.screen.components.CustomTopAppBar
import alisys.robotica.randomusercodetest.view.utils.extensions.getLocalizedString
import alisys.robotica.viewmodel.LanguageViewModel
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import java.util.Locale

@Composable
fun DetailsScreen(
    navController: NavHostController,
    languageViewModel: LanguageViewModel,
    firstName: String,
    lastName: String,
    email: String,
    gender: String,
    registerDate: String,
    phone: String,
    thumbnail: String
) {

    //UI sizes
    val imageHeight = with(LocalDensity.current) { LocalConfiguration.current.screenHeightDp.dp / 5 }
    val profileImageSize = 80.dp
    val profileImageOffset = profileImageSize / 2
    val horizontalPadding = 55.dp

    //Localization and Language Management
    val currentLanguage by languageViewModel.currentLanguage.observeAsState("es")
    val context = LocalContext.current

    //Strings
    val nameString = context.getLocalizedString(R.string.name, Locale(currentLanguage))
    val emailString = context.getLocalizedString(R.string.email, Locale(currentLanguage))
    val genderString = context.getLocalizedString(R.string.gender, Locale(currentLanguage))
    val registerDateString = context.getLocalizedString(R.string.registration_date, Locale(currentLanguage))
    val phoneString = context.getLocalizedString(R.string.phone, Locale(currentLanguage))
    val addressString = context.getLocalizedString(R.string.address, Locale(currentLanguage))


    Box(modifier = Modifier.background(Color.White)) {
        Image(
            painter = painterResource(id = R.drawable.image_background),
            contentDescription = "Background Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight),
            contentScale = ContentScale.Crop
        )

        // TopAppBar
        CustomTopAppBar(
            titleText = "$firstName $lastName",
            backgroundColor = Color.Transparent,
            contentColor = Color.White,
            languageViewModel = languageViewModel,
            onBackPress = { navController.navigate("UsersScreen") },

        )

        CircleImage(
            imageUrl = thumbnail,
            modifier = Modifier
                .offset(
                    x = (-profileImageOffset + horizontalPadding),
                    y = (imageHeight - profileImageOffset)
                )
                .size(profileImageSize)
        )

        IconRowBelowImage(
            imageHeight = imageHeight,
            onEditClick = { /* ... */ },
            onChangeClick = { /* ... */ }
        )

        LazyColumn(modifier = Modifier.padding(top = imageHeight + profileImageSize/2)) {
            item {

                DetailCard(
                    iconId = R.drawable.baseline_person_outline_24,
                    title = nameString,
                    detail = "$firstName $lastName"
                )
                DetailCard(
                    iconId = R.drawable.baseline_email_24,
                    title = emailString,
                    detail = email
                )
                DetailCard(
                    iconId = if (gender == "female") R.drawable.baseline_female_24 else R.drawable.baseline_male_24,
                    title = genderString,
                    detail = gender
                )
                DetailCard(
                    iconId = R.drawable.baseline_calendar_today_24,
                    title = registerDateString,
                    detail = registerDate
                )
                DetailCard(
                    iconId = R.drawable.baseline_local_phone_24,
                    title = phoneString,
                    detail = phone
                )
                AddressSection(
                    mapPlaceholderId = R.drawable.map_placeholder,
                    title = addressString)
            }
        }
    }
}




@Composable
fun DetailCard(
    @DrawableRes iconId: Int,
    title: String,
    detail: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = title,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(32.dp))
            Column {
                Text(text = title, color = Color.Gray, fontSize = 14.sp)
                Text(text = detail, fontWeight = FontWeight.Bold ,fontSize = 18.sp)
            }
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 75.dp, end = 20.dp)
    ) {
        Divider(
            color = Color.LightGray,
            thickness = 1.dp
        )
    }
}

@Composable
fun CircleImage(imageUrl: String, modifier: Modifier = Modifier) {
    Image(
        painter = rememberAsyncImagePainter(model = imageUrl),
        contentDescription = "Profile image",
        modifier = modifier
            .clip(CircleShape)
            .border(4.dp, Color.White, CircleShape)
    )
}

@Composable
fun IconRowBelowImage(imageHeight: Dp, onEditClick: () -> Unit, onChangeClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = imageHeight, end = 10.dp),
        horizontalArrangement = Arrangement.End
    ) {
        IconButton(onClick = onEditClick) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_photo_camera_24),
                contentDescription = "Edit photo",
                tint = Color.Gray
            )
        }
        Spacer(modifier = Modifier.width(2.dp))
        IconButton(onClick = onChangeClick) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_mode_edit_outline_24),
                contentDescription = "Change photo",
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun AddressSection(
    @DrawableRes mapPlaceholderId: Int,
    title: String,

) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 75.dp)
    ) {
        Text(
            text = title,
            color = Color.Gray,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )
        Image(
            painter = painterResource(id = mapPlaceholderId),
            contentDescription = "Map",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, end = 16.dp)
                .aspectRatio(16f / 9f),
            contentScale = ContentScale.Crop
        )
    }
}
