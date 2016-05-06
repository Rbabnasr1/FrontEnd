package com.application;

import java.sql.PreparedStatement;
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


@Path("/")
public class UserController {

	@Context
	HttpServletRequest request;
	
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public Response loginPage() {
		return Response.ok(new Viewable("/Login.jsp")).build();

	}
	@GET
	@Path("/home")
	@Produces(MediaType.TEXT_HTML)
	public Response home() {
		return Response.ok(new Viewable("/home.jsp")).build();

	}

	@GET
	@Path("/signUp")
	@Produces(MediaType.TEXT_HTML)
	public Response signUpPage() {
		return Response.ok(new Viewable("/Signup.jsp")).build();
	}
	
	@GET
	@Path("/getLastPosition")
	@Produces(MediaType.TEXT_HTML)
	public Response getLastPositionPage() {
		return Response.ok(new Viewable("/getLastPosition.jsp")).build();
	}

	@GET
	@Path("/new")
	@Produces(MediaType.TEXT_HTML)
	public Response newPage() {
		return Response.ok(new Viewable("/new.jsp")).build();
	}

	@GET
	@Path("/showLocation")
	@Produces(MediaType.TEXT_HTML)
	public Response showLocationPage() {
		return Response.ok(new Viewable("/ShowLocation.jsp")).build();
	}

	@GET
	@Path("/unfollow")
	@Produces(MediaType.TEXT_HTML)
	public Response unfollowPage() {
		return Response.ok(new Viewable("/unFollow.jsp")).build();
	}
	@GET
	@Path("/showhomepage")
	@Produces(MediaType.TEXT_HTML)
	public Response showhomePage() {
		return Response.ok(new Viewable("/showhomepage.jsp")).build();
	}
	
	@GET
	@Path("/checkin")
	@Produces(MediaType.TEXT_HTML)
	public Response showCheckinPage() {
		return Response.ok(new Viewable("/checkin.jsp")).build();
	}

	@POST
	@Path("/updateMyLocation")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateLocation(@FormParam("lat") String lat,
			@FormParam("long") String lon) {
		HttpSession session = request.getSession();
		Long id = (Long) session.getAttribute("id");
		// String serviceUrl =
		// "http://se2firstapp-softwareeng2.rhcloud.com/FCISquare/rest/updatePosition";
		String serviceUrl = "http://mmars-facelocation.rhcloud.com/FCISquare/rest/updatePosition";

		String urlParameters = "id=" + id + "&lat=" + lat + "&long=" + lon;
		// System.out.println(urlParameters);
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		JSONObject obj;
		try {
			obj = (JSONObject) parser.parse(retJson);
			Long status = (Long) obj.get("status");
			if (status == 1)
				return "Your location is updated";
			else
				return "A problem occured";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "A problem occured";

	}

	@POST
	@Path("/doLogin")
	@Produces(MediaType.TEXT_HTML)
	public Response showHomePage(@FormParam("email") String email,
			@FormParam("pass") String pass) {
		
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/login";
		String serviceUrl = "http://mmars-facelocation.rhcloud.com/FCISquare/rest/login";
				String urlParameters = "email=" + email + "&pass=" + pass;
				
		//System.out.println( serviceUrl + " " + urlParameters);
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		HttpSession session = request.getSession();
		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();
		System.out.println( retJson);
		
		try {
			obj = (JSONObject) parser.parse(retJson);
			session.setAttribute("email", obj.get("email"));
			session.setAttribute("name", obj.get("name"));
			session.setAttribute("id", obj.get("id"));
			session.setAttribute("lat", obj.get("lat"));
			session.setAttribute("_long", obj.get("long"));
			session.setAttribute("pass", obj.get("pass"));
			Map<String, String> map = new HashMap<String, String>();

			map.put("name", (String) obj.get("name"));
			map.put("email", (String) obj.get("email"));
			map.put("lat", String.valueOf(obj.get("lat")));
			map.put("_long", String.valueOf(obj.get("long")));

			return Response.ok(new Viewable("/home.jsp", map)).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@POST
	@Path("/doSignUp")
	@Produces(MediaType.TEXT_HTML)
	public Response showHomePage(@FormParam("name") String name,
			@FormParam("email") String email, @FormParam("pass") String pass) {
		// String serviceUrl =
		// "http://se2firstapp-softwareeng2.rhcloud.com/FCISquare/rest/signup";
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/signup";
		String serviceUrl = "http://mmars-facelocation.rhcloud.com/FCISquare/rest/signup";
		String urlParameters = "name=" + name + "&email=" + email + "&pass="
				+ pass;
		// System.out.println(urlParameters);
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		HttpSession session = request.getSession();
		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();
		try {
			obj = (JSONObject) parser.parse(retJson);
			session.setAttribute("email", obj.get("email"));
			session.setAttribute("name", obj.get("name"));
			session.setAttribute("id", obj.get("id"));
			session.setAttribute("lat", obj.get("lat"));
			session.setAttribute("_long", obj.get("long"));
			session.setAttribute("pass", obj.get("pass"));
			Map<String, String> map = new HashMap<String, String>();

			map.put("name", (String) obj.get("name"));
			map.put("email", (String) obj.get("email"));

			return Response.ok(new Viewable("/home.jsp", map)).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@POST
	@Path("/doFollow")
	@Produces(MediaType.TEXT_HTML)
	public Response doFollow(@FormParam("id2") String id2) {
		HttpSession session = request.getSession();
		Long id3 = (Long) session.getAttribute("id");
		
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/follow";
		String serviceUrl = "http://mmars-facelocation.rhcloud.com/FCISquare/rest/follow";
		
		String urlParameters = "id1=" + id3 + "&id2=" + id2;

		System.out.println(urlParameters);
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");

		// HttpSession session = request.getSession();
		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();	
		
		Map<String, String> map = new HashMap<String, String>();

		map.put("name", (String) obj.get("name"));
		map.put("email", (String) obj.get("email"));

		return Response.ok(new Viewable("/home.jsp", map)).build();

	}

	@POST
	@Path("/doUnfollow")
	@Produces(MediaType.TEXT_HTML)
	public Response doUnfollow(@FormParam("id1") String id1) {
		HttpSession session = request.getSession();
		Long id3 = (Long) session.getAttribute("id");
		// String serviceUrl =
		// "http://se2firstapp-softwareeng2.rhcloud.com/FCISquare/rest/signup";

		//String serviceUrl = "http://localhost:8080/FCISquare/rest/unfollow";
		String serviceUrl = "http://mmars-facelocation.rhcloud.com/FCISquare/rest/unfollow";
		

		String urlParameters = "id1=" + id3 + "&id2=" + id1;
		// System.out.println(urlParameters);
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		System.out.println("Hello");

		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();

		Map<String, String> map = new HashMap<String, String>();

		map.put("name", (String) obj.get("name"));
		map.put("email", (String) obj.get("email"));

		return Response.ok(new Viewable("/home.jsp", map)).build();

	}

	@POST
	@Path("/getLocation")
	@Produces(MediaType.TEXT_HTML)
	public Response getLastLocationPage() {

		String serviceUrl = "http://mmars-facelocation.rhcloud.com/FCISquare/rest/getLastPosition";
		// String serviceUrl = "http://localhost:8080/FCISquare/rest/login";
		HttpSession session = request.getSession();
		Long id3 = (Long) session.getAttribute("id");
		String urlParameters = "id1=" + id3;
//		System.out.println(urlParameters);
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		// HttpSession session = request.getSession();
		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();
		try {
			obj = (JSONObject) parser.parse(retJson);
			session.setAttribute("email", obj.get("email"));
			session.setAttribute("name", obj.get("name"));
			session.setAttribute("id", obj.get("id"));
			session.setAttribute("lat", obj.get("lat"));
			session.setAttribute("long", obj.get("long"));
			session.setAttribute("pass", obj.get("pass"));
			Map<String, String> map = new HashMap<String, String>();

			map.put("lat", String.valueOf(obj.get("lat")));
			map.put("_long", String.valueOf(obj.get("long")));

			return Response.ok(new Viewable("/new.jsp", map)).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GET
	@Path("/doShowHomePage")
	@Produces(MediaType.TEXT_HTML)
	public Response showhomepage() {
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/showhomepage";
		String serviceUrl = "http://mmars-facelocation.rhcloud.com/FCISquare/rest/showhomepage";
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("name");
			String urlParameters = "user=" + user  ;
		// System.out.println(urlParameters);
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		
		ArrayList<String> arr = new ArrayList();
		JSONObject obj = new JSONObject();
		//JSONArray obj = new JSONArray();;
		
		JSONParser parser = new JSONParser();
		System.out.println("rabab henaa :D w samar     "+retJson);
		try {
				
			obj =  (JSONObject) parser.parse(retJson);
			//obj =  (JSONArray) parser.parse(retJson);
			System.out.println("rabab samar <# :D    "+obj);
			//obj = (JSONArray) new JSONParser().parse(retJson);
			System.out.println(obj.size());
				for(int i = 0; i < obj.size(); i++) {
					
					//arr.add(obj.get(i).toString());
					arr.add(obj.get(Integer.toString(i+1)).toString());
				}
		
	
			
			Map<String, ArrayList<String> > map = new HashMap<String, ArrayList<String> >();
			//	System.out.println("Arr = " + arr);
				map.put("user", arr);
				
				return Response.ok(new Viewable("/showhomepage.jsp", map)).build();

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}
	
}
