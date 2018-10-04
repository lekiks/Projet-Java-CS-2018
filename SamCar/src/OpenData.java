import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.lang.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.*;

public class OpenData {

	private List<Event> listEvents = new ArrayList<Event>();
	static int taille_selection = 10;
	JSONParser parser = new JSONParser();

	private static String readUrl(String urlString) throws Exception {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read); 

			return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}

	public OpenData(){
		try {
			//Object data = parser.parse(readFile("/Users/corentinbourlet/Downloads/evenements-publics-cibul.geojson.json"));
			Object data = parser.parse(readUrl("https://www.data.gouv.fr/fr/datasets/r/a802c828-1b01-4408-b092-a2e294ddbef2"));
			JSONObject general = (JSONObject) data;
			JSONArray features = (JSONArray) general.get("features");

			for(int i=0; i<taille_selection; i++){
				JSONObject jsonEvent = (JSONObject) features.get(i);
				
				JSONObject propertiesEvent = (JSONObject) jsonEvent.get("properties");
				String titelEvent =  (String) propertiesEvent.get("title");
				String addressEvent = (String) propertiesEvent.get("address");
				String dateEvent = (String) propertiesEvent.get("date_start");
				System.out.println(titelEvent+";"+addressEvent+";"+dateEvent);
				
				Event event = new Event();
				event.setAddress(addressEvent);
				event.setDate(dateEvent);
				event.setEventName(titelEvent);

				listEvents.add(event);

				System.out.println(event.getEventName());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}    
	}
}