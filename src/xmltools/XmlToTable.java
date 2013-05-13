package xmltools;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Stack;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import cmdGA.Parser;
import cmdGA.SingleOption;
import cmdGA.exceptions.IncorrectParameterTypeException;
import cmdGA.parameterType.InputStreamParameter;
import cmdGA.parameterType.PrintStreamParameter;

public class XmlToTable {

   public static void main(String[] arg) {
	 
	SAXParserFactory factory = SAXParserFactory.newInstance();

	SAXParser saxParser;
	
	Parser parser = new Parser();
	
	SingleOption inOpt = new SingleOption(parser, System.in, "-in", InputStreamParameter.getParameter());
	SingleOption outOpt = new SingleOption(parser, System.out, "-out", PrintStreamParameter.getParameter());		

	try {
		parser.parseEx(arg);
	} catch (IncorrectParameterTypeException e) {
		System.err.println("Hubo un error:");
		System.err.println(e.getMessage());
		System.err.println("Ejecución finalizada");
		System.exit(1);
	}
	

	try {
		
		InputStream in = (InputStream) inOpt.getValue();
		
		PrintStream out = (PrintStream) outOpt.getValue(); 
		
		factory.setValidating(false);
		
		factory.setFeature("http://xml.org/sax/features/namespaces", false);
		factory.setFeature("http://xml.org/sax/features/validation", false);
		factory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
		factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
		
		saxParser = factory.newSAXParser();
		
		SimpleHandler handler = new SimpleHandler(out);
		
	    saxParser.parse(in, handler);
		
	} catch (ParserConfigurationException e) {
		e.printStackTrace();
	} catch (SAXException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	 

   }
    
  
    	 
}

class SimpleHandler extends DefaultHandler {
  	 
	boolean newElement = false;
	boolean addedchars = false;
	boolean lastWasChar = false;
	
	PrintStream out;
	
	Stack<String> currentElements = new Stack<String>(); 
	 
	public SimpleHandler(PrintStream out) {
		
		this.out = out;
		
	}

	public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {

		StringBuilder elem = new StringBuilder();
		
		elem.append(qName.toUpperCase().trim().replaceAll("\t", ""));
		
		
		if (!elem.equals("")) {

			int nAtt = attributes.getLength();
			
			for (int i=0; i< nAtt; i++) {

				elem.append('|' + attributes.getQName(i) + ':' + attributes.getValue(i));
				
			}
			
			currentElements.push("["+elem+"]");
			
			newElement = true;
			
			addedchars = false;
		}
	 
	}
	 
		public void endElement(String uri, String localName, String qName) throws SAXException {
	 
			if (lastWasChar) {
				out.println(this.prepareOutPutLine());
				lastWasChar = false;
			}
			
			currentElements.pop();

			if (addedchars) {
				currentElements.pop();
				addedchars = false;
			}
 
	 
		}
	 
		public void characters(char ch[], int start, int length) throws SAXException {
	 
			String chars = new String(ch, start, length).toUpperCase().toUpperCase().trim().replaceAll("\t", "");

			if (!chars.equals("")) {
				
				if (!newElement) {
					String tmp = currentElements.pop();
					currentElements.push(tmp +" "+chars);
				} else {
					currentElements.push(chars);
				}
				
				addedchars = true;
				
				newElement=false;
				
				lastWasChar = true;

			}
		}

		private String prepareOutPutLine() {

			StringBuilder sb = new StringBuilder();
			
			if (currentElements.size()>0) {

				sb.append(currentElements.get(0));
				
				for (int i = 1; i< currentElements.size(); i++) {
					
					sb.append("\t"+currentElements.get(i));
					
				}
				
			}
			
			return sb.toString();
			
		}
	 
};
   

