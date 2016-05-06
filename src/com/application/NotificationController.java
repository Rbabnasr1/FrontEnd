package com.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


@Path("/notification")
public class NotificationController {
	@Context
	HttpServletRequest request; 
	
	@GET
	@Path("/response")
	@Produces(MediaType.TEXT_HTML)
	public Response showcheckinPage() {
		return Response.ok(new Viewable("/ShowCheckIn.jsp")).build();
	}	
	@GET
	@Path("/showNotify")
	@Produces(MediaType.TEXT_HTML)
	public Response showNotifyPage() {
		return Response.ok(new Viewable("/Notification.jsp")).build();
	}	

	@GET
	@Path("/GetAllNotifications")
	@Produces(MediaType.TEXT_HTML)
	public Response showNotification() {
		
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/GetAllNotifications";
		String serviceUrl = "http://mmars-facelocation.rhcloud.com/FCISquare/rest/GetAllNotifications";

		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("name");
		
		
		String urlParameters = "user=" + user ;
		// System.out.println(urlParameters);
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		
		ArrayList<String> arr = new ArrayList ();
		//JSONArray obj = new JSONArray();
		JSONObject obj =new JSONObject();
		JSONParser parser = new JSONParser();
		System.out.println(retJson);
		
		try {
			System.out.println("Arr = " + retJson);
			//obj =  (JSONArray) parser.parse(retJson);
			obj =  (JSONObject) parser.parse(retJson);
			
			System.out.println("A = " + obj);
			for(int i = 0; i < obj.size(); i++) {
				
				//arr.add(obj.get(i).toString());
				arr.add(obj.get(Integer.toString(i+1)).toString());
			}
			Map<String, ArrayList<String> > map = new HashMap<String, ArrayList<String> >();
			//	System.out.println("Arr = " + arr);
				map.put("user", arr);
				
				return Response.ok(new Viewable("/Notification.jsp", map)).build();

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	
				}

		
	
	@POST
	@Path("/respondToNotifications")
	@Produces(MediaType.TEXT_HTML)
	public Response respondToNotification(@FormParam("place") String place) {
		
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("name");
	//	String serviceUrl = "http://localhost:8080/FCISquare/rest/responseToNotifications";
		String serviceUrl = "http://mmars-facelocation.rhcloud.com/FCISquare/rest/responseToNotifications";
		
		
	//http://mmars-facelocation.rhcloud.com/FCISquare/rest/GetAllNotifications
		String urlParameters = "user=" + user + "&place=" +place ;
		 System.out.println(urlParameters);
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		
		ArrayList<String> arr = new ArrayList ();
		JSONArray obj = new JSONArray();;
		JSONParser parser = new JSONParser();
		System.out.println(retJson);
		try {
			obj =  (JSONArray) parser.parse(retJson);
			for(int i = 0; i < obj.size(); i++) {
				
				arr.add(obj.get(i).toString());
			}
	
			
			Map<String, ArrayList<String> > map = new HashMap<String, ArrayList<String> >();
	//		System.out.println("Arr = " + arr);
			map.put("user", arr);
			
			return Response.ok(new Viewable("/Notification.jsp", map)).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
}
