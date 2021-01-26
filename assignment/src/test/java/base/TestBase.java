package base;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeSuite;

import httprequests.EndPoints;
import utils.FileUtils;

public class TestBase {

	private FileUtils fileUtils = new FileUtils();
	
	 /***
     * This method will execute before the test suite starts and calls
     * the configureEndpoints method
     * @throws IOException
     */
    @BeforeSuite
    public void setup() throws IOException {
        configureEndpoints();
    }

    /***
     * This method reads the endpoints.properties files and load them into a hash-map
     * which is finally being used by the Endpoints class to set various endpoints
     * @throws IOException
     */
    private void configureEndpoints() throws IOException {
        Properties properties = fileUtils.readPropertiesFile("endpoints.properties");
        EndPoints.endPoints = fileUtils.loadPropertiesFileInHashMap(properties);
    }
}
