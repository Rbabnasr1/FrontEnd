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


@Path("/checkin")
public class CheckinController {
	@Context
	HttpServletRequest request; 
	
	
	@GET
	@Path("/checkin")
	@Produces(MediaType.TEXT_HTML)
	public Response showcheckInPage() {
		return Response.ok(new Viewable("/checkin.jsp")).build();
	}
	@POST
	@Path("/doCheckin")
	@Produces(MediaType.TEXT_HTML)
	public Response showcheckinPage( @FormParam("place") String place) {
		// String serviceUrl =
		// "http://se2firstapp-softwareeng2.rhcloud.com/FCISquare/rest/signup";
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("name");
		String serviceUrl = "http://mmars-facelocation.rhcloud.com/FCISquare/rest/checkin";
	//http://mmars-facelocation.rhcloud.com/FCISquare/rest/checkin
		String urlParameters = "user=" + user +  "&place=" + place;
		 System.out.println(urlParameters);
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
	
		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();
		try {
			obj = (JSONObject) parser.parse(retJson);
			session.setAttribute("user", obj.get("user"));
			session.setAttribute("place", obj.get("place"));
			
			
			Map<String, String> map = new HashMap<String, String>();

			map.put("user", (String) obj.get("user"));
			map.put("place", (String) obj.get("place"));

			return Response.ok(new Viewable("/home.jsp", map)).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	@POST
	@Path("/doLikeCheckin")
	@Produces(MediaType.TEXT_HTML)
	public Response showcheckinPage( @FormParam("notifieduser") String notifieduser,
			@FormParam("place") String place) {
		// String serviceUrl =
		// "http://se2firstapp-softwareeng2.rhcloud.com/FCISquare/rest/signup";
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("name");
		String serviceUrl = "http://mmars-facelocation.rhcloud.com/FCISquare/rest/likeCheckin";
	//http://mmars-facelocation.rhcloud.com/FCISquare/rest/likeCheckin
		String urlParameters = "notifieduser=" + notifieduser +  "&place=" + place + "&user=" + user;
		 System.out.println(urlParameters);
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
	
		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();
		try {
			obj = (JSONObject) parser.parse(retJson);
			session.setAttribute("notifieduser", obj.get("notifieduser"));
			session.setAttribute("place", obj.get("place"));
			session.setAttribute("user", obj.get("user"));
			
			Map<String, String> map = new HashMap<String, String>();

			map.put("notifieduser", (String) obj.get("notifieduser"));
			map.put("place", (String) obj.get("place"));
			map.put("user", (String) obj.get("user"));

			return Response.ok(new Viewable("/home.jsp", map)).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	@POST
	@Path("/doCommentCheckin")
	@Produces(MediaType.TEXT_HTML)
	public Response showcheckinPage( @FormParam("notifieduser") String notifieduser,
			@FormParam("place") String place,@FormParam("comment") String comment) {
		// String serviceUrl =
		// "http://se2firstapp-softwareeng2.rhcloud.com/FCISquare/rest/signup";
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("name");
		String serviceUrl = "http://mmars-facelocation.rhcloud.com/FCISquare/rest/commentCheckin";
	//http://mmars-facelocation.rhcloud.com/FCISquare/rest/commentCheckin
		String urlParameters = "notifieduser=" + notifieduser +  "&place=" + place +
				"&comment=" + comment + "&user=" + user;
		 System.out.println(urlParameters);
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
	
		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();
		try {
			obj = (JSONObject) parser.parse(retJson);
			session.setAttribute("notifieduser", obj.get("notifieduser"));
			session.setAttribute("place", obj.get("place"));
			session.setAttribute("comment", obj.get("comment"));
			session.setAttribute("user", obj.get("user"));
			
			Map<String, String> map = new HashMap<String, String>();

			map.put("notifieduser", (String) obj.get("notifieduser"));
			map.put("place", (String) obj.get("place"));
			map.put("comment", (String) obj.get("comment"));
			map.put("user", (String) obj.get("user"));

			return Response.ok(new Viewable("/home.jsp", map)).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
