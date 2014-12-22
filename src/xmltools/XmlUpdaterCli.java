package xmltools;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import cmdGA2.CommandLine;
import cmdGA2.NoArgumentOption;
import cmdGA2.SingleArgumentOption;
import cmdGA2.returnvalues.InfileValue;
import cmdGA2.returnvalues.StringValue;

public class XmlUpdaterCli {

	public static void main(String[] args) {
		
		////////////////////////////////////////////////////////////////////////
		// Create Command line object
		CommandLine cmd = new CommandLine();
		////////////////////////////////////////////////////////////////////////

		////////////////////////////////////////////////////////////////////////
		// Add arguments
		SingleArgumentOption<File> inOpt = new SingleArgumentOption<File>(cmd, 
				"-file", new InfileValue(), null);
		NoArgumentOption abortOpt = new NoArgumentOption(cmd, "set_aborted");
		SingleArgumentOption<String> statusKeyOpt = new SingleArgumentOption<String>(cmd, "-status_key", new StringValue(), null);
		SingleArgumentOption<String> statusValueOpt = new SingleArgumentOption<String>(cmd, "-status_value", new StringValue(), null);
		////////////////////////////////////////////////////////////////////////
		
		cmd.readAndExitOnError(args);
		
		try {
		
     		Document doc = getDocument(inOpt.getValue());
			
     		if (abortOpt.isPresent()) {
     			doc.getElementsByTagName("aborted").item(0)
     			  .setTextContent("yes");
     		} else {
     			String key = statusKeyOpt.getValue();
     			String value = statusValueOpt.getValue();
     			NodeList nl = doc.getElementsByTagName("status");
     			int keyIsPresent = checkIfPresentKey(key, nl);
     			
     			if (keyIsPresent>0) {
   					nl.item(0).getChildNodes().item(keyIsPresent)
   					  .setTextContent(value);
   				} else {
 					Element e = doc.createElement(key);
   					e.setTextContent(value);
   					nl.item(0).appendChild(e);
     			}
     		}
			
		    TransformerFactory tFactory =  TransformerFactory.newInstance();
		    Transformer transformer = tFactory.newTransformer();

		    DOMSource source = new DOMSource(doc);
		    StreamResult result = new StreamResult( inOpt.getValue() );
		    transformer.transform(source, result);
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

	}

	private static int checkIfPresentKey(String key, NodeList nl) {
		for (int i = 0; i < nl.item(0).getChildNodes().getLength(); i++) {
				String nodeName = nl.item(0).getChildNodes().item(i).getNodeName();
				if (nodeName.equals(key)){
					return i;
				}; 
		}
		return 0;
	}

	private static Document getDocument(File in) 
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(in);
		return doc;
	}

}
