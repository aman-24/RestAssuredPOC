package httprequests;

import java.util.HashMap;

public class EndPoints {
	public static HashMap<String, String> endPoints;

    public static String getPromotionsEndpoint() {
        return endPoints.get("baseURI") + endPoints.get("promotions");
    }

}
