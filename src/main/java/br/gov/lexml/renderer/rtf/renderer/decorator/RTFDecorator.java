
package br.gov.lexml.renderer.rtf.renderer.decorator;

import org.dom4j.Element;

import br.gov.lexml.renderer.rtf.RendererRTFContext;

public interface RTFDecorator {

    void init(RendererRTFContext ctx, Element root) throws Exception;

    void beforeContent(RendererRTFContext ctx, Element root) throws Exception;

    void onEndPage(RendererRTFContext ctx, Element root) throws Exception;

    void afterContent(RendererRTFContext ctx, Element root) throws Exception;

}
