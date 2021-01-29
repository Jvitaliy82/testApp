-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** w(...);
    public static *** v(...);
    public static *** i(...);
}

# com.squareup.picasso:picasso
-dontwarn com.squareup.okhttp.**

-dontwarn sun.misc.Unsafe
-dontwarn com.google.common.collect.MinMaxPriorityQueue
-dontwarn com.google.common.util.concurrent.FuturesGetChecked**
-dontwarn javax.lang.model.element.Modifier
-dontwarn afu.org.checkerframework.**
-dontwarn org.checkerframework.**

# Logging
-dontwarn javax.mail.**
-dontwarn javax.naming.Context
-dontwarn javax.naming.InitialContext
-dontwarn ch.qos.logback.core.net.*
-dontwarn java.awt.**,javax.activation.**,java.beans.**

# com.squareup.retrofit2:retrofit
-dontwarn okio.**
-dontwarn javax.annotation.**
#-dontwarn retrofit2.Platform$Java8
-dontwarn retrofit2.**
#-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

# com.squareup.okhttp3:okhttp
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# other
-keep public class * extends java.lang.Exception
#-keep class com.appCraft.testApp.data.network.model.** { *; }
#-keep class java.awt.datatransfer.DataFlavor {*;}

# Google maps
#-keep class com.google.android.gms.maps.** { *; }

# Kotlinx serialization
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt # core serialization annotations

# kotlinx-serialization-json specific. Add this if you have java.lang.NoClassDefFoundError kotlinx.serialization.json.JsonObjectSerializer
#-keepclassmembers class kotlinx.serialization.json.** {
#    *** Companion;
#}
#-keepclasseswithmembers class kotlinx.serialization.json.** {
#    kotlinx.serialization.KSerializer serializer(...);
#}

-keep,includedescriptorclasses class com.appcraft.testapp.**$$serializer { *; }
#-keepclassmembers class com.appCraft.testApp.** {
#    *** Companion;
#}
#-keepclasseswithmembers class com.appCraft.testApp.** {
#    kotlinx.serialization.KSerializer serializer(...);
#}
