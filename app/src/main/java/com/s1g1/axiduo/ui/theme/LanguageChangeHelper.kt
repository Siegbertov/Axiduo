package com.s1g1.axiduo.ui.theme

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.s1g1.axiduo.R

enum class AppLanguage(
    val code: String,
    val fullName: String,
    @DrawableRes val flag: Int,
){
    English(code = "en", fullName = "english", flag = R.drawable.en),
    Ukrainian(code = "uk", fullName = "українська", flag = R.drawable.uk);


    companion object{
        fun fromCode(codeString: String) : AppLanguage{
            return AppLanguage.entries.find{ it.code == codeString}?:AppLanguage.English
        }
    }

}

class LanguageChangeHelper {

    fun changeLanguage(context: Context, newLanguageCode: String){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            context.getSystemService(LocaleManager::class.java).applicationLocales = LocaleList.forLanguageTags(newLanguageCode)
        } else {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(newLanguageCode))
        }
    }

    fun getLanguageCode(context: Context): String{
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            context.getSystemService(LocaleManager::class.java).applicationLocales[0]?.toLanguageTag()?.split("-")?.first() ?: "en"
        } else {
            AppCompatDelegate.getApplicationLocales()[0]?.toLanguageTag()?.split("-")?.first() ?: "en"
        }
    }
}