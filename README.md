# 🖌️ Ralfx Views
Ralfx Views is a custom Android view library designed to create **TextViews and CodeViews with syntax highlighting** easily and elegantly. Perfect for displaying code GitHub-style, with horizontal and vertical scrolling, customizable colors, and copy button support.

# 📦 Contents
- **PaintText** – Advanced TextView that allows coloring words or patterns using regex.
- **CodeView** – Code editor-like component that uses PaintText to highlight keywords, numbers, strings, and comments, with double scroll and copy button.
# 🛠️ How to Use
1. import
```java
import ralfx.view.PaintText;
import ralfx.view.CodeView;
```
2. Create PaintText
```java
PaintText paintText = new PaintText(ctx);

// Custom colors: text and optional background
paintText.put("Hello", 0xFFFFFFFF, 0xFF0000FF); // White text on blue background
paintText.putRegex("\\d+", 0xFF00FF00); // Green numbers

paintText.setText("Hello World 123!");
paintText.draw();
```
3. Create CodeView
```java
CodeView codeView = new CodeView(ctx);
codeView.setText(
    "public class Main {\n" +
    "    public static void main(String[] args) {\n" +
    "        System.out.println(\"Hello GitHub!\");\n" +
    "    }\n" +
    "}"
);
```
### CodeView Preview
<img width="1080" height="295" alt="1000254013" src="https://github.com/user-attachments/assets/7859dbe0-1c23-4047-ad8f-ba4eb1cc5100" />


- Vertical and horizontal scroll: allows viewing long code without problems.
- Copy button: quickly copy code lines.
- GitHub-style highlighting: keywords, numbers, strings (blue), comments, and methods.
# 🎨 Default Colors (GitHub style)
| Type                  | Hex Color   |
|-----------------------|------------|
| Keywords              | #FF7B72    |
| Numbers               | #79C0FF    |
| Strings               | #56B6C2    |
| Comments              | #9198A1    |
| Parentheses / Methods | #D2A8FF    |
# ⚙️ Requirements
- Android Studio 4.0+(PC) or codeAssist(Android)
- Kotlin or Java compatible with AndroidX
- API 21+
# 📁 Structure
```py
ralfx/view/
│
├─ PaintText.java       # TextView with syntax highlighting
├─ CodeView.java        # GitHub-style code view
└─ README.md
```
# 💡 Tips
- You can add more keywords or patterns using paintText.putRegex() to customize the highlighting.
- Adjust colors using ARGB hex codes.
- CodeView uses double scroll and auto height depending on text; the copy button stays fixed.

# 📜 License
MIT License – you can use, modify, and distribute the code freely.
