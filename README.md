# Android Open Source Keyboard

Android Open Source Keyboard aims to be a simple to implement keyboard for
the Android devices.

By default it is minimalistic with only 2 extensions,
- A simple view manager using compose for rendering
- A simple layout manager using compose for the view manager

It is possible to remove the layout and view manager [ see configuration ].



*Note*, while this keyboard (19.10.2025) "has" a keyboard layout, it is far
from a complete version and more will be added to the keyboard layout in the
future.



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
