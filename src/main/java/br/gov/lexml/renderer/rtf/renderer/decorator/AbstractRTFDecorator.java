
package br.gov.lexml.renderer.rtf.renderer.decorator;

import org.dom4j.Element;

import br.gov.lexml.renderer.rtf.RendererRTFContext;

public abstract class AbstractRTFDecorator implements RTFDecorator {

    @Override
    public void init(final RendererRTFContext ctx, final Element root) throws Exception {
        // Não faz nada
    }

    @Override
    public void beforeContent(final RendererRTFContext ctx, final Element root) throws Exception {
        // Não faz nada
    }

    @Override
    public void onEndPage(final RendererRTFContext ctx, final Element root) throws Exception {
        // Não faz nada
    }

    @Override
    public void afterContent(final RendererRTFContext ctx, final Element root) throws Exception {
        // Não faz nada
    }

}
