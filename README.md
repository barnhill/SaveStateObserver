# SaveStateObserver
Android tool to output the contents sizes of saved state bundles in onSaveInstance state calls

## Usage
To use in debug builds only use:

       debugImplementation 'com.pnuema.android:savestateobserver:1.0.0'

   Logcat:

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
