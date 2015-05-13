
package br.gov.lexml.renderer.rtf.renderer.element;

import org.dom4j.Element;

import com.lowagie.text.Font;
import com.lowagie.text.Phrase;

import br.gov.lexml.renderer.rtf.renderer.base.Renderer_Phrase;

public class Renderer_span extends Renderer_Phrase {

    @Override
    protected void formatPhrase(final Element el, final Phrase p) throws Exception {
        // Negrito para identificar outra língua
        if (el.attribute("lang") != null) {
            int style = hasAncestor(el, "i") ? Font.BOLDITALIC : Font.BOLD;
            p.setFont(ctx.getFont(style));
        }
        if(el.attribute("style") != null) {
        	String styleStr = el.attribute("style").getValue();
        	int style = 0;
        	if(styleStr.contains("font-style:italic")) style|=Font.ITALIC;
        	if(styleStr.contains("font-weight:bold") ||
        			styleStr.contains("font-weight:bolder")) style|=Font.BOLD;
        	p.setFont(ctx.getFont(style));
        }
    }

}
