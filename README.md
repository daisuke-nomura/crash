# crash: Cause a crash at @UiThread/@WorkerThread annotated method

[![Release](https://jitpack.io/v/daisuke-nomura/crash.svg)](https://jitpack.io/#daisuke-nomura/crash)

crash cause a crash explicitly at @UiThread/@MainThread or @WorkerThread annotated method if an unmatched thread is working.  
This library is inspired by Jake Wharton's [Hugo][hugo].

@UiThread/@MainThread/@WorkerThread warnings of Android Studio are not useful on callbacks. So I tried to forcibly crash at run time.

NOTE: To prevent crashes, **REMOVE THIS LIBRARY on release build**.

##Sample usage

Cause a crash at @UiThread/@MainThread if thread is NOT UI/main thread. Throws ExecuteOnWorkerThreadException.

    new AsyncTask<Void, Void, Void>() {
        @UiThread
        @Override
        protected Void doInBackground(Void... params) {
            //crash here
            return null;
        }
    }.execute();

Cause a crash at @WorkerThread if thread is main thread. Throws ExecuteOnMainThreadException.

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
        }
        dependencies {
            classpath 'com.android.tools.build:gradle:2.2.3'
            classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:1.0.9'
        }
    }

    allprojects {
        repositories {
            jcenter()
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

##Reference
I referred [Hugo][hugo-runtime] and [android 10 coder article][android10], [stackoverflow][stackoverflow], [Qiita][qiita], [AspectJX-Demo][AspectJX-Demo] to build AspectJ code.

[hugo]: https://github.com/JakeWharton/hugo
[aspectjx]: https://github.com/HujiangTechnology/gradle_plugin_android_aspectjx
[issues]: https://github.com/daisuke-nomura/crash/issues
[hugo-runtime]: https://github.com/JakeWharton/hugo/blob/master/hugo-runtime/src/main/java/hugo/weaving/internal/Hugo.java
[android10]: http://fernandocejas.com/2014/08/03/aspect-oriented-programming-in-android/
[stackoverflow]: http://stackoverflow.com/questions/31142125/aspectj-with-android-library
[qiita]: http://qiita.com/pe-suke/items/56dd2b8e277d174ac10a
[AspectJX-Demo]: https://github.com/HujiangTechnology/AspectJX-Demo