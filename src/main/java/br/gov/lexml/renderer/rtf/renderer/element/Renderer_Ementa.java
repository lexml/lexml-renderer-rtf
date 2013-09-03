
package br.gov.lexml.renderer.rtf.renderer.element;

import org.dom4j.Element;

import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;

import br.gov.lexml.renderer.rtf.ITextUtil;
import br.gov.lexml.renderer.rtf.RTFConfigs;
import br.gov.lexml.renderer.rtf.renderer.base.Renderer_Paragraph;

public class Renderer_Ementa extends Renderer_Paragraph {

    @Override
    protected void formatParagraph(final Element el, final Paragraph p) throws Exception {
        p.setIndentationLeft(ITextUtil.cm2point(8.5f));
        p.setFont(ctx.getFont(Font.NORMAL, ctx.getInt(RTFConfigs.FONT_SIZE_EMENTA)));
        p.setSpacingBefore(ITextUtil.cm2point(1));
        p.setSpacingAfter(ITextUtil.cm2point(2));
    }

}
