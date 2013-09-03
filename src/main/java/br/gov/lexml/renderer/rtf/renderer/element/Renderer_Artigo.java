
package br.gov.lexml.renderer.rtf.renderer.element;

import org.dom4j.Element;

import com.lowagie.text.Paragraph;

import br.gov.lexml.renderer.rtf.ITextUtil;
import br.gov.lexml.renderer.rtf.renderer.base.Renderer_ProtoParagraph;

public class Renderer_Artigo extends Renderer_ProtoParagraph {

    @Override
    protected void formatProtoParagraph(final Element el, final Paragraph p) {
        if (!el.getParent().getName().equals("Alteracao")) {
            p.setFirstLineIndent(ITextUtil.cm2point(2));
        }
    }

}
