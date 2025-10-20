# Android Open Source Keyboard [![](https://jitpack.io/v/Xtarii/AndroidKeyboard.svg)](https://jitpack.io/#Xtarii/AndroidKeyboard)

Android Open Source Keyboard aims to be a simple to implement keyboard for
the Android devices.

By default it is minimalistic with only 2 extensions,
- A simple view manager using compose for rendering
- A simple layout manager using compose for the view manager

It is possible to remove the layout and view manager [ see configuration ].



*Note*, while this keyboard (19.10.2025) "has" a keyboard layout, it is far
from a complete version and more will be added to the keyboard layout in the
future.



### Usage and Importation

Add the dependency with *Jitpack*,
```gradle.kts
dependencies {
    implementation("com.github.Xtarii:AndroidKeyboard:<TAG>")
}
```

To add the keyboard to any application add it to the `Manifest` file,
```xml
<service android:name="android.open.keyboard.Keyboard"
    android:exported="true"
    android:label="Simple Keyboard"
    android:permission="android.permission.BIND_INPUT_METHOD">

    <intent-filter>
        <action android:name="android.view.InputMethod" />
    </intent-filter>

    <meta-data
        android:name="android.view.im"
        android:resource="@xml/method" />
</service>
```

The setting `<service android:name="android.open.keyboard.Keyboard" ... >` is to link the keyboard
to the application. Any other settings may be changed to your liking or to what Android requiers.



### Simple setup for configuration file

Configuration for keyboard, located in `assets/keyboard/keyboard.settings.json`
```json
{
    "view":    "<path to view class>",
    "layout":  "<path to layout class>",

    "extensions": [
        ...
    ]
}
```

An example of a simple keyboard setup could be,
```json
{
    "view":    "com.example.ViewManager",
    "layout":  "com.example.Layout",

    "extensions": [
        "com.example.LocalLexicon",
        "com.example.SomeOtherExtension"
    ]
}
```



### Expanding with custom Extensions

There are 2 things a extension needs.
```java
@Extension(ID = "Foo", description = "My Extension", version = "v0.1")
public class Foo implements IExtension {
    ...
}
```

The `@Extension` requires an ID but the other values are not required.
For making a custom layout with `Compose` use the `IComposeLayout` instead of `IExtension`.
Similarly a custom view manager is created with `IViewManager` instead of `IExtension`.

It is not required to use compose for the UI, XML version works aswell as it is up for
the view manager to decide how the rendering should work.
