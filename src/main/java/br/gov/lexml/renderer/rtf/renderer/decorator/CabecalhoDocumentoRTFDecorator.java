
package br.gov.lexml.renderer.rtf.renderer.decorator;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.net.URL;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;

import br.gov.lexml.renderer.rtf.ITextUtil;
import br.gov.lexml.renderer.rtf.RendererRTFContext;

/**
 * Adiciona um cabeçalho padrão no início do documento com imagem, título e subtítulo centralizados.
 * <p>
 * <a href="CabecalhoPadraoDocumentoPDFDecorator.html"><i>Código Fonte</i></a>
 * </p>
 */
public class CabecalhoDocumentoRTFDecorator extends AbstractRTFDecorator {

    @Override
    public void beforeContent(final RendererRTFContext ctx, final Element root) throws Exception {

        String imageResourceName = ctx.getString("decorator.cabecalho_documento.image_resource");
        String title = ctx.getString("decorator.cabecalho_documento.title");
        String subtitle = ctx.getString("decorator.cabecalho_documento.subtitle");
        String fontColor = ctx.getString("decorator.cabecalho_documento.font_color");

        boolean hasImage = false;
        if (!StringUtils.isEmpty(imageResourceName)) {
            hasImage = true;
            URL resource = getClass().getResource(imageResourceName);
            if (resource == null) {
                throw new FileNotFoundException("Arquivo de recurso " + imageResourceName + " não encontrado.");
            }
            Image img = Image.getInstance(resource);
            img.setAlignment(com.lowagie.text.Element.ALIGN_CENTER);
            img.scalePercent(30);
            ctx.addToPdf(img);
        }

        Color color = null;
        if (StringUtils.isEmpty(fontColor)) {
            color = new Color(0);
        }
        else {
            color = new Color(Integer.parseInt(fontColor, 16));
        }

        if (!StringUtils.isEmpty(title)) {
            Paragraph p = ctx.createParagraph();
            Font f = new Font(ctx.getFont(Font.BOLD, 18));
            f.setColor(color);
            p.setFont(f);
            p.setAlignment(com.lowagie.text.Element.ALIGN_CENTER);
            p.add(title);
            if (hasImage) {
                p.setSpacingBefore(ITextUtil.cm2point(.3f));
            }
            p.setSpacingAfter(0);
            ctx.addToPdf(p);
        }

        if (!StringUtils.isEmpty(subtitle)) {
            Paragraph p = ctx.createParagraph();
            Font f = new Font(ctx.getFont(Font.BOLD, 14));
            f.setColor(color);
            p.setFont(f);
            p.setAlignment(com.lowagie.text.Element.ALIGN_CENTER);
            p.add(subtitle);
            p.setSpacingBefore(ITextUtil.cm2point(.1f));
            p.setSpacingAfter(ITextUtil.cm2point(1));
            ctx.addToPdf(p);
        }
        else {
            Paragraph p = ctx.createParagraph();
            p.setSpacingAfter(ITextUtil.cm2point(1));
            ctx.addToPdf(p);
        }

    }
}
