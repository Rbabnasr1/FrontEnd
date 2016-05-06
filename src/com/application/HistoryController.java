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

@Path("/history")
public class HistoryController {
	@Context
	HttpServletRequest request;
	
	
	@GET
	@Path("/history")
	@Produces(MediaType.TEXT_HTML)
	public Response historyPage() {
		return Response.ok(new Viewable("/historyofactions.jsp")).build();
	}
	@GET
	@Path("/PSort")
	@Produces(MediaType.TEXT_HTML)
	public Response sort1() {
		return Response.ok(new Viewable("/PlaceSorter.jsp")).build();
	}
	@GET
	@Path("/USort")
	@Produces(MediaType.TEXT_HTML)
	public Response sort2() {
		return Response.ok(new Viewable("/UserSorter.jsp")).build();
	}
	
	@GET
	@Path("/showhistory")
	@Produces(MediaType.TEXT_HTML)
	public Response historyofactions() {
		
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/historyofactions";
		String serviceUrl="http://mmars-facelocation.rhcloud.com/FCISquare/rest/historyofactions";
		
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("name");
		
		String urlParameters = "user=" + user  ;
		// System.out.println(urlParameters);
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		
		ArrayList<String> arr = new ArrayList();
		JSONObject obj = new JSONObject();;
		JSONParser parser = new JSONParser();
		System.out.println("rabab henaa :D     "+retJson);
		try {
			//{"1":"Show home page","2":"Show home page","3":"Show home page","4":"Show home page","5":"Show home page","6":"Show home page"}
			
			obj =  (JSONObject) parser.parse(retJson);
			System.out.println("rabab samar <# :D     "+obj);
			
			//obj = (JSONArray) new JSONParser().parse(retJson);
			System.out.println(obj.size());
			for(int i = 0; i < obj.size(); i++) {
				arr.add(obj.get(Integer.toString(i+1)).toString());
				System.out.println("hello    "+i+arr.get(i));
			}
	
			
			Map<String, ArrayList<String> > map = new HashMap<String, ArrayList<String> >();
			//	System.out.println("Arr = " + arr);
				map.put("user", arr);
				
				return Response.ok(new Viewable("/historyofactions.jsp", map)).build();

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	@GET
	@Path("/Psort")
	@Produces(MediaType.TEXT_HTML)
	public Response sortplace() {
	//	String serviceUrl = "http://localhost:8080/FCISquare/rest/Placesorter";
		String serviceUrl="http://mmars-facelocation.rhcloud.com/FCISquare/rest/Placesorter";
		
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("name");
		
		String urlParameters = "user=" + user  ;
		// System.out.println(urlParameters);
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		
		ArrayList<String> arr = new ArrayList();
		JSONObject obj = new JSONObject();;
		JSONParser parser = new JSONParser();
		System.out.println("rabab henaa :D     "+retJson);
		try {
			//{"1":"Show home page","2":"Show home page","3":"Show home page","4":"Show home page","5":"Show home page","6":"Show home page"}
			
			obj =  (JSONObject) parser.parse(retJson);
			System.out.println("rabab samar <# :D     "+obj);
			
			//obj = (JSONArray) new JSONParser().parse(retJson);
			System.out.println(obj.size());
			for(int i = 0; i < obj.size(); i++) {
				arr.add(obj.get(Integer.toString(i+1)).toString());
				System.out.println("hello    "+i+arr.get(i));
			}
	
			
			Map<String, ArrayList<String> > map = new HashMap<String, ArrayList<String> >();
			//	System.out.println("Arr = " + arr);
				map.put("user", arr);
				
				return Response.ok(new Viewable("/PlaceSorter.jsp", map)).build();

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}
	@GET
	@Path("/Usort")
	@Produces(MediaType.TEXT_HTML)
	public Response sort1place() {
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/Usersorter";
		//http://mmars-facelocation.rhcloud.com/FCISquare/rest/GetAllNotifications
		String serviceUrl="http://mmars-facelocation.rhcloud.com/FCISquare/rest/Usersorter";
		
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("name");
		
		String urlParameters = "user=" + user  ;
		// System.out.println(urlParameters);
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		
		ArrayList<String> arr = new ArrayList();
		JSONObject obj = new JSONObject();;
		JSONParser parser = new JSONParser();
		System.out.println("rabab henaa :D     "+retJson);
		try {
			//{"1":"Show home page","2":"Show home page","3":"Show home page","4":"Show home page","5":"Show home page","6":"Show home page"}
			
			obj =  (JSONObject) parser.parse(retJson);
			System.out.println("rabab samar <# :D     "+obj);
			
			//obj = (JSONArray) new JSONParser().parse(retJson);
			System.out.println(obj.size());
			for(int i = 0; i < obj.size(); i++) {
				arr.add(obj.get(Integer.toString(i+1)).toString());
			//ew	System.out.println("hello    "+i+arr.get(i));
			}
	
			
			Map<String, ArrayList<String> > map = new HashMap<String, ArrayList<String> >();
			//	System.out.println("Arr = " + arr);
				map.put("user", arr);
				
				return Response.ok(new Viewable("/UserSorter.jsp", map)).build();

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}
	
}
