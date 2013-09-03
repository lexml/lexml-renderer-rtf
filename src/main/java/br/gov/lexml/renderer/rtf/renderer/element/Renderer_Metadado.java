
package br.gov.lexml.renderer.rtf.renderer.element;

import org.dom4j.Element;

import br.gov.lexml.renderer.rtf.renderer.Renderer;
import br.gov.lexml.renderer.rtf.renderer.base.AbstractRenderer;

public class Renderer_Metadado extends AbstractRenderer {

    @Override
    public boolean render(final Element el) {
        return Renderer.ACABOU;
    }

}
