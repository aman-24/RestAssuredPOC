package httprequests;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;
import org.apache.commons.io.output.WriterOutputStream;

import java.io.PrintStream;
import java.io.StringWriter;

import static io.restassured.RestAssured.given;

public class Request {

	private Headers headers = new Headers();
    public StringWriter requestWriter;

    /***
     * to be used to print request in Test Report
     * @return
     */
    private PrintStream getRequestLogger() {
        requestWriter = new StringWriter();
        return new PrintStream(new WriterOutputStream(requestWriter), true);
    }

    /***
     * method to create a new resource using POST method
     * @param endPoint - api endpoint url
     * @param body - inuput json payload as string
     * @return
     */
    public Response postRequest(String endPoint, String body){
        return given()
                .filter(new RequestLoggingFilter(getRequestLogger()))
                .headers(headers.getHeaders())
                .body(body)
                .post(endPoint);
    }

    /***
     * method to create a update an existing resource using PUT method
     * @param endPoint - api endpoint url
     * @param body - input json payload as string
     * @return
     */
    public Response putRequest(String endPoint, String body){
        return given()
                .filter(new RequestLoggingFilter(getRequestLogger()))
                .headers(headers.getHeaders())
                .body(body)
                .put(endPoint);
    }

    /***
     * method to retrieve an existing resource using GET method
     * @param endPoint - api endpoint url
     * @return
     */
    public Response getRequest(String endPoint) {
        return given()
                .filter(new RequestLoggingFilter(getRequestLogger()))
                .headers(headers.getHeaders())
                .get(endPoint);
    }

    /***
     * method to delete an existing resource using DELETE method
     * @param endPoint - api endpoint url
     * @return
     */
    public Response deleteRequest(String endPoint){
        return given()
                .filter(new RequestLoggingFilter(getRequestLogger()))
                .headers(headers.getHeaders())
                .body("test")
                .delete(endPoint);
    }
}
