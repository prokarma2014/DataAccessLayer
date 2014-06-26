package com.pkrm.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.pkrm.Utils;
import com.pkrm.dao.ReaderDAO;

/**
 * 
 * @author Prokarma
 *
 */
public class XmlReaderImplementation implements ReaderDAO {

	Document doc = null;

	/**
	 * This method is used to open the file
	 * @param path
	 */
	public XmlReaderImplementation(String path) {

		try {
			Utils.logger.info(" Reading the XML file");
			File fXmlFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);

		} catch (Exception e) {
			Utils.logger.error(e.getMessage());
		}

	}
	
	private static volatile XmlReaderImplementation instance = null;

	/**
	 * This method create the instance of XmlReader
	 * @param path
	 * @return XmlReader
	 */
	public static XmlReaderImplementation getInstance(String path) {
		Utils.logger.info(" Trying to create instance for XMLfile");
		if (instance == null) {
			synchronized (XmlReaderImplementation.class) {
				if (instance == null) {
					instance = new XmlReaderImplementation(path);
				}
			}
		}
		return instance;
	}

	
	/**
	 * This method read the XML file data 
	 * @return List of map objects
	 */
	public List<Map<String, String>> read() {

		List<Map<String, String>> maplist = new ArrayList<Map<String, String>>();
		Utils.logger.info(" inside read() method");
		try {

			doc.getDocumentElement().normalize();

			// To get Route tag
			Node nNode = doc.getDocumentElement();

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				// Get the main chaild
				NodeList nodeLIst = eElement.getChildNodes();

				// iterating the main chailds
				for (int i = 0; i < nodeLIst.getLength(); i++) {

					Node node = nodeLIst.item(i);

					if (Node.ELEMENT_NODE == node.getNodeType()) {

						// To get the grand chailds
						NodeList innerNodeLIst = node.getChildNodes();
						Map<String, String> map = new HashMap<String, String>();

						for (int j = 0; j < innerNodeLIst.getLength(); j++) {

							Node grandNodes = innerNodeLIst.item(j);

							if (grandNodes.getNodeType() == Node.ELEMENT_NODE) {
								map.put(grandNodes.getNodeName(),
										grandNodes.getTextContent());
							}
						}// end of iteration of grand child store the map in
							// list
						maplist.add(map);
					}
				}

			}
		} catch (Exception e) {
			if(e.getMessage() != null && e.getMessage().isEmpty()){
				Utils.logger.error(e.getMessage());
			}
			else{
				Utils.logger.error("Some problem occured while parsing the file");
			}
			
		}
		return maplist;

	}
}