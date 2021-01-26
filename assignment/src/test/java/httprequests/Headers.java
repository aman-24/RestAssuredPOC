package httprequests;

import java.util.HashMap;

public class Headers {

	  private HashMap<String, String> headersMap = new HashMap<>();

	    public void addAHeader(String headerName, String headerValue) {
	        if (headersMap.containsKey(headerName)) {
	            headersMap.replace(headerName, headerValue);
	        } else {
	            headersMap.put(headerName, headerValue);
	        }
	    }

	    public void resetHeaders() {
	        if (!headersMap.isEmpty()) headersMap.clear();
	    }

	    public void removeHeader(String header) {
	        headersMap.remove(header);
	    }

	    public HashMap<String, String> getHeaders(){
	        return headersMap;
	    }
}
