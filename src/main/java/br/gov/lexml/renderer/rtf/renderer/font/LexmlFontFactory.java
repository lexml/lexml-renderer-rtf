
package br.gov.lexml.renderer.rtf.renderer.font;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;

public class LexmlFontFactory {

    private final Map<Integer , BaseFont> baseFontMap = new HashMap<Integer , BaseFont>();

    private final Map<String , Font> fontCache = new HashMap<String , Font>();

    public LexmlFontFactory(final String normal, final String bold, final String italic, final String boldItalic)
                                                                                                                 throws Exception {

        addBaseFont(Font.NORMAL, normal);
        addBaseFont(Font.BOLD, bold);
        addBaseFont(Font.ITALIC, italic);
        addBaseFont(Font.BOLDITALIC, boldItalic);
    }

    private void addBaseFont(final int style, final String resourceName) throws Exception {
        InputStream is = getClass().getResourceAsStream(resourceName);
        byte[] fdata = IOUtils.toByteArray(is);
        IOUtils.closeQuietly(is);
        BaseFont bf = BaseFont.createFont(resourceName, BaseFont.CP1252, BaseFont.EMBEDDED, true, fdata, null);
        baseFontMap.put(style, bf);
    }

    public Font getFont(final int style, final float size) {

        String key = style + "," + size;
        Font f = fontCache.get(key);

        if (f != null) {
            return f;
        }

        BaseFont bf = baseFontMap.get(style);
        f = new Font(bf, size);

        fontCache.put(key, f);

        return f;
    }

}
