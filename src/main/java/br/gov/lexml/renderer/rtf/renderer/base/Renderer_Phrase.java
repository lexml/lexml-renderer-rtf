
package br.gov.lexml.renderer.rtf.renderer.base;

import org.dom4j.Element;

import com.lowagie.text.Phrase;

import br.gov.lexml.renderer.rtf.renderer.Renderer;

public abstract class Renderer_Phrase extends Renderer_inline {

    @Override
    public boolean render(final Element el) throws Exception {
        Phrase p = new Phrase();
        formatPhrase(el, p);
        ctx.pushContainer(p);
        return Renderer.NAO_ACABOU;
    }

    @Override
    public void close() throws Exception {
        Phrase p = (Phrase) ctx.popContainer();
        addToContainer(p);
    }

    protected abstract void formatPhrase(Element el, Phrase p) throws Exception;

}
