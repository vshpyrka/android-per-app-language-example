# android-per-app-language-example

Per-app language preferences help user to select other languages for specific apps, such as Dutch, Chinese, or Hindi.

Example:

https://github.com/vshpyrka/android-per-app-language-example/assets/2741602/36427e6e-506d-4160-8085-39fb58d66754


* Create file `res/xml.locales_config.xml`
Example:
```
<?xml version="1.0" encoding="utf-8"?>
<locale-config xmlns:android="http://schemas.android.com/apk/res/android">
   <locale android:name="en-US"/>
   <locale android:name="ua"/>
</locale-config>
```
* Add AndroidManifest attribute to use locale configuration file
```
<manifest>
    <application
        android:localeConfig="@xml/locales_config">
    </application>
</manifest>
```
* Note! This only works if this attribute is specified in AndroidManifest.xml file which is in the main `app` module.

* `AppCompatDelegate.getApplicationLocales()` helps to retrieve users`s preferred locale that can be set from system settings:

https://github.com/vshpyrka/android-per-app-language-example/assets/2741602/296c7d59-b796-477d-956a-2e02cb988183

`AppCompatDelegate.setApplicationLocales` allowes to set preferred app language from the code. Calling this method can initiate activity `recreate()` method.

```
AppCompatDelegate.setApplicationLocales(
    LocaleListCompat.forLanguageTags("en")
)
```

* Android 12 and lower

`AppLocalesMetadataHolderService` from appcompat library helps to automatically store preferred application language on devices running Android 12(API level 32) and lower.

```
<application
  ...
  <service
    android:name="androidx.appcompat.app.AppLocalesMetadataHolderService"
    android:enabled="false"
    android:exported="false">
    <meta-data
      android:name="autoStoreLocales"
      android:value="true" />
  </service>
  ...
</application>
```

https://github.com/vshpyrka/android-per-app-language-example/assets/2741602/24debc91-df6d-4313-af12-f31c9a37ad96

* Starting with Gradle AGP 8.1 support of per-app launguage can be set automatically. Plugin will analyse `res` folder to track and include them into LocaleConfig file and will automatically add appropriate configuration into Android Manifest.xml file. 

Enable gradle configuration in main `app` module (it only works in android module, not library module)
```
android {
  androidResources {
    generateLocaleConfig = true
  }
}
```

* Supported languages in gradle specification

`resourceConfigurations` attribute helps to specify and reduce the amount of supported languages, consequently preventing include of languages from other libraries that the app might not support.
```
android {
  defaultConfig {
    resourceConfigurations += ["en", "ua"]
  }
}
```


More information about [Per App Language Support](https://developer.android.com/guide/topics/resources/app-languages)
