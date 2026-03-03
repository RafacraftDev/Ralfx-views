package ralfx.view;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaintText extends TextView {

    // Palabras literales y colores de texto
    private final Map<String, Integer> texts = new LinkedHashMap<>();
    // Regex y colores de texto
    private final Map<String, Integer> regexs = new LinkedHashMap<>();
    // Fondos opcionales por palabra literal
    private final Map<String, Integer> backgrounds = new LinkedHashMap<>();
    // Fondos opcionales por regex
    private final Map<String, Integer> regexBackgrounds = new LinkedHashMap<>();

    public PaintText(Context ctx) {
        super(ctx);
        setTextColor(0xFFFFFFFF);
        setText("PaintText");
    }

    // ------------------
    // Métodos para agregar
    // ------------------
    
    // Agregar palabra literal con color de texto
    public void put(String str, int color) {
        texts.put(str, color);
    }

    // Agregar palabra literal con color de texto y fondo
    public void put(String str, int textColor, int bgColor) {
        texts.put(str, textColor);
        backgrounds.put(str, bgColor);
    }

    // Agregar regex con color de texto
    public void putRegex(String rgx, int color) {
        regexs.put(rgx, color);
    }

    // Agregar regex con color de texto y fondo
    public void putRegex(String rgx, int textColor, int bgColor) {
        regexs.put(rgx, textColor);
        regexBackgrounds.put(rgx, bgColor);
    }

    // ------------------
    // Obtener clave literal por índice
    // ------------------
    public String getKeyAt(int i) {
        List<String> keys = new ArrayList<>(texts.keySet());
        return keys.get(i);
    }

    // ------------------
    // Dibujar colores
    // ------------------
    public void draw() {
        String fullText = getText().toString();
        SpannableStringBuilder ssb = new SpannableStringBuilder(fullText);

        // --- Colorear palabras literales ---
        for (String key : texts.keySet()) {
            int color = texts.get(key);
            int bgColor = backgrounds.getOrDefault(key, -1); // -1 significa sin fondo
            int index = fullText.indexOf(key);
            while (index >= 0) {
                int end = index + key.length();
                ssb.setSpan(new ForegroundColorSpan(color), index, end, SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE);
                if (bgColor != -1) {
                    ssb.setSpan(new BackgroundColorSpan(bgColor), index, end, SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                index = fullText.indexOf(key, end);
            }
        }

        // --- Colorear regex ---
        for (String rgx : regexs.keySet()) {
            int color = regexs.get(rgx);
            int bgColor = regexBackgrounds.getOrDefault(rgx, -1);
            Pattern p = Pattern.compile(rgx);
            Matcher m = p.matcher(fullText);
            while (m.find()) {
                int start = m.start();
                int end = m.end();
                ssb.setSpan(new ForegroundColorSpan(color), start, end, SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE);
                if (bgColor != -1) {
                    ssb.setSpan(new BackgroundColorSpan(bgColor), start, end, SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }

        setText(ssb);
    }
}
