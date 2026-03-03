package ralfx.view;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.pt.R;

public class CodeView extends LinearLayout {
    private PaintText paintText;
	Context ct;

    // Keywords de Java
    private final String[] keywords = {
            // Modifiers
            "public", "private", "protected", "static", "final", "abstract", "synchronized", "volatile", "transient",
            "native", "strictfp",

            // Types
            "void", "int", "char", "float", "double", "boolean", "long", "short", "byte",

            // Control flow
            "if", "else", "switch", "case", "default", "for", "while", "do", "break", "continue", "return", "throw",
            "try", "catch", "finally", "assert",

            // Literals
            "true", "false", "null",

            // Class and interface
            "class", "interface", "enum", "extends", "implements", "this", "super", "new", "instanceof", "package",
            "import"
    };

    // Colores
    private static final int KEYWORD_COLOR = 0xFFFF7B72; // Salmon
    private static final int NUMBER_COLOR = 0xFF79C0FF; // Azul para números
    private static final int STRING_COLOR = 0xFF79C0FF; // Azul al estilo GitHub
    private static final int COMMENT_COLOR = 0xFF9198A1; // Gris para comentarios
    private static final int METHOD_COLOR = 0xFFD2A8FF; // Lila para métodos

    public CodeView(Context ctx) {
        super(ctx);
		ct = ctx;
        setOrientation(HORIZONTAL);
        setBackgroundColor(0xFF151B23); // Fondo oscuro
        setPadding(8, 8, 8, 8);
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        paintText = new PaintText(ctx);

        // Resaltar keywords
        for (String keyword : keywords) {
            paintText.putRegex("\\b" + keyword + "(?=\\s|\\(|\\.|$)", KEYWORD_COLOR);
        }

        // Resaltar métodos después de un punto
        paintText.putRegex("(?<=\\.)\\b\\w+\\b(?=\\s*\\()", METHOD_COLOR);

        // Números (enteros y decimales)
        paintText.putRegex("[0-9]+(\\.[0-9]+)?", NUMBER_COLOR);
        paintText.putRegex("0x[0-9A-Fa-f]+", NUMBER_COLOR);

        // Strings
        paintText.putRegex("\".*?\"", STRING_COLOR);

        // Comentarios
        paintText.putRegex("//.*", COMMENT_COLOR);

        // Scroll vertical con altura máxima
        ScrollView scrollVertical = new ScrollView(ctx) {
            int maxHeightDp = 200; // Altura máxima en dp
            float scale = ctx.getResources().getDisplayMetrics().density;
            int maxHeightPx = (int) (maxHeightDp * scale + 0.5f);

            @Override
            protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                int newHeightSpec = MeasureSpec.makeMeasureSpec(maxHeightPx, MeasureSpec.AT_MOST);
                super.onMeasure(widthMeasureSpec, newHeightSpec);
            }
        };
        scrollVertical.setFillViewport(true);

        // Scroll horizontal
        HorizontalScrollView scrollHorizontal = new HorizontalScrollView(ctx);
        scrollHorizontal.setFillViewport(true);

        // PaintText dentro de scrolls
        scrollHorizontal.addView(paintText);
        scrollVertical.addView(scrollHorizontal);

        // Layout para el botón de copy
        LinearLayout ln = new LinearLayout(ctx);
        ln.setOrientation(HORIZONTAL);
        ln.setGravity(Gravity.TOP | Gravity.END);
        ln.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        int dp = 35;
        float scale = ctx.getResources().getDisplayMetrics().density;
        int px = (int) (dp * scale + 0.5f);

        ImageView img = new ImageView(ctx);
        img.setLayoutParams(new LayoutParams(px, px));
        img.setImageResource(R.drawable.ic_copy);

        img.setOnClickListener(v -> {
            ClipboardManager cm = (ClipboardManager) ctx.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("CodeView Text", paintText.getText().toString());
            cm.setPrimaryClip(clip);
        });

        ln.addView(img);

        // Contenedor horizontal principal: scroll vertical + botón copy
        LinearLayout container = new LinearLayout(ctx);
        container.setOrientation(HORIZONTAL);
        container.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        container.addView(scrollVertical, new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f));
        container.addView(ln);

        addView(container);
    }

    /**
     * Asigna el texto al CodeView y aplica el resaltado.
     *
     * @param text Texto que se va a mostrar en el CodeView
     */
    public void setText(String text) {
        paintText.setText(text);
        paintText.draw();
    }
	
	public void setSize(int size) {
		paintText.setTextSize(dpToPx(size).floatValue());
	}
	
	public Number dpToPx(int t) {
		int dp = t;
        float scale = ct.getResources().getDisplayMetrics().density;
        Number px = (int) (dp * scale + 0.5f);
		return px;
	}
}
