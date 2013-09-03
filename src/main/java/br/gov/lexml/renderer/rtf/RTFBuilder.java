
package br.gov.lexml.renderer.rtf;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;
import org.jaxen.SimpleNamespaceContext;

import br.gov.lexml.renderer.rtf.renderer.Renderer;
import br.gov.lexml.renderer.rtf.renderer.RendererFactory;
import br.gov.lexml.renderer.rtf.renderer.decorator.RTFDecoratorList;
import br.gov.lexml.renderer.rtf.renderer.font.GentiumFontFactory;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.rtf.RtfWriter2;

public class RTFBuilder {

    private final RendererRTFContext ctx;

    private final Element root;

    private RTFDecoratorList decorators;

    public RTFBuilder(final RendererRTFContext ctx, final Element root) throws Exception {
        this.ctx = ctx;
        this.root = root;
    }

    public void build() throws Exception {

        // Para resolução de namespace padrão via XPATH
        Map<String , String> map = new HashMap<String , String>();
        map.put("lexml", root.getNamespaceURI());
        ctx.setXpathNamespaceContext(new SimpleNamespaceContext(map));

        // seta LexmlFontFactory
        ctx.setFontFactory(new GentiumFontFactory());

        // Setup da página
        Rectangle pageSize = new Rectangle(ctx.getPoints(RTFConfigs.DOCUMENT_WIDTH),
                                           ctx.getPoints(RTFConfigs.DOCUMENT_HEIGHT));

        Document doc = new Document(pageSize, ctx.getPoints(RTFConfigs.DOCUMENT_MARGIN_LEFT),
                                    ctx.getPoints(RTFConfigs.DOCUMENT_MARGIN_RIGHT),
                                    ctx.getPoints(RTFConfigs.DOCUMENT_MARGIN_TOP),
                                    ctx.getPoints(RTFConfigs.DOCUMENT_MARGIN_BOTTOM));

        ctx.setRtf(doc);

        OutputStream out = ctx.getOutputStream();

        RtfWriter2 rtfWriter = RtfWriter2.getInstance(doc, ctx.getOutputStream());
        // rtfWriter.setPageEvent(new PdfPageListener());

        ctx.setRtfWriter(rtfWriter);

        // Inicializa decoradores
        decorators = new RTFDecoratorList(ctx.getString(RTFConfigs.DECORATOR_CLASSES));
        decorators.init(ctx, root);

        // createMetadata(doc, root, rtfWriter);

        doc.open();

        // Fonte / Parágrafo padrão
        Paragraph p = ctx.createParagraph();
        Font f = ctx.getFont(Font.NORMAL);
        p.setFont(f);
        p.setAlignment(com.lowagie.text.Element.ALIGN_JUSTIFIED);
        p.setSpacingAfter(ctx.getPoints(RTFConfigs.PARAGRAPH_SPACING));
        ctx.pushProtoParagraph(p);

        decorators.beforeContent(ctx, root);

        render(root);

        ctx.popProtoParagraph();

        decorators.afterContent(ctx, root);

        ctx.flushPdf();

        doc.close();

        out.close();

    }

    private void render(final Element el) throws Exception {

        Renderer renderer = RendererFactory.createRenderer(el, ctx);

        boolean abreAspas = el.attributeValue("abreAspas") != null;
        boolean fechaAspas = el.attributeValue("fechaAspas") != null;
        String notaAlteracao = el.attributeValue("notaAlteracao");

        if (abreAspas) {
            ctx.addToNextContainer(new Chunk("“"));
        }

        if (renderer == null) {
            renderSubElements(el);
        }
        else if (renderer.render(el) == Renderer.NAO_ACABOU) {
            if (renderer.isMixed()) {
                renderAllChildNodes(el);
            }
            else {
                renderSubElements(el);
            }
            renderer.close();
        }

        StringBuilder lastTail = new StringBuilder(12);
        if (fechaAspas) {
            lastTail.append("” ");
        }
        if (!StringUtils.isEmpty(notaAlteracao)) {
            lastTail.append('(');
            lastTail.append(notaAlteracao);
            lastTail.append(')');
        }
        if (lastTail.length() > 0) {
            ctx.addToLastAddedContainer(new Chunk(lastTail.toString()));
        }

    }

    private void renderSubElements(final Element el) throws Exception {
        for (Object elFilho : el.elements()) {
            render((Element) elFilho);
        }
    }

    private void renderAllChildNodes(final Element el) throws Exception {

        if (el.isTextOnly()) {
            renderTextToContainer(el.getText());
        }
        else {
            for (Iterator< ? > it = el.nodeIterator(); it.hasNext();) {
                Node nFilho = (Node) it.next();

                if (nFilho instanceof Text) {
                    renderTextToContainer(nFilho.getText());
                }
                else if (nFilho instanceof Element) {
                    render((Element) nFilho);
                }
            }
        }

    }

    private void renderTextToContainer(final String text) {
    	String str = ctx.stringTransform(ITextUtil.normalizeSpaces(text));
        ctx.addToContainer(new Chunk(str));
    }

    private void createMetadata(final Document doc, final Element root, final PdfWriter writer) throws Exception {

        // Title
        String metadata = ctx.getString(RTFConfigs.METADATA_TITLE);
        if (StringUtils.isEmpty(metadata)) {
            metadata = ctx.createXPath("//lexml:Epigrafe/lexml:p").stringValueOf(root);
            if (StringUtils.isEmpty(metadata)) {
                metadata = "";
            }
        }
        doc.addTitle(metadata);

        // Subject
        metadata = ctx.getString(RTFConfigs.METADATA_SUBJECT);
        if (!StringUtils.isEmpty(metadata)) {
            doc.addSubject(metadata);
        }

        // Keywords
        metadata = ctx.getString(RTFConfigs.METADATA_KEYWORDS);
        if (!StringUtils.isEmpty(metadata)) {
            doc.addKeywords(metadata);
        }

        // Creator
        metadata = ctx.getString(RTFConfigs.METADATA_CREATOR);
        if (!StringUtils.isEmpty(metadata)) {
            doc.addCreator(metadata);
        }

        // Author
        metadata = ctx.getString(RTFConfigs.METADATA_AUTHOR);
        if (!StringUtils.isEmpty(metadata)) {
            doc.addAuthor(metadata);
        }

        writer.createXmpMetadata();
    }

    public class PdfPageListener extends PdfPageEventHelper {

        @Override
        public void onEndPage(final PdfWriter writer, final Document document) {
            try {
                decorators.onEndPage(ctx, root);
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

}
