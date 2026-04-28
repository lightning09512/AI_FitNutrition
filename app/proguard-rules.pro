# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.

# Keep Room entities
-keep class com.nhom10.aifitnutrition.data.model.** { *; }
-keep class com.nhom10.aifitnutrition.data.dao.** { *; }

# Keep Gemini AI classes
-keep class com.google.ai.client.generativeai.** { *; }
-dontwarn com.google.ai.client.generativeai.**

# Keep MPAndroidChart
-keep class com.github.mikephil.charting.** { *; }

# Keep ViewBinding
-keep class * implements androidx.viewbinding.ViewBinding { *; }

# Keep Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class com.bumptech.glide.** { *; }

# Keep iText PDF (remove if not using)
-dontwarn com.itextpdf.**

# General Android
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
