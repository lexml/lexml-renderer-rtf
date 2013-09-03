
package br.gov.lexml.renderer.rtf.renderer.element;

import org.dom4j.Element;

import com.lowagie.text.Font;
import com.lowagie.text.Phrase;

import br.gov.lexml.renderer.rtf.renderer.base.Renderer_Phrase;

public class Renderer_b extends Renderer_Phrase {

    @Override
    protected void formatPhrase(final Element el, final Phrase p) throws Exception {
        int style = hasAncestor(el, "i") ? Font.BOLDITALIC : Font.BOLD;
        p.setFont(ctx.getFont(style));
    }

}
