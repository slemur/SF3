package net.aurora.loader.page;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author Rick van Biljouw
 *         Date: 19-11-11
 *         Time: 20:18
 */
public class Server {
    private String url;
    private Language language;

    public Server(String url, Language language) {
        this.url = url;
        this.language = language;
    }
    /**
     * Returns the language this server uses (english, german, french or portuguese
     * @return language
     */
    public Language getLanguage() {
        return language;
    }

    public URL getCodeBase() throws MalformedURLException {
        return new URL(this.url.substring(0, this.url.indexOf(".com") + 5));
    }

    public URL getDocumentBase() throws MalformedURLException {
        return new URL(this.url.substring(0, this.url.indexOf(".com") + 5));
    }

	public HashMap<String, String> getParameters()
			throws IOException {
		URL url = new URL(this.url);
		URLConnection uc = url.openConnection();
		uc.addRequestProperty(
				"Accept",
				"text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
		uc.addRequestProperty("Accept-Charset",
				"ISO-8859-1,utf-8;q=0.7,*;q=0.7");
		uc.addRequestProperty("Accept-Encoding", "gzip,deflate");
		uc.addRequestProperty("Accept-Language", "en-gb,en;q=0.5");
		uc.addRequestProperty("Connection", "keep-alive");
		uc.addRequestProperty("Host", url.getHost());
		uc.addRequestProperty("Keep-Alive", "300");
		uc.addRequestProperty(
				"User-Agent",
				"Mozilla/4.0 (" + System.getProperty("os.name") + " "
						+ System.getProperty("os.version") + ") Java/"
						+ System.getProperty("java.version"));
		DataInputStream di = new DataInputStream(uc.getInputStream());
		byte[] buffer = new byte[uc.getContentLength()];
		di.readFully(buffer);
		di.close();
		final String HTML = new String(buffer);
		HashMap<String, String> properties = new HashMap<String, String>();
		try {
            if(HTML.contains("archive")) {
                System.out.println(HTML);
				properties.put("archive", HTML.substring(HTML.indexOf("archive=") + 8, HTML.indexOf(".jar") + 4));
            }

            if(HTML.contains("code")) {
				properties.put("code", HTML.substring(HTML.indexOf("code=") + 5, HTML.indexOf(".class") + 6));
			}
		} catch (PatternSyntaxException ex) {
			ex.printStackTrace();
		}
		try {
			Pattern regex = Pattern.compile(
					"<param name=([^\\s]+)\\s+value=([^>]*)>",
					Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
			Matcher regexMatcher = regex.matcher(HTML.replaceAll("\"", ""));
			while (regexMatcher.find()) {
				if (properties.get(regexMatcher.group(1)) == null) {
					properties
							.put(regexMatcher.group(1), regexMatcher.group(2));
                    System.out.println(regexMatcher.group(1) + ", "+ regexMatcher.group(2));
				}
			}
		} catch (PatternSyntaxException ex) {
			ex.printStackTrace();
		}
		return properties;
	}

    public static enum Language {
        ENGLISH (0),
        GERMAN (1),
        FRENCH(2),
	    PORTUGUESE(3);

        private final int code;

        Language(int code) {
        	this.code = code;
        }

        public int getCode() {
        	return code;
        }

        public String toString() {
            return (getCode() >= 1 ? "l=" + getCode() + "/" : "");
        }
    }
}
