# SaveStateObserver  ![Build Status](https://github.com/barnhill/SaveStateObserver/workflows/Android%20CI/badge.svg) [![API](https://img.shields.io/badge/API-26%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=19)
Android tool to output the contents sizes of saved state bundles in onSaveInstance state calls.  This will allow you to analyze your bundles as you pass data to other classes to prevent them from growing to sizes that are not allowed by Android.

## Usage
To use in debug builds only use:
```Gradle
debugImplementation 'com.pnuema.android:savestateobserver:2.14.0'
```
Once it has been included as a dependency it will listen for lifecycle state changes and output the following information without any code being inserted in your codebase.

   Example Logcat:

       2022-07-06 08:59:54.618 27026-27026/com.pnuema.android.savestateobserver.app D/SaveStateObserver: ===== SaveStateObserver =====
    MainActivity saved Bundle@142181721 which contained 8 elements for 304.73 KB
         String = 32 bytes
         Bundle@238359838 innerBundle contained 4 elements for 100.17 KB
             String = 40 bytes
             BigString = 100.02 KB
             Integer = 8 bytes
             Float = 8 bytes
         Bundle@205345279 android:viewHierarchyState contained 1 elements for 612 bytes
             android:views = 568 bytes
         Integer = 8 bytes
         Float = 8 bytes
         Bundle@257018828 androidx.lifecycle.BundlableSavedStateRegistry.key contained 4 elements for 203.19 KB
             Bundle@229907989 androidx:appcompat contained 0 elements for 4 bytes
             Bundle@1664298 android:support:lifecycle contained 0 elements for 4 bytes
             Bundle@2035483 android:support:activity-result contained 5 elements for 1.39 KB
                 KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT = 152 bytes
                 KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS = 752 bytes
                 KEY_COMPONENT_ACTIVITY_REGISTERED_RCS = 56 bytes
                 Bundle@230021304 KEY_COMPONENT_ACTIVITY_PENDING_RESULT contained 0 elements for 4 bytes
                 KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS = 8 bytes
             Bundle@80053905 android:support:fragments contained 1 elements for 201.54 KB
                 android:support:fragments = 201.47 KB
         android:lastAutofillId = 8 bytes
         android:fragments = 336 bytes

## Notification
Consumers can register for detection events. Start by registering this on application start in debug
only code to receive notifications and perform the action intended such as logging analytic events,
or reporting a bug.

    Example:

        uuid = OversizeBundleRegistrar.register { stringifyBundle ->
            // Action to perform when oversize bundle is detected
        }

Make sure to unregister when you no longer want to trigger the work to be done:

    Example:
    
        OversizeBundleRegistrar.unregister(uuid)
