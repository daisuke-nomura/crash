# crash: Cause a crash at @UiThread/@WorkerThread annotated method

[![Release](https://jitpack.io/v/daisuke-nomura/crash.svg)](https://jitpack.io/#daisuke-nomura/crash)

crash cause a crash explicitly at @UiThread/@MainThread or @WorkerThread annotated method if an unmatched thread is working.  
This library is inspired by Jake Wharton's [Hugo][hugo].

@UiThread/@MainThread/@WorkerThread warnings of Android Studio are not useful on callbacks. So I tried to forcibly crash at run time.

NOTE: To prevent crashes, **REMOVE THIS LIBRARY on release build**.

##Sample usage

1. Cause a crash at @UiThread/@MainThread if thread is NOT UI/main thread. Throws ExecuteOnWorkerThreadException.


    new AsyncTask<Void, Void, Void>() {
        @UiThread
        @Override
        protected Void doInBackground(Void... params) {
            //crash here
            return null;
        }
    }.execute();

2. Cause a crash at @WorkerThread if thread is main thread. Throws ExecuteOnMainThreadException.


    //create handler on UI/main thread
    handler.postDelayed(new Runnable() {
        @WorkerThread
        @Override
        public void run() {
            //crash here
        }
    }, 0);

##Binaries

Add [aspectjx][aspectjx] plugin. aspectjx is great and very useful for AspectJ support.

    buildscript {
        repositories {
            jcenter()
            maven { url 'https://jitpack.io' }
        }
        dependencies {
            classpath 'com.android.tools.build:gradle:2.2.3'
            classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:1.0.9'
        }
    }

    allprojects {
        repositories {
            maven { url "https://jitpack.io" }
        }
    }

apply plugin

    apply plugin: 'android-aspectjx'
    
and

    dependencies {
        debugCompile 'com.github.daisuke-nomura:crash:1.0.0'
    }

##Bugs and Feedback

Please use [GitHub Issues][issues].  

##License

   Copyright 2017 Daisuke Nomura

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
  
       http://www.apache.org/licenses/LICENSE-2.0
  
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.



[hugo]: https://github.com/JakeWharton/hugo
[aspectjx]: https://github.com/HujiangTechnology/gradle_plugin_android_aspectjx
[issues]: https://github.com/daisuke-nomura/crash/issues