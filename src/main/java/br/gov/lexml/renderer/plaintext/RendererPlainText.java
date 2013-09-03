package br.gov.lexml.renderer.plaintext;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.text.Document;
import javax.swing.text.rtf.RTFEditorKit;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;

import br.gov.lexml.renderer.rtf.RendererRTF;

public class RendererPlainText {

	/**
	 * Renderiza em plain text um Stream de um XML do LexML. Retorna uma String. 
	 * @param xmlLexmlInputStream
	 * @param out
	 * @throws Exception
	 */
    public String render(final InputStream xmlLexmlInputStream) throws Exception {
    	
    	ByteArrayOutputStream bosRTF = new ByteArrayOutputStream();
        
    	// converte para RTF
    	RendererRTF renderedRTF = new RendererRTF();
    	renderedRTF.render(xmlLexmlInputStream, bosRTF);
    	
    	// consome o outputstream e gera outro inputstream
    	InputStream isRTF = new ByteArrayInputStream(bosRTF.toByteArray());
    	
    	// extrai o texto do RTF 
    	RTFEditorKit rtfParser = new RTFEditorKit();
    	Document document = rtfParser.createDefaultDocument();
    	rtfParser.read(isRTF, document, 0);
    	return document.getText(0, document.getLength());
    }

	/**
	 * Renderiza em plain text um Stream de um XML do LexML. O resultado é preenchido no OutputStream out. 
	 * @param xmlLexmlInputStream
	 * @param out
	 * @throws Exception
	 */
	public void render(final InputStream xmlLexmlInputStream, final OutputStream out) throws Exception {
    	// popula o output stream
    	out.write(render(xmlLexmlInputStream).getBytes());
    }

	/**
	 * Renderiza em plain text o conteúdo em string de um XML do LexML. Retorna uma String. 
	 * @param xmlLexML
	 * @return
	 * @throws Exception
	 */
    public String render(final String xmlLexML) throws Exception {
    	return render(IOUtils.toInputStream(xmlLexML));
    }

    /**
     * Renderiza em plain text um arquivo XML do LexML. O resultado é preenchido no OutputStream out.
     * @param fileName
     * @param out
     * @throws Exception
     */
    public void render(final String fileName, final OutputStream out)
        throws Exception {
        File f = new File(fileName);

        if (!f.isFile() || !f.canRead()) {
            throw new Exception("Arquivo " + fileName + " não existe ou não pode ser lido.");
        }

        render(new FileInputStream(f), out);
    }
    

    
    
    private static void printHelp() {
        System.out.println("Utilize: java " + RendererPlainText.class.getName() + " <arquivo_lexml>");
    }

    public static void main(final String[] args) throws FileNotFoundException {

        if (args.length == 0) {
        	RendererPlainText.printHelp();
            System.exit(1);
        }
        OutputStream os;
        if (args.length > 1) {
        	os = new BufferedOutputStream(new FileOutputStream(new File(args[1])));
        } else {
        	os = System.out;
        }

        try {
            new RendererPlainText().render(args[0], os);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /*
	public static void teste(String args[]) throws Exception{
		
		RendererPlainText rpt = new RendererPlainText();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		rpt.render("src/test/resources/lexml/pls_207_2009.xml", out);
			
		System.out.println();
		System.out.println("Saida:");
		System.out.println();
		System.out.println(out.toString());
	}
	*/

	
}
