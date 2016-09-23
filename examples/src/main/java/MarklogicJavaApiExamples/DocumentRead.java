package MarklogicJavaApiExamples;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.DOMHandle;
import com.marklogic.client.io.DocumentMetadataHandle;
import com.marklogic.client.io.InputStreamHandle;
import org.w3c.dom.Document;

import java.io.IOException;

/**
 * Created by gsurendr on 10/1/2016.
 */
public class DocumentRead {
    static DatabaseClient client;
    static XMLDocumentManager docMgr;

    public DocumentRead() throws IOException {
        // Get Database Client
        client = new Connection().getClient();

        // Create a XML Document Manager
        docMgr = client.newXMLDocumentManager();

    }

    public String readDocument(String uri) throws Exception {
        DOMHandle handle = new DOMHandle();
        docMgr.read(uri, handle);
        return Helper.convertToString(handle.get());
    }

    public DocumentMetadataHandle.DocumentCollections readMetaDataCollections(String uri) throws Exception {
        DocumentMetadataHandle metadataHandle = new DocumentMetadataHandle();
        docMgr.readMetadata(uri, metadataHandle);
        return metadataHandle.getCollections();
    }

    public DocumentMetadataHandle.DocumentProperties readMetaDataProperties(String uri) throws Exception {
        DocumentMetadataHandle metadataHandle = new DocumentMetadataHandle();
        docMgr.readMetadata(uri, metadataHandle);
        return metadataHandle.getProperties();
    }

}
