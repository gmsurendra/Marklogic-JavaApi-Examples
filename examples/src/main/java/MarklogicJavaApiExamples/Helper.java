package MarklogicJavaApiExamples;

import com.marklogic.client.io.InputStreamHandle;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Created by gsurendr on 9/30/2016.
 */
public class Helper {

    protected static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, Charset.defaultCharset());
    }

    protected static InputStreamHandle getStream(String filePath) throws IOException {
        return new InputStreamHandle(new ByteArrayInputStream(readFile(filePath).getBytes("UTF-8")));
    }

    protected static String convertToString(org.w3c.dom.Document document) throws Exception {
        DOMSource domSource = new DOMSource(document);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.transform(domSource, result);
        return writer.toString();
    }


}
