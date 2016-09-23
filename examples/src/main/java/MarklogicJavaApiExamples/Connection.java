package MarklogicJavaApiExamples;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by gsurendr on 9/30/2016.
 */
class Connection {
    private static Properties prop;
    private final String REST_SERVER = "RestServer";
    private final String REST_PORT = "RestPort";
    private final String DATABASE_NAME = "DatabaseName";
    private final String USER_NAME = "UserName";
    private final String PASSWORD = "Password";

    protected DatabaseClient getClient() throws IOException {
        prop = new Properties();
        prop.load(getClass().getResourceAsStream("/Connection.properties"));

        return DatabaseClientFactory.newClient(prop.getProperty(REST_SERVER)
                , Integer.parseInt(prop.getProperty(REST_PORT))
                , prop.getProperty(DATABASE_NAME)
                , prop.getProperty(USER_NAME)
                , prop.getProperty(PASSWORD)
                , DatabaseClientFactory.Authentication.DIGEST);
    }
}
