package br.gov.lexml.renderer.rtf;

import java.io.FileOutputStream;

public class ToRTF {
	
	public static void main(String[] args) {

		try {
			String to = args[0].replace(".xml", ".rtf");
            new RendererRTF().render(args[0], new FileOutputStream(to));
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
		
	}

}
