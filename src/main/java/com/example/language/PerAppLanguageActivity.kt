package com.example.language

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.language.databinding.ActivityPerAppLanguageBinding

class PerAppLanguageActivity : AppCompatActivity() {

    /*

    // Old way of handling language change and altering context
    // with modified resource configuration instance

    override fun attachBaseContext(newBase: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            val language = getLocale()
            super.attachBaseContext(updateResources(newBase, language))
        } else {
            super.attachBaseContext(newBase)
        }
    }

    override fun getApplicationContext(): Context {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            val applicationContext = super.getApplicationContext()
            val language = getLocale()
            updateResources(applicationContext, language)
        } else {
            super.getApplicationContext()
        }
    }

    override fun getBaseContext(): Context {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            val baseContext = super.getBaseContext()
            val language = getLocale()
            updateResources(baseContext, language)
        } else {
            super.getBaseContext()
        }
    }

    private fun getLocale(): String {
        val applicationLocales = AppCompatDelegate.getApplicationLocales()
        return applicationLocales.toLanguageTags().ifEmpty { "en" }
    }

    override fun createConfigurationContext(overrideConfiguration: Configuration): Context {
        val createConfigurationContext = super.createConfigurationContext(overrideConfiguration)
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            val language = getLocale()
            updateResources(createConfigurationContext, language)
        } else {
            return createConfigurationContext
        }
    }

    private fun updateResources(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)
        return context.createConfigurationContext(configuration)
    }

     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPerAppLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitle(R.string.per_app_language_activity_title)

        val applicationLocales = AppCompatDelegate.getApplicationLocales()
        when (applicationLocales[0]?.language) {
            "en", "en_US" -> {
                binding.english.isChecked = true
            }
            "uk", "uk_UA" -> {
                binding.ukrainian.isChecked = true
            }
        }

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.english -> {
                    AppCompatDelegate.setApplicationLocales(
                        LocaleListCompat.forLanguageTags("en")
                    )
                }
                R.id.ukrainian -> {
                    AppCompatDelegate.setApplicationLocales(
                        LocaleListCompat.forLanguageTags("uk")
                    )
                }
            }

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                recreate()
            }
        }
    }
}
