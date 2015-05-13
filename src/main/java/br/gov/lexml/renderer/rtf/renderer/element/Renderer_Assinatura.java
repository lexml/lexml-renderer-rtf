
package br.gov.lexml.renderer.rtf.renderer.element;

import org.dom4j.Element;

import com.lowagie.text.Paragraph;

import br.gov.lexml.renderer.rtf.ITextUtil;
import br.gov.lexml.renderer.rtf.renderer.base.Renderer_ProtoParagraph;

public class Renderer_Assinatura extends Renderer_ProtoParagraph {

    @Override
    protected void formatProtoParagraph(final Element el, final Paragraph p) {
        p.setAlignment(com.lowagie.text.Element.ALIGN_LEFT);
        p.setSpacingAfter(0);
        p.setIndentationLeft(ITextUtil.cm2point(9));
    }

}
