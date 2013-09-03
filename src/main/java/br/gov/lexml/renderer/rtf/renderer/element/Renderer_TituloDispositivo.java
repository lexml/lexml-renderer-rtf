
package br.gov.lexml.renderer.rtf.renderer.element;

import org.dom4j.Element;

import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;

import br.gov.lexml.renderer.rtf.renderer.base.Renderer_Paragraph;

public class Renderer_TituloDispositivo extends Renderer_Paragraph {

    @Override
    protected void formatParagraph(final Element el, final Paragraph p) throws Exception {
        p.setFont(ctx.getFont(Font.BOLD));
    }

}
