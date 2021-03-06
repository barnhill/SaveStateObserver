# SaveStateObserver  [ ![Download](https://api.bintray.com/packages/bradfordbarnhill/maven/savestateobserver/images/download.svg)](https://bintray.com/bradfordbarnhill/maven/savestateobserver/_latestVersion) ![Build Status](https://github.com/barnhill/SaveStateObserver/workflows/Android%20CI/badge.svg) [![API](https://img.shields.io/badge/API-17%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=17)
Android tool to output the contents sizes of saved state bundles in onSaveInstance state calls.  This will allow you to analyze your bundles as you pass data to other classes to prevent them from growing to sizes that are not allowed by Android.

## Usage
To use in debug builds only use:
```Gradle
debugImplementation 'com.pnuema.android:savestateobserver:2.3.5'
```
Once it has been included as a dependency it will listen for lifecycle state changes and output the following information without any code being inserted in your codebase.

   Example Logcat:

       com.virtualdyno.android D/b: ===== SaveStateObserver =====
        MainActivity saved Bundle@213688783 which contained 10 elements for 180.50 KB
             Bundle@192534497 com.google.firebase.analytics.screen_service contained 3 elements for 124 bytes
                 referrer_name = 36 bytes
                 id = 12 bytes
                 name = 4 bytes
             OuterCharacterArray = 67.05 KB
             Bundle@195930374 android:viewHierarchyState contained 2 elements for 2.36 KB
                 android:views = 2.26 KB
                 android:focusedViewId = 8 bytes
             android:support:fragments = 1.12 KB
             Integer = 8 bytes
             Short = 8 bytes
             Bundle@51706823 InnerBundle contained 2 elements for 64.20 KB
                 InnerCharacterArray = 20.05 KB
                 InnerCharacterArray2 = 44.05 KB
             android:lastAutofillId = 8 bytes
             android:fragments = 352 bytes
             LongArray = 44.81 KB
