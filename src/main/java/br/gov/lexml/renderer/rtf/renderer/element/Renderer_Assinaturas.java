
package br.gov.lexml.renderer.rtf.renderer.element;

import org.dom4j.Element;

import br.gov.lexml.renderer.rtf.renderer.base.AbstractRenderer;

public class Renderer_Assinaturas extends AbstractRenderer {

    @Override
    public boolean render(final Element el) throws Exception {
        addVerticalSpaceCM(.5f);
        return NAO_ACABOU;
    }

}
