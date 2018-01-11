/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential  OCO Source Materials                            */
/*                                                                   */
/* Copyright IBM Corp. 2017                                          */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */
package com.poc.api.dataprovider;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.tools.ant.DirectoryScanner;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.poc.utils.FileLoader;

public class XMLDataProvider {

    private List<Map<String, Map<String, String>>> dataList = new ArrayList<Map<String, Map<String, String>>>();

    /**
     * Method description - Retrieve the test data from xml format data file for each testcase class and combine with
     * the global test data
     * 
     * @param method
     *            - A Method provides information about, and access to, a single method on a class or interface.
     * @throws Exception
     */

    public Object[][] retrieveTestData(Method method) throws Exception {
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();

        for (int i = 0; i < dataList.size(); i++) {
            Map<String, Map<String, String>> m = dataList.get(i);
            if (m.containsKey(method.getName())) {
                Map<String, String> dm = (Map<String, String>) m.get(method.getName());
                result.add(dm);
            }
        }
        Object[][] testdatas = new Object[result.size()][];
        for (int i = 0; i < result.size(); i++) {
            testdatas[i] = new Object[] { result.get(i) };
        }
        if (testdatas.length == 0) {
            throw new Exception(method.getName() + " tag is not found in respective Test Data xml");
        }

        return testdatas;
    }

    /**
     * Method description - Retrieve the test data from xml format data file for each testcase class and combine with
     * the global test data
     * 
     * @param fileName
     *            - the file name for xml data file
     * @throws Exception
     */

    public void loadXMLTestDataFile(String fileName) throws Exception {
        DirectoryScanner scanner = new DirectoryScanner();
        scanner.setIncludes(new String[]{"**\\dataprovider\\**\\"+fileName+".xml"});
        scanner.setBasedir(System.getProperty("user.dir"));
        scanner.setCaseSensitive(false);
        scanner.scan();
        String[] files = scanner.getIncludedFiles();
        
        //dataList = parser3Xml(FileLoader.getFilePath("dataprovider/", fileName + ".xml"));
        dataList = parser3Xml(files[0]);
    }

    /**
     * Method description - Parse the XML format file which contains three level nodes
     * 
     * @param filePath
     *            - xml format file path
     * @return - data in type "List"
     * @throws Exception
     */
    private List<Map<String, Map<String, String>>> parser3Xml(String filePath) throws Exception {
        List<Map<String, Map<String, String>>> list = new ArrayList<Map<String, Map<String, String>>>();

        File inputXml = new File(filePath);
        if (inputXml.exists()) {

            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputXml);
            Element rootElement = document.getRootElement();

            for (Iterator<?> i = rootElement.elementIterator(); i.hasNext();) {

                Element childElement = (Element) i.next();
                Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
                Map<String, String> tempMap = new HashMap<String, String>();

                for (Iterator<?> j = childElement.elementIterator(); j.hasNext();) {
                    Element node = (Element) j.next();
                    tempMap.put(node.getName(), node.getText().replace("\\n", "\n"));
                }

                map.put(childElement.getName(), tempMap);
                list.add(map);

            }
        }

        else {
            throw new Exception(new StringBuilder("The file path ").append(filePath).append(" does not exist")
                    .toString());
        }

        return list;
    }

}
