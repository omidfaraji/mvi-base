[![](https://jitpack.io/v/omidfaraji/mvi-base.svg)](https://jitpack.io/#omidfaraji/mvi-base)

# MVI-Base

##### A small framework written in Kotlin for implementing MVI Architecture.

&nbsp;

---

&nbsp;

## Languages, libraries and tools used in the framework
* [Kotlin](https://kotlinlang.org/)
* [Kotlin Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html)

Also this repository include a boilerplate of using this framework.


You can integrate MVI-Base framework with in your project with just two following steps:

**Step 1.** Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
    	...
    	maven { url 'https://jitpack.io' }
    }
}
```

**Step 2.** Add the dependency
```
dependencies {
    implementation 'com.github.omidfaraji:mvi-base:Tag'
}
```
&nbsp;

---

&nbsp;

# MVI-Base Boilerplate 
### An example project that using MVI-Base Framework and very good technologies. 

## Languages, libraries and tools used in the MVI-Base boilerplate
* [Kotlin](https://kotlinlang.org/)
* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html)
* [Navigation Component](https://developer.android.com/guide/navigation)
* [Hilt](https://dagger.dev/hilt/)
* [Retrofit](http://square.github.io/retrofit/)
* [OkHttp](http://square.github.io/okhttp/)
* [Gson](https://github.com/google/gson)
* [Timber](https://github.com/JakeWharton/timber)
