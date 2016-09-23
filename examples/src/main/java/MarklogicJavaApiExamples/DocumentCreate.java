package MarklogicJavaApiExamples;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.document.DocumentWriteSet;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.DocumentMetadataHandle;
import com.marklogic.client.io.InputStreamHandle;

import java.io.IOException;
import java.util.Date;

/**
 * Created by gsurendr on 9/24/2016.
 */
public class DocumentCreate {
    static DatabaseClient client;
    static XMLDocumentManager docMgr;

    public DocumentCreate() throws IOException {
        // Get Database Client
        client = new Connection().getClient();

        // Create a XML Document Manager
        docMgr = client.newXMLDocumentManager();

    }

    public void createDocument(String uri) throws IOException {

        // Read the Employee.xml XML from the resource folder
        InputStreamHandle employeeXML = Helper.getStream(Constants.EMPLOYEE_XML_PATH);

        // Create the document.
        docMgr.write(uri, employeeXML);
    }

    public void createDocumentWithCollections(String uri) throws IOException {

        // Read the Employee.xml XML from the resource folder
        InputStreamHandle employeeXML = Helper.getStream(Constants.EMPLOYEE_XML_PATH);

        // Add the Collections MetaData
        DocumentMetadataHandle metadata = new DocumentMetadataHandle();
        metadata.getCollections().add(Constants.COLLECTION_EMPLOYEE);
        metadata.getCollections().add(Constants.COLLECTION_SUPERHERO);

        // Create a XML Document Manager
        XMLDocumentManager docMgr = client.newXMLDocumentManager();

        // Create the document.
        docMgr.write(uri, metadata, employeeXML);
    }

    public void createDocumentWithProperties(String uri) throws IOException {

        // Read the Employee.xml XML from the resource folder
        InputStreamHandle employeeXML = Helper.getStream(Constants.EMPLOYEE_XML_PATH);

        // Add the Collections MetaData
        DocumentMetadataHandle metadata = new DocumentMetadataHandle();
        metadata.getProperties().put(Constants.PROPERTIES_CREATED_DATE, "");
        metadata.getProperties().put(Constants.PROPERTIES_AUTHOR, "Surendra");

        // Create a XML Document Manager
        XMLDocumentManager docMgr = client.newXMLDocumentManager();

        // Create the document.
        docMgr.write(uri, metadata, employeeXML);
    }

    public void createDocumentWithCollectionsAndProperties(String uri) throws IOException {

        // Read the Employee.xml XML from the resource folder
        InputStreamHandle employeeXML = Helper.getStream(Constants.EMPLOYEE_XML_PATH);

        // Add the Collections MetaData
        DocumentMetadataHandle metadata = new DocumentMetadataHandle();
        metadata.getCollections().add(Constants.COLLECTION_EMPLOYEE);
        metadata.getCollections().add(Constants.COLLECTION_SUPERHERO);
        metadata.getProperties().put(Constants.PROPERTIES_CREATED_DATE, "");
        metadata.getProperties().put(Constants.PROPERTIES_AUTHOR, "Surendra");

        // Create the document.
        docMgr.write(uri, metadata, employeeXML);
    }

    public void createDocumentInBatch() throws IOException {

        // Read the Employee.xml XML from the resource folder
        InputStreamHandle employeeXML = Helper.getStream(Constants.EMPLOYEE_XML_PATH);

        // Read the Employee.xml XML from the resource folder
        InputStreamHandle schoolXML = Helper.getStream(Constants.SCHOOL_XML_PATH);

        DocumentWriteSet batch = docMgr.newWriteSet();

        // Add without a metaData.
        batch.add(Constants.URI_EMPLOYEE_BATCH1_XML, employeeXML);
        batch.add(Constants.URI_SCHOOL_BATCH1_XML, schoolXML);

        // Write the documents to the Marklogic
        docMgr.write(batch);

        DocumentWriteSet batch1 = docMgr.newWriteSet();


        // Add with metaData before writing them to Marklogic.

        DocumentMetadataHandle employeeMetadata = new DocumentMetadataHandle();
        employeeMetadata.getCollections().add(Constants.COLLECTION_EMPLOYEE);
        employeeMetadata.getCollections().add(Constants.COLLECTION_SUPERHERO);
        employeeMetadata.getProperties().put(Constants.PROPERTIES_CREATED_DATE, "");
        employeeMetadata.getProperties().put(Constants.PROPERTIES_AUTHOR, "Surendra");

        DocumentMetadataHandle schoolMetaData = new DocumentMetadataHandle();
        schoolMetaData.getCollections().add(Constants.COLLECTION_SCHOOL);
        schoolMetaData.getCollections().add(Constants.COLLECTION_SIDE_KICK);
        schoolMetaData.getProperties().put(Constants.PROPERTIES_CREATED_DATE, "");
        schoolMetaData.getProperties().put(Constants.PROPERTIES_AUTHOR, "Mr.Ganti");

        // Read the Employee.xml XML from the resource folder
        InputStreamHandle employeeXML1 = Helper.getStream(Constants.EMPLOYEE_XML_PATH);

        // Read the Employee.xml XML from the resource folder
        InputStreamHandle schoolXML1 = Helper.getStream(Constants.SCHOOL_XML_PATH);


        // Add with a metaData.
        batch1.add(Constants.URI_EMPLOYEE_BATCH2_XML, employeeMetadata, employeeXML1);
        batch1.add(Constants.URI_SCHOOL_BATCH2_XML, schoolMetaData, schoolXML1);

        // Write the documents to the Marklogic
        docMgr.write(batch1);

    }

}
