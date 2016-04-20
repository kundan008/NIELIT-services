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


class GetClient {

	public static void main(String[] args) {

		  try {

				URL url = new URL("http://localhost:8888/nilelit2/Action/get");
				
				// getting choice of type of course
				Scanner s= new Scanner(System.in);
				
				System.out.println("enter ur choice/n");
				System.out.println("a1. long term /n a2. short term /n a3. corp training");
				String choice=s.nextLine();
				String choice1=URLEncoder.encode(choice,"UTF-8");
				s.close();
				// choice has been entered
				
				
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}
				
				
				// writing choice to service to get type of course  from database
				
				OutputStream os = conn.getOutputStream();
				os.write(choice1.getBytes());
				os.flush();
				
				//  checking for errors
				
				if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
					throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
				}
				
				
				// printing result set from database to the console
				
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

