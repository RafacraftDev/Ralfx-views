# 🚀 PaintText:
`PaintText` is an advanced type of TextView that allows you to color words, phrases, or patterns using literal words or regular expressions (regex). It also allows you to assign text color and background color. 

---

## 🛠️ How to use
PaintText is very easy, just place this import:
```java
import ralfx.view.PaintText;
```
Create the PaintText:
```java
PaintText paintText = new PaintText(ctx);
```
## 1️⃣ Color in literal words
```java
// Text color only
paintText.put("Hello", 0xFFFFFFFF); // White text

// Text and backgrounds 
paintText.put("world", 0xFFFFFFFF, 0xFFFF0000); // White text, red background
```
> ⚠️ Note: The colors use ARGB format. 0xFFFFFFFF = white, 0xFFFF0000 = red.

## 2️⃣ Coloring using regular expressions
```java
// Text color only
paintText.putRegex("class .*", 0xFFFFFFFF); // Colors any word that begins with "class"

// Text and background color
paintText.putRegex("\\d+", 0xFF00FF00, 0xFF000000);
```
> 💡 Regex soporta cualquier patrón válido de Java (Pattern.compile).
# 3️⃣ Draw the colors
After adding your words or regex:
```java
paintText.setText("Hello world 123 class Test");
paintText.draw(); // Apply the colors
```
# 4️⃣ Useful methods

| Method | Description |
|--------|-------------|
| `put(String word, int color)` | Colors a literal word with the given text color. |
| `put(String word, int color, int background)` | Colors a literal word with text color **and** background color. |
| `putRegex(String regex, int color)` | Colors patterns using regex with the given text color. |
| `putRegex(String regex, int color, int background)` | Colors patterns using regex with text **and** background color. |
| `getKeyAt(int index)` | Returns the literal word at the specified `index` (useful for iterating through keys). |
| `draw()` | Applies all the text and background colors to the current text in the TextView. |
## 5️⃣ Complete example

```java
PaintText paintText = new PaintText(ctx);

paintText.put("Hello", 0xFFFFFFFF, 0xFF0000FF); // White on blue
paintText.put("World", 0xFFFFFF00, 0xFFFF0000); // Yellow on red
paintText.putRegex("\\d+", 0xFF00FF00); // Green numbers

paintText.setText("Hello World 123!");

paintText.draw();
```
