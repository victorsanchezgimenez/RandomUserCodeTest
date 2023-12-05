package alisys.robotica.randomusercodetest.view.screen.components

import alisys.robotica.randomusercodetest.R
import alisys.robotica.viewmodel.LanguageViewModel
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp

@Composable
fun CustomTopAppBar(
    titleText: String,
    languageViewModel: LanguageViewModel,
    backgroundColor: Color = Color.White,
    contentColor: Color = Color.Black,
    onBackPress: () -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }
    val languages by languageViewModel.languages.observeAsState(listOf())

    TopAppBar(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        title = {
            Text(
                text = titleText,
                color = contentColor,
                fontFamily = FontFamily(Font(R.font.oswald))
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackPress) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = contentColor)
            }
        },
        actions = {
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(Icons.Filled.MoreVert, contentDescription = "More options", tint = contentColor)
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                languages.forEach { (code, name) ->
                    DropdownMenuItem(onClick = {
                        languageViewModel.setLanguage(code)
                        showMenu = false
                    }) {
                        Text(text = name, color = Color.Black)
                    }
                }
            }
        },
        elevation = 0.dp
    )
}



