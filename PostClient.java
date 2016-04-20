package nielit.webService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class PostClient {

	//http://localhost:8888/nilelit2/Action/registration
	public static void main(String[] args) {

	  try {

		URL url = new URL("http://localhost:8888/nilelit2/Action/registration");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		Scanner s= new Scanner(System.in);
		
		// posting to database(registration)
		System.out.println("enter name");
		String name=s.nextLine();
		System.out.println("enter email");
		String email=s.nextLine();
		System.out.println("enter phone number");
		String number=s.nextLine();
		String query="insert into query values('"+name+"','"+email+"','"+number+"');";
		String main=URLEncoder.encode(query, "UTF-8");
		s.close();
		
		conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		OutputStream os = conn.getOutputStream();
		os.write(main.getBytes());
		os.flush();

		if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}

		conn.disconnect();

	  } catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	 }

	}

}
