# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\AndroidEnv\AS\Env\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}


-optimizationpasses 5          # 指定代码的压缩级别
-dontusemixedcaseclassnames   # 是否使用大小写混合
-dontpreverify           # 混淆时是否做预校验
-verbose                # 混淆时是否记录日志

-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*  # 混淆时所采用的算法

#-libraryjars libs/bugly_1.2.6_release.jar
#-libraryjars libs/gson-2.2.1.jar
#-libraryjars libs/httpcore-4.2.4.jar
#-libraryjars src/main/libs/httpcore-4.2.4.jar
#-libraryjars src/main/libs/gson-2.2.1.jar
#-libraryjars src/main/libs/volley.jar
-ignorewarnings #这1句是屏蔽警告，脚本中把这行注释去掉


#过滤第三方jar包
-keep class **.R$* {   *;  }
-keep class com.google.gson.**{*;}
-keep class org.apache.http.**{*;}
-keep class com.android.volley.**{*;}


-keepclassmembers class com.starv.tvindex.util.view.WebViewUtil$tvIndexJavaScriptInterface{
  public *;
}
-keepattributes *Annotation*
-keepattributes *JavascriptInterface*
-keep class android.webkit.JavascriptInterface {*;}


-keep class cn.sharesdk.**{*;}
-keep class com.sina.**{*;}
-keep class **.R$* {*;}
-keep class **.R{*;}
-dontwarn cn.sharesdk.**
-dontwarn **.R$*


#butterknife混淆
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#-dontwarn com.tencent.bugly.**
#-keep public class com.tencent.bugly.**{*;}