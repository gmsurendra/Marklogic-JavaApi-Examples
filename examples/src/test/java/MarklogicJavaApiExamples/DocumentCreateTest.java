package MarklogicJavaApiExamples;

import com.marklogic.client.io.DocumentMetadataHandle;
import junit.framework.TestCase;
import org.custommonkey.xmlunit.XMLUnit;
import org.custommonkey.xmlunit.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by gsurendr on 10/1/2016.
 */
public class DocumentCreateTest extends XMLTestCase {

    private DocumentCreate dc;
    private DocumentRead dr;

    public DocumentCreateTest() throws IOException {
        dc = new DocumentCreate();
        dr = new DocumentRead();
        XMLUnit.setIgnoreWhitespace(true);
    }

    public void testCreateDocument() throws Exception {
        dc.createDocument(Constants.URI_EMPLOYEE1_XML);
        testCreateDocument(Constants.URI_EMPLOYEE1_XML, Constants.EMPLOYEE_XML_PATH);
    }

    public void testCreateDocumentWithCollections() throws Exception {
        dc.createDocumentWithCollections(Constants.URI_EMPLOYEE2_XML);
        testCreateDocumentWithCollections(Constants.URI_EMPLOYEE2_XML, Constants.COLLECTION_EMPLOYEE, Constants.COLLECTION_SUPERHERO);
    }

    public void testCreateDocumentWithProperties() throws Exception {
        dc.createDocumentWithProperties(Constants.URI_EMPLOYEE3_XML);
        Map<String, String> map = new HashMap<String, String>();
        map.put(Constants.PROPERTIES_CREATED_DATE, "");
        map.put(Constants.PROPERTIES_AUTHOR, "Surendra");
        testCreateDocumentWithProperties(Constants.URI_EMPLOYEE3_XML, map);
    }

    public void testCreateDocumentWithCollectionsAndProperties() throws Exception {
        dc.createDocumentWithCollectionsAndProperties(Constants.URI_EMPLOYEE4_XML);

        testCreateDocumentWithCollections(Constants.URI_EMPLOYEE4_XML, Constants.COLLECTION_EMPLOYEE, Constants.COLLECTION_SUPERHERO);

        Map<String, String> map = new HashMap<String, String>();
        map.put(Constants.PROPERTIES_CREATED_DATE, "");
        map.put(Constants.PROPERTIES_AUTHOR, "Surendra");
        testCreateDocumentWithProperties(Constants.URI_EMPLOYEE4_XML, map);
    }

    public void testCreateDocumentInBatch() throws Exception {
        dc.createDocumentInBatch();

        testCreateDocument(Constants.URI_EMPLOYEE_BATCH1_XML, Constants.EMPLOYEE_XML_PATH);
        testCreateDocument(Constants.URI_SCHOOL_BATCH1_XML, Constants.SCHOOL_XML_PATH);

        testCreateDocument(Constants.URI_EMPLOYEE_BATCH2_XML, Constants.EMPLOYEE_XML_PATH);
        testCreateDocument(Constants.URI_SCHOOL_BATCH2_XML, Constants.SCHOOL_XML_PATH);

        testCreateDocumentWithCollections(Constants.URI_SCHOOL_BATCH2_XML, Constants.COLLECTION_SCHOOL, Constants.COLLECTION_SIDE_KICK);
        testCreateDocumentWithCollections(Constants.URI_EMPLOYEE_BATCH2_XML, Constants.COLLECTION_EMPLOYEE, Constants.COLLECTION_SUPERHERO);

        Map<String, String> map1 = new HashMap<String, String>();
        map1.put(Constants.PROPERTIES_CREATED_DATE, "");
        map1.put(Constants.PROPERTIES_AUTHOR, "Surendra");
        testCreateDocumentWithProperties(Constants.URI_EMPLOYEE_BATCH2_XML, map1);

        Map<String, String> map2 = new HashMap<String, String>();
        map2.put(Constants.PROPERTIES_CREATED_DATE, "");
        map2.put(Constants.PROPERTIES_AUTHOR, "Mr.Ganti");
        testCreateDocumentWithProperties(Constants.URI_SCHOOL_BATCH2_XML, map2);

    }


    private void testCreateDocument(String uri, String xmlPath) throws Exception {
        String xmlFromMarkLogic = dr.readDocument(uri);
        String xmlFromResource = Helper.readFile(xmlPath);
        assertXMLEqual("Comparing " + uri + " document with " + xmlPath + " resources ", xmlFromMarkLogic, xmlFromResource);
    }

    private void testCreateDocumentWithCollections(String uri, String... collections) throws Exception {


        DocumentMetadataHandle.DocumentCollections col = dr.readMetaDataCollections(uri);
        for (String s : collections) {
            assertTrue("Collection Check", col.contains(s));
        }
    }

    private void testCreateDocumentWithProperties(String uri, Map<String, String> properties) throws Exception {
        DocumentMetadataHandle.DocumentProperties prop = dr.readMetaDataProperties(uri);
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            assertTrue(entry.getKey() + " Property Check", prop.containsKey(entry.getKey()));
            if (!entry.getValue().equals(""))
                assertEquals(entry.getKey() + " property value check", prop.get(entry.getKey()), entry.getValue());
        }
    }

}