package org.readbook.utils.net.http;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.readbook.utils.GZIPcompress;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;

/**
 * @author Administrator
 * 
 */
public class HttpHelper {
	private Set<String> cookies = new HashSet<String>();
	private HttpClient httpClient;
	private static final int TIME_OUT = 30 * 1000;
	private static HttpHelper instance = null;
	private Proxy mProxy = null;

	public static HttpHelper getInstance() {
		if (instance == null) {
			synchronized (HttpHelper.class) {
				instance = new HttpHelper();
			}
		}
		return instance;
	}

	public HttpHelper() {
		this.initHttpClient();
	}

	public void setCookie(HttpUriRequest request) {
		/*
		 * synchronized (this) { for (String v : this.cookies) {
		 * System.err.println("Set Cookie: " + v); request.addHeader("Cookie",
		 * v); } }
		 */

	}

	public void getCookie(HttpResponse response) {
		/*
		 * 
		 * 
		 * synchronized (this) { for (Header h :
		 * response.getHeaders("Set-Cookie")) { String value =
		 * h.getValue().split(";", 2)[0]; if (value.startsWith("JSESSIONID")) {
		 * this.cookies.clear(); } System.err.println(h.getValue().split(";",
		 * 2)[0]); this.cookies.add(h.getValue().split(";", 2)[0]); } }
		 */
	}

	public String getHost() {
		return "http://file.peng.me";
	}

	public String httpExecute(HttpUriRequest request)
			throws ClientProtocolException, IOException {
		// this.setCookie(request);
		HttpResponse response = this.httpClient.execute(request);
		/*
		 * this.getCookie(response); while
		 * (response.getStatusLine().getStatusCode() == 401) {
		 * this.getCookie(response); response =
		 * this.httpClient.execute(request); } this.getCookie(response);
		 */
		// response.get
		HttpEntity entity = response.getEntity();
		if (entity == null)
			return null;
		// LogUtil.logD(Constants.Tag.TAG,"content Length = "+
		// entity.getContentLength());
		// InputStream is = entity.getContent();
		// return EntityUtils.toString(entity);
		return EntityUtils.toString(entity, HTTP.UTF_8);
		// ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// try {
		// // GZIPInputStream gInputStream = new
		// GZIPInputStream(entity.getContent());
		// InputStream inputStream = entity.getContent();
		// byte[] by = new byte[1];
		//
		// int len = 0;
		// while ((len = inputStream.read(by)) != -1) {
		// baos.write(by, 0, len);
		// }
		// byte[] origin = baos.toByteArray();
		// //unzip
		//
		// return new String(origin);
		// } catch (IOException e) {
		// e.printStackTrace();
		// byte[] origin = baos.toByteArray();
		// //unzip
		// String sr = new String(origin, HTTP.UTF_8);
		// System.out.println(sr + "   len = "+origin.length);
		// }
		// return null;
	}

	private String getURL(String url) {
		if (!url.startsWith("http://")) {
			url = this.getHost() + url;
		}
		return url;
	}

	public String httpGetByAuth(String url) throws ClientProtocolException,
			IOException {

		String mobile = "";
		String token = "";
		String headerStr = mobile + ":" + token;
		String header = Base64.encodeToString(headerStr.getBytes(),
				Base64.URL_SAFE | Base64.NO_WRAP);
		HttpGet httpGet = new HttpGet(this.getURL(url));
		// httpGet.addHeader("content-type","application/x-www-form-urlencoded");
		httpGet.addHeader("Authorization", "Basic " + header);
		return this.httpExecute(httpGet);
	}
	
	public byte[] download(String url) throws ClientProtocolException, IOException {
		HttpGet httpGet = new HttpGet(this.getURL(url));
		HttpResponse response = this.httpClient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		if (entity == null)
			return null;
		InputStream is = entity.getContent();
		byte[] resultBytes = new byte[(int)entity.getContentLength()];
		byte[] tempByte = new byte[resultBytes.length];
		int tempLen = 0;
		int length = 0;
		int index = 0;
		while((length = is.read(tempByte)) != -1) {
			tempLen = tempLen + length;
			System.arraycopy(tempByte, 0, resultBytes, index, length);
			index += length;
		}
		is.close();
		//如果没有全部读完，则返回网络连接异常
//		if(tempLen != resultBytes.length){
//			int a = 1 / 0;
//		}
		return resultBytes;
	}

	public String httpGet(String url) throws ClientProtocolException,
			IOException {
		return this.httpExecute(new HttpGet(this.getURL(url)));
	}

	public String httpGet(String url, Map<String, String> params)
			throws ClientProtocolException, IOException {
		if (url.startsWith("http://"))
			return this.httpGet(QueryString.build(url, params));
		else
			return this.httpGet(QueryString.build(this.getURL(url), params));
	}

	public byte[] httpGetByte(String url) throws ClientProtocolException,
			IOException {
		HttpGet get;
		if (!url.startsWith("http://"))
			get = new HttpGet(this.getURL(url));
		else
			get = new HttpGet(url);

		HttpContext context = new BasicHttpContext();
		HttpResponse response = this.httpClient.execute(get, context);
		HttpEntity entity = response.getEntity();
		return EntityUtils.toByteArray(entity);
	}

	public String httpPostJson(String url, String json)
			throws ClientProtocolException, IOException {
		HttpPost post = new HttpPost(url);
		post.addHeader("Host", "ios-proxy.lietou.com");
		post.addHeader("Content-Type", "application/json");
		post.addHeader("Content-Encoding", "gzip");
		post.addHeader("Connection", "close");
		post.addHeader("Accept", "application/json");
		post.addHeader("User-Agent",
				"猎聘秘书 2.4.1 (iPhone; iPhone OS 7.0.3; zh_CN)");
		// post.removeHeaders("Content-Length");
		post.addHeader("Accept-Encoding", "gzip");
		byte[] bzig = GZIPcompress.compress(json);
		InputStreamEntity entity = new InputStreamEntity(
				new ByteArrayInputStream(bzig), bzig.length);
		// StringEntity entity = new StringEntity(GZIPcompress.compress(json));
		entity.setChunked(true);
		// post.adddHeader("Content-Length", ""+);
		post.setEntity(entity);
		// GZIPOutputStream gzipOutputStream = new
		// GzipDecompressingEntity(entity);
		return this.httpExecute(post);

	}

	public String httpPost(String url, Map<String, ?> map)
			throws ParseException, IOException {
		HttpPost post = new HttpPost(this.getURL(url));
		post.addHeader("content-type", "application/x-www-form-urlencoded");
		// post.addHeader("content-type","application/json");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for (String key : map.keySet()) {
			Object p = map.get(key);
			if (p != null) {
				if (p instanceof String) {
					nvps.add(new BasicNameValuePair(key, p.toString()));
				} else {
					@SuppressWarnings("unchecked")
					List<String> l = (List<String>) p;
					for (String v : l) {
						nvps.add(new BasicNameValuePair(key, v));
					}
				}
			}
		}
		post.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		return this.httpExecute(post);
	}

	/**
	 * header == base64 username:password
	 */
	public String httpPostByAuth(String url, Map<String, ?> map)
			throws ParseException, IOException {

		String mobile = "";
		String token = "";
		String headerStr = mobile + ":" + token;
		String header = Base64.encodeToString(headerStr.getBytes(),
				Base64.URL_SAFE | Base64.NO_WRAP);
		HttpPost post = new HttpPost(this.getURL(url));
		post.addHeader("content-type", "application/x-www-form-urlencoded");
		post.addHeader("Authorization", "Basic " + header);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for (String key : map.keySet()) {
			Object p = map.get(key);
			if (p != null) {
				if (p instanceof String) {
					nvps.add(new BasicNameValuePair(key, p.toString()));
				} else {
					@SuppressWarnings("unchecked")
					List<String> l = (List<String>) p;
					for (String v : l) {
						nvps.add(new BasicNameValuePair(key, v));
					}
				}
			}
		}
		post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		return this.httpExecute(post);
	}

	// os/ios osversion/6.0 version/1.2 device/iphone4 imei/357710043770232
	// network/wifi operator/460_00 latitude/31.216153 longitude/121.527799
	// resolution/960*640
	private String getUserAgent() {
		StringBuffer sb = new StringBuffer();
		// sb.append("os/").append(Constants.UserAgent.os).append(" ");
		// sb.append("osversion/").append(Constants.UserAgent.osversion)
		// .append(" ");
		// sb.append("version/").append(Constants.UserAgent.version).append(" ");
		// sb.append("device/").append(Constants.UserAgent.device).append(" ");
		// sb.append("deviceid/").append(Constants.UserAgent.deviceid).append(" ");
		// sb.append("imei/").append(Constants.UserAgent.imei).append(" ");
		// sb.append("network/").append(Constants.UserAgent.network).append(" ");
		// sb.append("operator/").append(Constants.UserAgent.operator).append(" ");
		// sb.append("latitude/").append(Constants.UserAgent.latitude).append(" ");
		// sb.append("longitude/").append(Constants.UserAgent.longitude)
		// .append(" ");
		// sb.append("resolution/").append(Constants.UserAgent.resolution)
		// .append(" ");
		return sb.toString();
	}

	public void initHttpClient() {
		HttpParams params = new BasicHttpParams();
		ConnManagerParams.setMaxTotalConnections(params, 15);
		ConnManagerParams.setTimeout(params, 10000);
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		List<BasicHeader> headers = new ArrayList<BasicHeader>();
		headers.add(new BasicHeader("Accept", "*/*"));
		headers.add(new BasicHeader("Accept-Encoding", "gzip"));

		// headers.add(new BasicHeader("Content-type",
		// "application/x-www-form-urlencoded"));
		params.setParameter(ClientPNames.DEFAULT_HEADERS, headers);

		// HttpConnectionParams.setSocketBufferSize(params,
		// 64 * 1024);
		HttpConnectionParams.setSoTimeout(params, TIME_OUT);
		HttpConnectionParams.setConnectionTimeout(params, TIME_OUT);
		HttpConnectionParams.setStaleCheckingEnabled(params, true);

		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", PlainSocketFactory
				.getSocketFactory(), 80));
		ClientConnectionManager cm = new ThreadSafeClientConnManager(params,
				schemeRegistry);
		this.httpClient = new DefaultHttpClient(cm, params);
		((AbstractHttpClient) this.httpClient)
				.addResponseInterceptor(new HttpResponseInterceptor() {
					public void process(final HttpResponse response,
							final HttpContext context) throws HttpException,
							IOException {
						HttpEntity entity = response.getEntity();
						Header encheader = entity.getContentEncoding();
						if (encheader != null) {
							HeaderElement[] codecs = encheader.getElements();
							for (int i = 0; i < codecs.length; i++) {
								if (codecs[i].getName()
										.equalsIgnoreCase("gzip")) {
									response.setEntity(new GzipDecompressingEntity(
											entity));
									break;
								}
							}
						}
					}
				});

	}

	static class GzipDecompressingEntity extends HttpEntityWrapper {
		public GzipDecompressingEntity(final HttpEntity entity) {
			super(entity);
		}

		@Override
		public InputStream getContent() throws IOException,
				IllegalStateException {
			// the wrapped entity's getContent() decides about repeatability
			InputStream wrappedin = wrappedEntity.getContent();
			// FileOutputStream fos = new
			// FileOutputStream("/sdcard/testlog.txt");
			// byte[] buffer = new byte[1024];
			// int len = 0;
			// while ((len = wrappedin.read(buffer)) != -1) {
			// fos.write(buffer, 0, len);
			// }
			// fos.flush();
			return new GZIPInputStream(wrappedin);
		}

		@Override
		public long getContentLength() {
			// length of ungzipped content is not known
			return -1;
		}
	}

	public String httpMultipartUploadByAuth(String u,
			Map<String, Object> params, Map<String, byte[]> datas,
			Context context) throws IOException {
		String boundary = "*****************************************";
		String newLine = "\r\n";
		// detectProxy();
		URL url = new URL(u);
		HttpURLConnection con = null;
		// String type = getNetworkType(context);
		// if (mProxy != null && type != null && (type.equals(APNNet.CMWAP) ||
		// type.equals(APNNet.GWAP_3) || type .equals(APNNet.UNIWAP))) {
		// con = (HttpURLConnection) url.openConnection(mProxy);
		// } else {
		con = (HttpURLConnection) url.openConnection();
		// }
		String mobile = "";
		String token = "";
		String headerStr = mobile + ":" + token;
		String header = Base64.encodeToString(headerStr.getBytes(),
				Base64.URL_SAFE | Base64.NO_WRAP);
		con.setRequestProperty("Authorization", "Basic " + header);
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false);
		con.setRequestMethod("POST");
		// con.setRequestProperty("Charset", "UTF-8");
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Content-Type", "multipart/form-data;boundary="
				+ boundary);

		for (String v : this.cookies) {
			con.addRequestProperty("Cookie",
					"rtime=0; ltime=" + System.currentTimeMillis() + "; " + v);
		}
		DataOutputStream dos = new DataOutputStream(con.getOutputStream());

		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			sb.append("--");
			sb.append(boundary);
			sb.append(newLine);
			sb.append("Content-Disposition: form-data; name=\"");
			sb.append(entry.getKey());
			sb.append("\"");
			sb.append(newLine);
			sb.append("Content-Type: text/plain; charset=");
			sb.append("UTF-8");
			sb.append(newLine);
			sb.append("Content-Transfer-Encoding: 8bit");
			sb.append(newLine);
			sb.append(newLine);
			sb.append(entry.getValue());
			sb.append(newLine);
			sb.append("--");
			sb.append(boundary);
			sb.append("--");
			sb.append(newLine);
		}
		dos.write(sb.toString().getBytes("UTF-8"));
		if (datas != null && datas.size() > 0) {
			for (Map.Entry<String, byte[]> entry : datas.entrySet()) {
				dos.writeBytes("--" + boundary + newLine);
				dos.writeBytes("Content-Type: image/jpeg");
				dos.writeBytes(newLine);
				dos.writeBytes("Content-Disposition: form-data; " + "name=\""
						+ entry.getKey() + "\";filename=\"" + entry.getKey()
						+ ".jpg\"" + newLine + newLine);
				byte[] b = entry.getValue();
				if (b != null) {
					dos.write(entry.getValue());
				}
				dos.writeBytes(newLine);
				dos.writeBytes("--" + boundary + "--" + newLine);
			}
		}
		dos.flush();
		ByteArrayOutputStream rd = new ByteArrayOutputStream();
		byte[] buffer2 = new byte[1024];
		for (int i = con.getInputStream().read(buffer2); i > -1; i = con
				.getInputStream().read(buffer2)) {
			rd.write(buffer2, 0, i);
		}
		dos.close();
		return rd.toString("UTF-8");
	}

	public String httpUpload(String url, Map<String, String> params,
			String filePath) {
		FileInputStream fis = null;
		DataOutputStream dos = null;
		ByteArrayOutputStream rd = null;
		try {
			String boundary = "*****************************************";
			String newLine = "\r\n";
			int bytesAvailable;
			int bufferSize;
			int maxBufferSize = 4096;
			int bytesRead;
			URL u = new URL(url);
			HttpURLConnection con = (HttpURLConnection) u.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setRequestMethod("POST");
			con.setRequestProperty("Charset", "UTF-8");
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Content-Type",
					"multipart/form-data;boundary=" + boundary);

			for (String v : this.cookies) {
				con.addRequestProperty("Cookie",
						"rtime=0; ltime=" + System.currentTimeMillis() + "; "
								+ v);
			}

			dos = new DataOutputStream(con.getOutputStream());
			// System.out.println("---filePath--httphelper---"+filePath);
			File f = new File(filePath);
			// System.out.println("----isFileNull----"+f);
			fis = new FileInputStream(f);
			dos.writeBytes("--" + boundary + newLine);
			dos.writeBytes("Content-Disposition: form-data; " + "name=\""
					+ "image" + "\";filename=\"" + f.getName() + ".jpg\""
					+ newLine + newLine);
			bytesAvailable = fis.available();
			bufferSize = Math.min(bytesAvailable, maxBufferSize);
			byte[] buffer = new byte[bufferSize];
			bytesRead = fis.read(buffer, 0, bufferSize);
			while (bytesRead > 0) {
				dos.write(buffer, 0, bufferSize);
				bytesAvailable = fis.available();
				bufferSize = Math.min(bytesAvailable, maxBufferSize);
				bytesRead = fis.read(buffer, 0, bufferSize);
			}
			dos.writeBytes(newLine);
			dos.writeBytes("--" + boundary + "--" + newLine);

			StringBuilder sb = new StringBuilder();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				sb.append("--");
				sb.append(boundary);
				sb.append(newLine);
				sb.append("Content-Disposition: form-data; name=\"");
				sb.append(entry.getKey());
				sb.append("\"");
				sb.append(newLine);
				sb.append("Content-Type: text/plain; charset=");
				sb.append("UTF-8");
				sb.append(newLine);
				sb.append("Content-Transfer-Encoding: 8bit");
				sb.append(newLine);
				sb.append(newLine);
				sb.append(entry.getValue());
				sb.append(newLine);
				sb.append("--");
				sb.append(boundary);
				sb.append("--");
				sb.append(newLine);
			}
			dos.write(sb.toString().getBytes("UTF-8"));
			dos.flush();
			rd = new ByteArrayOutputStream();
			byte[] buffer2 = new byte[1024];
			for (int i = con.getInputStream().read(buffer2); i > -1; i = con
					.getInputStream().read(buffer2)) {
				rd.write(buffer2, 0, i);
				rd.flush();
			}
			return rd.toString("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rd != null) {
				try {
					rd.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (dos != null) {
				try {
					dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public String httpUpload2(String u, Map<String, String> params,
			String filePath, Context context) throws IOException {
		String boundary = "*****************************************";
		String newLine = "\r\n";
		int bytesAvailable;
		int bufferSize;
		int maxBufferSize = 4096;
		int bytesRead;
		detectProxy();
		u = this.getHost() + u;
		URL url = new URL(u);
		HttpURLConnection con = null;
		String type = getNetworkType(context);
		// 检查是否需要设置代理
		if (mProxy != null
				&& type != null
				&& (type.equals(APNNet.CMWAP) || type.equals(APNNet.GWAP_3) || type
						.equals(APNNet.UNIWAP))) {
			con = (HttpURLConnection) url.openConnection(mProxy);
		} else {
			con = (HttpURLConnection) url.openConnection();
		}
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false);
		con.setRequestMethod("POST");
		con.setRequestProperty("Charset", "UTF-8");
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Content-Type", "multipart/form-data;boundary="
				+ boundary);

		for (String v : this.cookies) {
			con.addRequestProperty("Cookie",
					"rtime=0; ltime=" + System.currentTimeMillis() + "; " + v);
		}
		DataOutputStream dos = new DataOutputStream(con.getOutputStream());

		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			sb.append("--");
			sb.append(boundary);
			sb.append(newLine);
			sb.append("Content-Disposition: form-data; name=\"");
			sb.append(entry.getKey());
			sb.append("\"");
			sb.append(newLine);
			sb.append("Content-Type: text/plain; charset=");
			sb.append("UTF-8");
			sb.append(newLine);
			sb.append("Content-Transfer-Encoding: 8bit");
			sb.append(newLine);
			sb.append(newLine);
			sb.append(entry.getValue());
			sb.append(newLine);
			sb.append("--");
			sb.append(boundary);
			sb.append("--");
			sb.append(newLine);
		}
		dos.write(sb.toString().getBytes("UTF-8"));
		File f = new File(filePath);
		String name = "";
		if (f != null) {
			name = f.getName();
		}
		dos.writeBytes("--" + boundary + newLine);
		dos.writeBytes("Content-Disposition: form-data; " + "name=\"image"
				+ "\";filename=\"" + name + "\"" + newLine + newLine);
		if (f != null) {
			FileInputStream fis = new FileInputStream(f);
			bytesAvailable = fis.available();
			bufferSize = Math.min(bytesAvailable, maxBufferSize);
			byte[] buffer = new byte[bufferSize];
			bytesRead = fis.read(buffer, 0, bufferSize);
			while (bytesRead > 0) {
				dos.write(buffer, 0, bufferSize);
				bytesAvailable = fis.available();
				bufferSize = Math.min(bytesAvailable, maxBufferSize);
				bytesRead = fis.read(buffer, 0, bufferSize);
			}
			dos.writeBytes(newLine);
			dos.writeBytes("--" + boundary + "--" + newLine);
			fis.close();
		} else {
			dos.writeBytes(newLine);
			dos.writeBytes("--" + boundary + "--" + newLine);
		}

		dos.flush();
		// InputStream is = con.getInputStream();
		// byte[] resultBytes = new byte[con.getContentLength()];
		// byte[] tempByte = new byte[resultBytes.length];
		// int tempLen = 0;
		// int length = 0;
		// int index = 0;
		// // int tempPos = 0;
		// while((length = is.read(tempByte)) != -1) {
		// tempLen = tempLen + length;
		// System.arraycopy(tempByte, 0, resultBytes, index, length);
		// // tempPos = length;
		// index += length;
		// }
		// if(is != null){
		// is.close();
		// is = null;
		// }
		// return new String(resultBytes);
		ByteArrayOutputStream rd = new ByteArrayOutputStream();
		byte[] buffer2 = new byte[1024];
		for (int i = con.getInputStream().read(buffer2); i > -1; i = con
				.getInputStream().read(buffer2)) {
			rd.write(buffer2, 0, i);
		}
		dos.close();
		return rd.toString("UTF-8");
	}

	/**
	 * 获取手机默认代理
	 */
	public void detectProxy() {
		String proxyHost = android.net.Proxy.getDefaultHost();
		int port = android.net.Proxy.getDefaultPort();
		if (proxyHost != null) {
			final InetSocketAddress sa = new InetSocketAddress(proxyHost, port);
			mProxy = new Proxy(Proxy.Type.HTTP, sa);
		}
	}

	/**
	 * 获取手机的网络类型 注意：必须添加权限(<uses-permission
	 * android:name="android.permission.ACCESS_NETWORK_STATE"/>)
	 * 
	 * @return 网络类型 :Intenet、cmwap、cmnet、3gwap、3gnet、uniwap、uninet 等等
	 */
	public String getNetworkType(Context context) {
		String apn = null;
		// 通过context得到ConnectivityManager连接管理
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		// 通过ConnectivityManager得到NetworkInfo网络信息
		NetworkInfo info = manager.getActiveNetworkInfo();
		// 获取NetworkInfo中的apn信息
		if (info != null) {
			apn = info.getExtraInfo();
			if (apn == null) {
				apn = "取不到移动网络信息";
			}
		} else {
			apn = "取不到网络信息";
		}
		return apn;
	}

	/**
	 * APN接入点
	 * 
	 * @author Paulson.yan
	 * 
	 */
	public static class APNNet {
		/**
		 * 中国移动cmwap
		 */
		public static String CMWAP = "cmwap";
		/**
		 * 中国移动cmnet
		 */
		public static String CMNET = "cmnet";
		// 中国联通3GWAP设置 中国联通3G因特网设置 中国联通WAP设置 中国联通因特网设置
		// 3gwap 3gnet uniwap uninet
		/**
		 * 3G wap 中国联通3gwap APN
		 */
		public static String GWAP_3 = "3gwap";
		/**
		 * 3G net 中国联通3gnet APN
		 */
		public static String GNET_3 = "3gnet";
		/**
		 * uni wap 中国联通uni wap APN
		 */
		public static String UNIWAP = "uniwap";
		/**
		 * uni net 中国联通uni net APN
		 */
		public static String UNINET = "uninet";
	}

}
