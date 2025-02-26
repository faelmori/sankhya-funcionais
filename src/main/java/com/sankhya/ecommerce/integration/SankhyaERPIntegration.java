package com.sankhya.ecommerce.integration;

import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import br.com.sankhya.service.SWServiceInvoker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This example depends on the following APIs in the classpath:
 * 
 *    1 - SWService (Sankhya API for calling Webservices)
 *    2 - Apache Commons Codec (Indirect dependency of SWService)
 *     
 * 
 */
public class SankhyaERPIntegration {
    private static final Logger logger = LogManager.getLogger(SankhyaERPIntegration.class);

    public static void main(String[] args) {
        listarParceiros("http://192.168.1.218:8180", "SUP", "", "JOSE%");
    }

    /**
     * Lists partners from the ERP system.
     *
     * @param erpUrl      the URL of the ERP system
     * @param erpUser     the username for the ERP system
     * @param erpPassword the password for the ERP system
     * @param partnerName the name of the partner to search for
     */
    private static void listarParceiros(String erpUrl, String erpUser, String erpPassword, String partnerName) {
        try {
            SWServiceInvoker si = new SWServiceInvoker(erpUrl, erpUser, erpPassword);

            StringBuffer serviceBody = loadServiceBody(SankhyaERPIntegration.class, "buscaParceirosPorNome-body.xml");
            
            replaceParameters(serviceBody, partnerName);
            
            Document docRet = si.call("CRUDServiceProvider.loadRecords", "mge", serviceBody.toString() );

            printResponse(docRet);
        } catch (Exception e) {
            logger.error("Error listing partners", e);
        }
    }

    /**
     * Replaces the parameters contained in the XML request body.
     *
     * @param body   the XML request body
     * @param params the parameters to replace in the XML request body
     */
    private static void replaceParameters(StringBuffer body, String... params) {
        // Replace possible parameters
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                String pName = "@P" + i;
                int fromIndex = 0;
                while ((fromIndex = body.indexOf(pName, fromIndex)) > -1) {
                    body.replace(fromIndex, fromIndex + pName.length(), params[i]);
                }
            }
        }
    }

    /**
     * Loads the XML request body and replaces possible parameters.
     *
     * @param baseClass    the base class to load the resource from
     * @param resourcePath the path to the resource
     * @return the XML request body
     * @throws Exception if an error occurs while loading the resource
     */
    private static StringBuffer loadServiceBody(Class baseClass, String resourcePath) throws Exception {
        InputStream inStream = baseClass.getResourceAsStream(resourcePath);

        if (inStream == null) {
            throw new IllegalArgumentException("File not found: " + baseClass.getName() + " -> " + resourcePath);
        }

        byte[] buf = new byte[1024];

        StringBuffer sbuf = new StringBuffer();

        // Read the XML into the StringBuffer
        while (true) {
            int readen = inStream.read(buf);

            if (readen <= 0) {
                break;
            }

            sbuf.append(new String(buf, 0, readen, "ISO-8859-1"));
        }

        return sbuf;
    }

    /**
     * Prints the response XML document.
     *
     * @param doc the XML document to print
     * @throws Exception if an error occurs while printing the document
     */
    private static void printResponse(Document doc) throws Exception {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StreamResult result = new StreamResult(new StringWriter());
        DOMSource source = new DOMSource(doc);
        transformer.transform(source, result);
        String xmlString = result.getWriter().toString();
        System.out.println("----Inicio reposta >>>");
        System.out.println(xmlString);
        System.out.println("----Fim resposta <<<");
    }

}
