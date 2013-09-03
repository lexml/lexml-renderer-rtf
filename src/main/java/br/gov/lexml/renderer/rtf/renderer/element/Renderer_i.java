
package br.gov.lexml.renderer.rtf.renderer.element;

import org.dom4j.Element;

import com.lowagie.text.Font;
import com.lowagie.text.Phrase;

import br.gov.lexml.renderer.rtf.renderer.base.Renderer_Phrase;

public class Renderer_i extends Renderer_Phrase {

    @Override
    protected void formatPhrase(final Element el, final Phrase p) throws Exception {
        int style = hasAncestor(el, "b") ? Font.BOLDITALIC : Font.ITALIC;
        p.setFont(ctx.getFont(style));
    }

}
