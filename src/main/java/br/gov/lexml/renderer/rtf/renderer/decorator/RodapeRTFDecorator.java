
package br.gov.lexml.renderer.rtf.renderer.decorator;

import java.awt.Color;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

import com.lowagie.text.Document;
import com.lowagie.text.rtf.RtfWriter2;

import br.gov.lexml.renderer.rtf.ITextUtil;
import br.gov.lexml.renderer.rtf.RendererRTFContext;

/**
 * Adiciona um rodapé a todas as páginas com texto à esquerda e à direita podendo apresentar o
 * número da página.
 */
public class RodapeRTFDecorator extends AbstractRTFDecorator {

    private static final String PAGE_NUMBER_MARKUP = "<numero_pagina>";

    private static final float SPACE_AFTER_LINE = 1;

    String textoEsquerda, textoDireita;
    float fontSize, y, left, right, yLine;
    Color color;

    @Override
    public void init(final RendererRTFContext ctx, final Element root) throws Exception {
        textoEsquerda = ctx.getString("decorator.rodape.text_left");
        textoDireita = ctx.getString("decorator.rodape.text_right");

        Document doc = ctx.getRtf();

        fontSize = 12;
        y = doc.bottomMargin() + fontSize;
        yLine = y + fontSize + SPACE_AFTER_LINE;
        left = doc.leftMargin();
        right = doc.getPageSize().getWidth() - doc.rightMargin();

        doc.setMargins(doc.leftMargin(), doc.rightMargin(), doc.topMargin(),
                       doc.bottomMargin() + ITextUtil.cm2point(1.2f));

        String fontColor = ctx.getString("decorator.rodape.font_color");
        color = null;
        if (StringUtils.isEmpty(fontColor)) {
            color = new Color(0);
        }
        else {
            color = new Color(Integer.parseInt(fontColor, 16));
        }

    }

    @Override
    public void onEndPage(final RendererRTFContext ctx, final Element root) throws Exception {

        RtfWriter2 rtfWriter = ctx.getRtfWriter();

        // String pageNumber = Integer.toString(rtfWriter.getPageNumber());
        //
        // PdfContentByte cb = rtfWriter.getDirectContent();
        //
        // // Linha
        // cb.moveTo(left, yLine);
        // cb.lineTo(right, yLine);
        // cb.setLineWidth(.1f);
        // cb.stroke();
        //
        // // Texto
        // Font f = new Font(ctx.getFont(Font.NORMAL));
        // f.setColor(color);
        //
        // if (!StringUtils.isEmpty(textoEsquerda)) {
        // Phrase p = new Phrase();
        // p.setFont(f);
        // p.add(textoEsquerda.replace(PAGE_NUMBER_MARKUP, pageNumber));
        // ColumnText.showTextAligned(cb, com.lowagie.text.Element.ALIGN_LEFT, p, left, y, 0);
        // }
        //
        // if (!StringUtils.isEmpty(textoDireita)) {
        // Phrase p = new Phrase();
        // p.setFont(f);
        // p.add(textoDireita.replace(PAGE_NUMBER_MARKUP, pageNumber));
        // ColumnText.showTextAligned(cb, com.lowagie.text.Element.ALIGN_RIGHT, p, right, y, 0);
        // }

    }
}
