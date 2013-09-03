
package br.gov.lexml.renderer.rtf.renderer.element;


import org.dom4j.Element;

import br.gov.lexml.renderer.rtf.ITextUtil;
import br.gov.lexml.renderer.rtf.renderer.base.Renderer_ProtoParagraph;

import com.lowagie.text.Annotation;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;

public class Renderer_Alteracao extends Renderer_ProtoParagraph {

    @Override
    protected void formatProtoParagraph(final Element el, final Paragraph p) {
        p.setIndentationLeft(ITextUtil.cm2point(3));
        p.setFirstLineIndent(ITextUtil.cm2point(1));
        if(el.attributeValue("base") != null) {
        	try {
				ctx.addToPdf(new Annotation("Documento base",el.attributeValue("base")));
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

}
