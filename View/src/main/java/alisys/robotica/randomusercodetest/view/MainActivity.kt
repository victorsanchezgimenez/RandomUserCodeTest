package alisys.robotica.randomusercodetest.view

import alisys.robotica.randomusercodetest.view.navigate.AppNavigation
import alisys.robotica.viewmodel.LanguageViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val languageViewModel: LanguageViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation(languageViewModel)
        }
    }
}