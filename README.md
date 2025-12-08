# ColoredTextView
Un nuevo tipo de textView que tiene funciones de span ya listas en java

## ¿como usarlo?
en settings de gradle
```gradle
dependencies {
		implementation 'com.github.User:Repo:Tag'
	}
```
y en las dependencias
```gradle
dependencies {
    implementation 'com.github.RafacraftDev:ColoredTextView:1.0'
}
```
y en el java
```java
color.changeText("hello, world!"); // texto
color.setColor(0xFFFFFFFF); // color de texto sin span
color.setColors(new String[]{ "hello!" }, new int[] { 0xFFFF0000 }); // textos y colores
```

### creado por RafacraftCoder!
