
package br.gov.lexml.renderer.rtf.renderer;

import org.dom4j.Element;

import br.gov.lexml.renderer.rtf.RendererRTFContext;

public interface Renderer {

    public static final boolean ACABOU = true;

    public static final boolean NAO_ACABOU = false;

    void setContext(RendererRTFContext ctx);

    /**
     * Renderiza o elemento.
     * 
     * @return ACABOU: não processa filhos nem chama close(). NAO_ACABOU: processa filhos e chama close()
     */
    boolean render(Element el) throws Exception;

    /**
     * Finaliza processo de renderização. Chamado apenas se render(Element) retornou false.
     */
    void close() throws Exception;

    boolean isMixed();

}
