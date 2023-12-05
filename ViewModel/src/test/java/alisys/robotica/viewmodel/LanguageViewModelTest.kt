package alisys.robotica.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LanguageViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: LanguageViewModel

    @Before
    fun setUp() {
        viewModel = LanguageViewModel()
    }

    @Test
    fun `setLanguage updates current language if different`() {
        val observer = mockk<Observer<String>>(relaxed = true)
        viewModel.currentLanguage.observeForever(observer)

        viewModel.setLanguage("en")

        verify { observer.onChanged("en") }
    }

    @Test
    fun `updateLanguages updates language list`() {
        val observer = mockk<Observer<List<Pair<String, String>>>>(relaxed = true)
        viewModel.languages.observeForever(observer)

        val newLanguages = listOf("fr" to "Français", "de" to "Deutsch")
        viewModel.updateLanguages(newLanguages)

        verify { observer.onChanged(newLanguages) }
    }
}