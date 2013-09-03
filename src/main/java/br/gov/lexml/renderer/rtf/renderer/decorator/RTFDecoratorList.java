
package br.gov.lexml.renderer.rtf.renderer.decorator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

import br.gov.lexml.renderer.rtf.RendererRTFContext;

public class RTFDecoratorList implements RTFDecorator {

    private final List<RTFDecorator> decorators = new ArrayList<RTFDecorator>();

    public RTFDecoratorList() {
        // 
    }

    public RTFDecoratorList(final String classNames) throws Exception {

        if (StringUtils.isEmpty(classNames)) {
            return;
        }

        String[] classNameArray = classNames.split("[\\s,]+");
        for (String className : classNameArray) {
            Class< ? > clazz = Class.forName(className);
            if (!RTFDecorator.class.isAssignableFrom(clazz)) {
                throw new Exception("Classe " + className + " não implementa " + RTFDecorator.class.getName());
            }
            add((RTFDecorator) clazz.newInstance());
        }
    }

    public void add(final RTFDecorator decorator) {
        decorators.add(decorator);
    }

    @Override
    public void init(final RendererRTFContext ctx, final Element root) throws Exception {
        for (RTFDecorator decorator : decorators) {
            decorator.init(ctx, root);
        }
    }

    @Override
    public void beforeContent(final RendererRTFContext ctx, final Element root) throws Exception {
        for (RTFDecorator decorator : decorators) {
            decorator.beforeContent(ctx, root);
        }
    }

    @Override
    public void onEndPage(final RendererRTFContext ctx, final Element root) throws Exception {
        for (RTFDecorator decorator : decorators) {
            decorator.onEndPage(ctx, root);
        }
    }

    @Override
    public void afterContent(final RendererRTFContext ctx, final Element root) throws Exception {
        for (RTFDecorator decorator : decorators) {
            decorator.afterContent(ctx, root);
        }
    }

}
