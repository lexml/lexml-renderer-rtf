
package br.gov.lexml.renderer.rtf.renderer.element;

import org.dom4j.Element;

import br.gov.lexml.renderer.rtf.RTFConfigs;
import br.gov.lexml.renderer.rtf.renderer.StringTransformer;
import br.gov.lexml.renderer.rtf.renderer.base.Renderer_Paragraph;

import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;

public class Renderer_Epigrafe extends Renderer_Paragraph implements StringTransformer {

    @Override
    protected void formatParagraph(final Element el, final Paragraph p) throws Exception {
        p.setAlignment(com.lowagie.text.Element.ALIGN_CENTER);
        p.setFont(ctx.getFont(Font.BOLD, ctx.getInt(RTFConfigs.FONT_SIZE_EPIGRAFE)));
        ctx.pushStringTransformer(this);
    }
    
    @Override
    public void close() throws Exception {
    	super.close();
    	ctx.popStringTransformer();
    }

	@Override
	public String transform(String str) {
		str = str.replace("LEXML_EPIGRAFE_NUMERO", "_______");
		str = str.replace("LEXML_EPIGRAFE_DATA", "_______");
		return str;
	}

}
