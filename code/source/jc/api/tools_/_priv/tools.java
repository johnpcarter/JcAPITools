package jc.api.tools_._priv;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import com.jc.wm.introspection.APIInfo;
import com.jc.wm.introspection.PackageIntrospector;
import com.wm.app.b2b.server.ServerAPI;
import com.wm.app.b2b.server.ServerStartupNotifier;
// --- <<IS-END-IMPORTS>> ---

public final class tools

{
	// ---( internal utility methods )---

	final static tools _instance = new tools();

	static tools _newInstance() { return new tools(); }

	static tools _cast(Object o) { return (tools)o; }

	// ---( server methods )---




	public static final void envProperties (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(envProperties)>> ---
		// @sigtype java 3.5
		// [i] record:0:required apiServer
		// [i] - field:0:required apiGatewayURL
		// [i] - field:0:required apiGatewayCredentials
		// [i] - field:0:required apiAllowUpdate
		// [i] - field:0:required apiMaturity
		// [i] - field:0:required apiGroup
		// [i] - field:0:required apiVersion
		// [i] - field:0:required defaultApp
		// [o] record:0:required apiServer
		// [o] - field:0:required apiGatewayURL
		// [o] - field:0:required apiUser
		// [o] - field:0:required apiPassword
		// [o] - field:0:required apiAllowUpdate
		// [o] - field:0:required apiMaturity
		// [o] - field:0:required apiGroup
		// [o] - field:0:required apiVersion
		// [o] - field:0:required defaultApp
		IDataCursor pc = pipeline.getCursor();
		IData api = IDataUtil.getIData(pc, "apiServer");
		
		// process
		
		if (api == null)
			api = IDataFactory.create();
		
		IDataCursor c = api.getCursor();
		
		if (IDataUtil.get(c, "apiGatewayURL") == null && System.getenv("api_gateway_url") != null)
			IDataUtil.put(c, "apiGatewayURL", System.getenv("api_gateway_url"));
		
		if (IDataUtil.get(c, "apiGatewayCredentials") == null 
				&& System.getenv("api_gateway_user") != null
					&& !System.getenv("api_gateway_user").isEmpty() ) {
			IDataUtil.put(c, "apiUser", System.getenv("api_gateway_user"));
		} else if (IDataUtil.get(c, "apiGatewayCredentials") != null) {
			
			String creds = IDataUtil.getString(c, "apiGatewayCredentials");
			
			if (creds.indexOf(":") == -1)
				throw new ServiceException("Invalid credentials, must combiner user and password separated by semi-colon ':'");
			
			int index = creds.indexOf(":");
			String part1 = creds.substring(0,index);
			String part2 = creds.substring(index+1);
		
			IDataUtil.put(c, "apiUser", part1);
			IDataUtil.put(c, "apiPassword", part2);
		
		} else if (IDataUtil.get(c, "apiUser") == null && System.getenv("api_gateway_url") != null) {
			IDataUtil.put(c, "apiUser", "Administrator");
		}
		
		if (IDataUtil.get(c, "apiPassword") == null && System.getenv("api_gateway_password") != null)
			IDataUtil.put(c, "apiPassword", System.getenv("api_gateway_password"));
		else if (IDataUtil.get(c, "apiPassword") == null && System.getenv("api_gateway_url") != null)
			IDataUtil.put(c, "apiPassword", "manage");
		
		if (IDataUtil.get(c, "apiAllowUpdate") == null && System.getenv("api_gateway_allow_update") != null)
			IDataUtil.put(c, "apiAllowUpdate", System.getenv("api_gateway_allow_update"));
		else if (IDataUtil.get(c, "apiAllowUpdate") == null && System.getenv("api_gateway_url") != null)
			IDataUtil.put(c, "apiAllowUpdate", "false");
		
		if (IDataUtil.get(c, "apiMaturity") == null && System.getenv("api_gateway_default_maturity") != null)
			IDataUtil.put(c, "apiMaturity", System.getenv("api_gateway_default_maturity"));
		
		if (IDataUtil.get(c, "apiGroup") == null && System.getenv("api_gateway_default_group") != null)
			IDataUtil.put(c, "apiGroup", System.getenv("api_gateway_default_group"));
		
		if (IDataUtil.get(c, "apiVersion") == null && System.getenv("api_gateway_default_version") != null)
			IDataUtil.put(c, "apiVersion", System.getenv("api_gateway_default_version"));
		else 
			IDataUtil.put(c, "apiVersion", IDataUtil.get(c, "apiVersion"));
		
		if (IDataUtil.get(c, "defaultApp") == null && System.getenv("api_gateway_default_app") != null)
			IDataUtil.put(c, "defaultApp", System.getenv("api_gateway_default_app"));
		
		c.destroy();
		
		// pipeline out 
		
		IDataUtil.put(pc, "apiServer", api);
		pc.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void flattenStringList (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(flattenStringList)>> ---
		// @sigtype java 3.5
		// [i] field:1:required list
		// [i] field:0:optional postfix
		// [i] field:0:optional format {"false","true"}
		// [o] field:0:required string
		// pipeline in
		
		IDataCursor pipelineCursor = pipeline.getCursor();
		String[] list = IDataUtil.getStringArray(pipelineCursor, "list");
		String format = IDataUtil.getString(pipelineCursor, "format");
		String postfix = IDataUtil.getString(pipelineCursor, "postfix");
		
		pipelineCursor.destroy();
		
		// process
		
		String string = "";
		
		if (format != null)
			string += "[";
		
		if (list != null) {
			for (String e : list) {
				
				if (postfix != null) {
					e += ":" + postfix;
				}
				
				if (format != null)
					string += "\"" + e + "\",";
				else
					string += e + ",";
			}
		}
		
		if (string != null && string.endsWith(","))
			string = string.substring(0, string.length()-1);
		
		if (format != null)
			string += "]";
		
		// pipeline out
		
		IDataUtil.put(pipelineCursor, "string", string);
		pipelineCursor.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void getAPIs (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getAPIs)>> ---
		// @sigtype java 3.5
		// [o] record:1:required apis
		// [o] - field:0:required name
		// [o] - field:0:required basePath
		// [o] - field:0:required swagger
		// pipeline  in
		
		IDataCursor pipelineCursor = pipeline.getCursor();
			
		// process
		
		APIInfo[] apis = PackageIntrospector.defaultInstance("./packages", false).apis();
		
		List<IData> out = new ArrayList<IData>();
		
		for (APIInfo api : apis) {
			
			if (!api.getName().equals("TestRunner") && !api.getName().equals("tools")) {
				
				IData i = IDataFactory.create();
				IDataCursor c = i.getCursor();
				IDataUtil.put(c, "name", api.getName());
				IDataUtil.put(c, "basePath", api.getBasePath());
				IDataUtil.put(c, "swagger", api.swaggerEndpoint());
				IDataUtil.put(c, "package", api.getPackageName());
		
				c.destroy();
				out.add(i);
			}
			
		}
		
		// pipeline out
		
		IDataCursor c = pipeline.getCursor();
		IDataUtil.put(c, "apis", out.toArray(new IData[out.size()]));
			
		// --- <<IS-END>> ---

                
	}



	public static final void getLastRollbackId (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getLastRollbackId)>> ---
		// @sigtype java 3.5
		// [i] record:1:required rollback
		// [o] field:0:required rollbackId
		// pipeline in
		
		IDataCursor pipelineCursor = pipeline.getCursor();
		IData[]	rollback = IDataUtil.getIDataArray(pipelineCursor, "rollback");
			
		// process
		
		String rollbackId = null;
		
		if ( rollback != null && rollback.length > 0) {
			
			IData lr = rollback[rollback.length-1];
			
			IDataCursor c = lr.getCursor();
			rollbackId = IDataUtil.getString(c, "id");
			c.destroy();
		}
		
		// pipeline out
		
		IDataUtil.put(pipelineCursor, "rollbackId", rollbackId);
		pipelineCursor.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void getLocalVersion (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getLocalVersion)>> ---
		// @sigtype java 3.5
		// [i] field:0:optional apiVersion
		// [o] field:0:required localVersion
		IDataCursor c = pipeline.getCursor();
		String v = IDataUtil.getString(c, "apiVersion");
		
		String lv = "1.0";
		
		if (v == null || v.length() == 0) {
			v = System.getenv("image_version");
			
			if (v != null) {
				if (v.contains(".")) {
					int dot = v.indexOf(".");
					String major = v.substring(0, dot);
					String minor = v.substring(dot+1);
					
					if (minor.contains(".")) {
						// ignore patches
						
						minor = minor.substring(0, minor.indexOf("."));
					}
					
					lv = major + "." + minor;
				} else {
					lv = v;
				}
			}
		} else {
			lv = v;
		}
		
		IDataUtil.put(c, "localVersion", lv);
		c.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void getServerEndPoint (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getServerEndPoint)>> ---
		// @sigtype java 3.5
		// [o] field:0:required httpPrefix
		// [o] field:0:required server
		// [o] field:0:optional primaryPort
		// [o] field:0:required user
		// [o] field:0:required password
		IDataCursor c = pipeline.getCursor();
		
		if (System.getenv("api_server_url") != null && System.getenv("api_server_url").length() > 0) {
			String url = System.getenv("api_server_url");
			String server = null;
			String port = null;
			String prefix = "http://";
			
			if (url.startsWith("http")) {
				if (url.charAt(4) == 's') {
					server = url.substring(8);
					prefix = "https://";
				} else {
					server = url.substring(7);
					prefix = "http://";
				}
			} else {
				prefix = "http://";
				server = url;
			}
			
			if (server.contains(":")) {
				int index = server.indexOf(":");
				port = server.substring(index+1);
				server = server.substring(0, index);
			}
			
			IDataUtil.put(c, "httpPrefix", prefix);
			IDataUtil.put(c, "server", server);
			
			if (port != null)
				IDataUtil.put(c, "primaryPort", port);
			
		} else {
			IDataUtil.put(c, "server", ServerAPI.getServerName());
		
			IDataUtil.put(c, "httpPrefix", "http://");
			
			if (ServerAPI.getCurrentPort() == -1)
				IDataUtil.put(c, "primaryPort", "5555");
			else
				IDataUtil.put(c, "primaryPort", "" + ServerAPI.getCurrentPort());
		}
		
		if (System.getenv("api_server_user") != null) {
			IDataUtil.put(c,  "user", System.getenv("api_server_user"));
		} else {
			IDataUtil.put(c,  "user", "Administrator");
		}
		
		if (System.getenv("api_server_password") != null) {
			IDataUtil.put(c,  "password", System.getenv("api_server_password"));
		} else {
			IDataUtil.put(c,  "password", "manage");
		}
		
		c.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void makeStringList (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(makeStringList)>> ---
		// @sigtype java 3.5
		// [i] field:0:required in
		// [o] field:0:required list
		// pipeline in
		
		IDataCursor pipelineCursor = pipeline.getCursor();
		String in = IDataUtil.getString(pipelineCursor, "in");
		
		// process
			
		List<String> list = new ArrayList<String>();
		
		if (in != null) {
			if (in.startsWith("[")) {
				in = in.substring(1, in.length()-1);
			}
			
			StringTokenizer tk = new StringTokenizer(in, ",");
			
			while (tk.hasMoreTokens()) {
				String str = tk.nextToken();
			
				if (str.startsWith("\"") || str.startsWith("\'"))
					list.add(str.substring(1, str.length()-1));
				else
					list.add(str);
			}
		}
		// pipeline out
		
		IDataUtil.put(pipelineCursor, "list", list.toArray(new String[list.size()]));
		pipelineCursor.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void replaceUrlAddress (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(replaceUrlAddress)>> ---
		// @sigtype java 3.5
		// [i] field:0:required url
		// [i] field:0:required newAddress
		// [o] field:0:required url
		// pipeline out 
		
		IDataCursor pipelineCursor = pipeline.getCursor();
		String url = IDataUtil.getString( pipelineCursor, "url");
		String newAddress = IDataUtil.getString( pipelineCursor, "newAddress");
		pipelineCursor.destroy();
		
		if (newAddress.contains(":")) {
			newAddress = newAddress.substring(0, newAddress.indexOf(":"));
		}
		
		int start = url.indexOf("//");
		int end = url.substring(start).indexOf(":") + start;
		
		if (end == -1 || end < start) {
			end = url.substring(start+2).indexOf("/") + start + 2;
		}
		
		String out = url.substring(0, start+2) + newAddress + url.substring(end);
		
		// pipeline out
		
		IDataUtil.put(pipelineCursor, "url", out);
		pipelineCursor.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void runOnServerReady (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(runOnServerReady)>> ---
		// @sigtype java 3.5
		// [i] field:0:required service
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		String	service = IDataUtil.getString(pipelineCursor, "service");
		pipelineCursor.destroy();
		
		
		new ServerStartupNotifier().registerForStartup(service, pipeline);
			
		// --- <<IS-END>> ---

                
	}



	public static final void sleepRandom (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(sleepRandom)>> ---
		// @sigtype java 3.5
		// [i] field:0:optional maxSecs
		IDataCursor c = pipeline.getCursor();
		String maxSecsStr = IDataUtil.getString(c, "maxSecs");
		c.destroy();
		
		int maxWait = 1000;
		
		try { maxWait = Integer.parseInt(maxSecsStr); } catch(Exception e) { } // do nothing
		
		long wait = (long) (Math.random() * maxWait * 1000);
		
		System.out.println("Pausing " + wait + " milliseconds....");
		
		try {
			Thread.sleep(wait);
		} catch (InterruptedException e) {
			// don't care
		}
			
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	
	Map<String, String> xref = new HashMap<String, String>();
	// --- <<IS-END-SHARED>> ---
}

