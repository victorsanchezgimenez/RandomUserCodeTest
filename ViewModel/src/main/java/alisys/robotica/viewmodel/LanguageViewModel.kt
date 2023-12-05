package alisys.robotica.viewmodel

import alisys.robotica.viewmodel.utils.LanguageConstants
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LanguageViewModel : ViewModel() {

    private val _currentLanguage = MutableLiveData("es")
    val currentLanguage: LiveData<String> = _currentLanguage

    private val _languages = MutableLiveData(LanguageConstants.languages)
    val languages: LiveData<List<Pair<String, String>>> = _languages

    fun setLanguage(language: String) {
        if (_currentLanguage.value != language) {
            _currentLanguage.value = language
            Log.d("LanguageViewModel", "Idioma cambiado a: $language")
        }
    }

    fun updateLanguages(newLanguages: List<Pair<String, String>>) {
        _languages.value = newLanguages
    }
}


