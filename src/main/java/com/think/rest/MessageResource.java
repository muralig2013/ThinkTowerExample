package com.think.rest;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;
import com.thinktower.utils.CacheUtils;
import com.thinktower.custom.*;

@Path("/myrestapp")
public class MessageResource {
	static CacheUtils<String, String> cacheUtils = new CacheUtils<String,String>(10000, 100, 20);
	@POST
	@Path("/post")
	public Response postMessages(@QueryParam("message") String message, @Context Request messageContext) {
		String key;
		//int counter = 1,status = 200;
		/*int TTL_CACHE_MESSAGES=100000; //in ms
        String tag =  "message"+messageId;
*/
        //is under cache
        /*Response r = HttpCacheRizze.getCachedResponseMilliseconds(messageContext, tag, TTL_CACHE_MESSAGES);
        if(r!=null){
             return r;
        }
        
         ArrayList<String> messageList = new ArrayList<String>();
         messageList.add(message);
         
         if(messageList == null || messageList.size() == 0 )
             status = 204;

         r = HttpCacheRizze.getCacheInvalidatedResponse(status, new Gson().toJson(messageList), tag, TTL_CACHE_MESSAGES);

         return r;
*/
		key = String.valueOf(System.currentTimeMillis());
		try{
			if(message.length()<=249){
				cacheUtils.put(key, message);
			}else {
				throw new MessageSizeExceedsException("Message size exceeds 249 characters");	
			}
		}catch(MessageSizeExceedsException e){
			return Response.status(Status.BAD_REQUEST).build();
		}
		return Response.status(Status.OK).build();
	}
	
	@GET
	@Path("/home")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMessages() {
		ArrayList<String> messageList = cacheUtils.getAll();
		if(messageList != null && messageList.size()>0){
			return Response.status(Status.OK).entity(new Gson().toJson(messageList)).build();	
		}
		return Response.status(200).entity(new Gson().toJson("")).build();
	}

}