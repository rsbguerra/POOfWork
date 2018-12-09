/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comprimidos;

import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;
import org.w3c.dom.*;

/**
 *
 * @author henri
 */
public class XMLSave {
    
    public static void saveToXML(String xmlFileName){
        Document doc;
        Element el;
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        
        try{
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.newDocument();
            
            Element rootElement = doc.createElement("Comprimidos");
            
            el = doc.createElement("Utente");
            el.setAttribute("nome", "João");
            el.setAttribute("Idade", "20");
            int i = Integer.parseInt(el.getAttribute("Idade"));
            System.out.println(i);
            
            el.appendChild(doc.createElement("Comprimido"));
            rootElement.appendChild(el);
            
            doc.appendChild(rootElement);
            
            try{
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
                
                tr.transform(new DOMSource(doc), 
                                 new StreamResult(new FileOutputStream(xmlFileName)));
                
            }catch(IOException e){
                System.out.println(e);
            }catch(TransformerException e){
                System.out.println(e);
            }
            
        }catch(ParserConfigurationException e){
            
        }
    }
    
}
