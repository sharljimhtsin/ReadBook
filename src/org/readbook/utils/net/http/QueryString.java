package org.readbook.utils.net.http;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public final class QueryString {
	
	private QueryString(){
		
	}	
	public static String build(String url, Map<String, String> params)
			throws UnsupportedEncodingException {
		StringBuffer buffer = new StringBuffer();
		buffer.append(url);
		if(params!=null){
			buffer.append("?");
			for(Map.Entry<String, String> e : params.entrySet()) {
				buffer.append(e.getKey());
				buffer.append("=");
				buffer.append(java.net.URLEncoder.encode(e.getValue(), "utf-8") );
				buffer.append("&");
			}
			buffer.setLength(buffer.length() - 1);
		}
		return buffer.toString();
	}

}
