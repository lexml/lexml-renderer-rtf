
package br.gov.lexml.renderer.rtf.renderer.element;

import org.dom4j.Element;

import com.lowagie.text.Paragraph;

import br.gov.lexml.renderer.rtf.renderer.base.AbstractRenderer;

public class Renderer_NomePessoa extends AbstractRenderer {

    @Override
    public boolean render(final Element el) throws Exception {
        Paragraph p = ctx.createParagraph();
        p.add(el.getTextTrim());
        addToPDF(p);
        return ACABOU;
    }

}
