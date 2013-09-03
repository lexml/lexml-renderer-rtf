
package br.gov.lexml.renderer.rtf.renderer.element;

import org.dom4j.Element;

import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;

import br.gov.lexml.renderer.rtf.renderer.base.Renderer_Paragraph;

public class Renderer_NomeAgrupador extends Renderer_Paragraph {

    @Override
    protected void formatParagraph(final Element el, final Paragraph p) throws Exception {
        p.setAlignment(com.lowagie.text.Element.ALIGN_CENTER);
        String parentName = el.getParent().getName();
        if (parentName.equals("Secao") || parentName.equals("Subsecao")) {
            p.setFont(ctx.getFont(Font.BOLD));
        }
    }

}
