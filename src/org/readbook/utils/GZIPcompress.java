package org.readbook.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class GZIPcompress {

	public static byte[] compress(String data) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(out);
		gzip.write(data.getBytes("UTF-8"));
		gzip.close();
		return out.toByteArray();
	}

}
