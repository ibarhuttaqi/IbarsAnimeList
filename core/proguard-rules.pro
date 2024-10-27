# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Keep the domain and usecase packages
-keep class com.example.ibarsanimelist.core.domain.** { *; }
-keep class com.example.ibarsanimelist.core.domain.usecase.** { *; }
-keep class com.example.ibarsanimelist.core.ui.** { *; }
-keep class com.example.ibarsanimelist.core.utils.DataMapper { *; }

# Keep any other packages referenced in the error log, like di, model, repository, etc.
-keep class com.example.ibarsanimelist.core.di.** { *; }
-keep class com.example.ibarsanimelist.core.domain.model.** { *; }
-keep class com.example.ibarsanimelist.core.domain.repository.** { *; }
#-printconfiguration proguard-config.txt

-keep class com.example.ibarsanimelist.core.data.source.remote.response.** { *; }
-keepattributes Signature
-keepattributes *Annotation*

-keep class com.google.gson.reflect.TypeToken { *; }
-keepclassmembers class com.google.gson.reflect.TypeToken

-keep class com.example.ibarsanimelist.core.data.source.local.room.** { *; }

-keep class com.example.ibarsanimelist.core.data.source.local.Converters { *; }
-keepclassmembers class com.example.ibarsanimelist.core.data.source.local.Converters { *; }

-keep class * extends com.google.gson.reflect.TypeToken
-keepclassmembers class * {
    com.google.gson.reflect.TypeToken typeToken;
}

# Menjaga seluruh kelas Resource dan subclassnya tetap utuh
-keep class com.example.ibarsanimelist.core.data.Resource { *; }
-keepclassmembers class com.example.ibarsanimelist.core.data.Resource$* { *; }



