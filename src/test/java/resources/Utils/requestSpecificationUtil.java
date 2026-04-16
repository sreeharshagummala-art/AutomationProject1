package resources.Utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class requestSpecificationUtil {
    public static RequestSpecification req;

    //Below we are creating a stream which can be used for printing logs. FileOutputStream creates a new file and saves the logs on it.

    public RequestSpecification requestSpecification() throws IOException {
        if (req==null) {
            PrintStream logs = new PrintStream(new FileOutputStream("logsForRecentTest.txt"));

            req = new RequestSpecBuilder()
                    .setBaseUri(getGlobalValue("baseURI"))
                    .addQueryParam("key", "qaclick123")
                    .setContentType(ContentType.JSON)
                    .addFilter(RequestLoggingFilter.logRequestTo(logs)) //This step prints all logs to "logs" declared above
                    .addFilter(ResponseLoggingFilter.logResponseTo(logs))
                    .build();
            return req;
        }
        return req;
    }

    public static String getGlobalValue(String prop) throws IOException {
        Properties p = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\rpooj\\IdeaProjects\\RestAssuredCucumberBDD\\src\\test\\java\\resources\\global.properties");
        p.load(fis);
        return p.getProperty(prop);
    }
}
