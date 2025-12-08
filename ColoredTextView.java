import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.TextView;

public class ColoredTextView extends TextView {

    public ColoredTextView(Context context) {
        super(context);
        init();
    }

    public ColoredTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ColoredTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setText("ColoredTextView");
        setTextColor(0xFFFFFFFF);
    }

    public void changeText(String value) {
        setText(value);
    }

    public void setColors(String[] palabrasColoreadas, int[] coloresHex) {

        String texto = getText().toString();
        SpannableStringBuilder builder = new SpannableStringBuilder(texto);

        for (int i = 0; i < palabrasColoreadas.length; i++) {
            String palabra = palabrasColoreadas[i];
            int color = coloresHex[i];

            int index = texto.indexOf(palabra);

            while (index >= 0) {
                int end = index + palabra.length();

                builder.setSpan(
                        new ForegroundColorSpan(color),
                        index,
                        end,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                );

                index = texto.indexOf(palabra, end);
            }
        }

        setText(builder);
    }

    public void setColor(int c) {
        setTextColor(c);
    }
}
