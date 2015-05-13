
package br.gov.lexml.renderer.rtf.renderer.element;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.Chunk;

import br.gov.lexml.renderer.rtf.renderer.base.AbstractRenderer;

public class Renderer_sub extends AbstractRenderer {

    private static final Logger log = LoggerFactory.getLogger(Renderer_sub.class);

    @Override
    public boolean render(final Element el) throws Exception {
        Chunk c = new Chunk(el.getText());

        // Não estamos tratando o sub como inline (warning se tiver subelementos)
        if (!el.elements().isEmpty()) {
            log.warn("Elementos dentro de tag <sub> não foram renderizados.");
        }

        // Desloca para baixo
        c.setTextRise(-3);

        addToContainer(c);
        return ACABOU;
    }

}
