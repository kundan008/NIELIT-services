package nielit.webService;

import nielit.webService.DbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.*;

@Path("/Action")
public class NielitServices{
	
	@POST
	@Path("/registration")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response Registration(String query) throws SQLException
	{
		DbConnection db= new DbConnection();
		Connection con=db.getConnection();
		 Statement stmt = con.createStatement();
         stmt.executeQuery(query);
         String result="Succesfully Registered";
         return Response.status(201).entity(result).build();
	}
	
	
	
	@GET
	@Path("/get")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultSet courses(int i) throws SQLException
	{
		ResultSet rs = null;
		DbConnection db;
		Connection con;
		Statement stmt;
		int k=i;
		switch(k)
		{
			case 1: String query="SELECT * FROM NIELIT WHERE COURSE_TYPE='Short_Term'";
					db= new DbConnection();
					con=db.getConnection();
					stmt = con.createStatement();
					rs = stmt.executeQuery(query);
					break;
					
			case 2:	 String query1="SELECT * FROM NIELIT WHERE COURSE_TYPE='Long_Term'";
					    db= new DbConnection();
					    con=db.getConnection();
						stmt = con.createStatement();
						rs = stmt.executeQuery(query1);
						break;
						
			case 3:  String query2="SELECT * FROM NIELIT WHERE COURSE_TYPE='Corp_Training'";
						db= new DbConnection();
						con=db.getConnection();
						stmt= con.createStatement();
						rs= stmt.executeQuery(query2);
						break;
		
		}
		return rs;
	}
}