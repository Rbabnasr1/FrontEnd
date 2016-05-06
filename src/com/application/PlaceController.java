package com.application;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


@Path("/place")
public class PlaceController {
	@Context
	HttpServletRequest request; 
	
	@GET
	@Path("/addPlace")
	@Produces(MediaType.TEXT_HTML)
	public Response showPlacePage() {
		return Response.ok(new Viewable("/place.jsp")).build();
	}	

	@POST
	@Path("/doAddPlace")
	@Produces(MediaType.TEXT_HTML)
	public Response showAddPlacePage(@FormParam("name") String name,
			 @FormParam("descr") String descr) {
		
	//	String serviceUrl = "http://mmars-facelocation.rhcloud.com/FCISquare/rest/addPlace";
	//http://mmars-facelocation.rhcloud.com/FCISquare/rest/addPlace
		String serviceUrl = "http://mmars-facelocation.rhcloud.com/FCISquare/rest/addPlace";
		
		String urlParameters = "name=" + name +  "&descr=" + descr;
		System.out.println(urlParameters);
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		HttpSession session = request.getSession();
		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();
					Map<String, String> map = new HashMap<String, String>();

			map.put("name", (String) obj.get("name"));
			map.put("descr", (String) obj.get("descr"));
			System.out.println( "service url ..... " + serviceUrl +" ... ret jason .. "+retJson+ "... urlParameters.." + urlParameters );
			return Response.ok(new Viewable("/home.jsp", map)).build();

	}
	@POST
	@Path("/doSavePlace")
	@Produces(MediaType.TEXT_HTML)
	public Response showSavePlace( @FormParam("place") String place) {
		// String serviceUrl =
		// "http://se2firstapp-softwareeng2.rhcloud.com/FCISquare/rest/signup";
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("name");
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/saveplace";
		String serviceUrl = "http://mmars-facelocation.rhcloud.com/FCISquare/rest/saveplace";
		
	//http://mmars-facelocation.rhcloud.com/FCISquare/rest/savePlace
		String urlParameters = "user=" + user +  "&place=" + place;
		System.out.println(urlParameters);
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		
		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();
		/*	obj = (JSONObject) parser.parse(retJson);
			session.setAttribute("user", obj.get("user"));
			session.setAttribute("place", obj.get("place"));
		*/
			Map<String, String> map = new HashMap<String, String>();

			map.put("user", (String) obj.get("user"));
			map.put("place", (String) obj.get("place"));

			
			System.out.println("rabab"+retJson);
			
			
			return Response.ok(new Viewable("/home.jsp", map)).build();

			
			
		

	}

}
