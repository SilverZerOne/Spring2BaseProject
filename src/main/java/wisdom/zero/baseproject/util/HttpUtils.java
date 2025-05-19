package wisdom.zero.baseproject.util;

import org.apache.http.cookie.MalformedCookieException;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class HttpUtils {

	public static final String HEADERS_COUNTRY = "HTTP_CF_IPCOUNTRY";
	public static final String HEADERS_IP = "X-Remote-Ip";
	public static final String HEADERS_USER_AGENT = "User-Agent";
	public static final String COOKIE_WZS = "wZs";

	public static String getIp(HttpServletRequest request) {
		
		String inputUrl = "";
    	if(request.getHeader(HEADERS_IP) != null && !request.getHeader(HEADERS_IP).isEmpty()) {
    		inputUrl = request.getHeader(HEADERS_IP);
    	} else {
    		inputUrl = request.getRemoteAddr();
    	}
    	
    	return inputUrl;
   	
	}
	
	/**
     * Método que devuelve el valor de la cookie wZs a partir de una petición.
     *
     * @param request la petición de la que se quieren obtener las cookies
     * @return el valor de la cookie wZs
     * @throws UnsupportedEncodingException the unsupported encoding exception
     * @throws MalformedCookieException     en caso de que la cookie no sea
     *                                      encontrada
     */
    public static String getWzsCookie(HttpServletRequest request)
            throws UnsupportedEncodingException, MalformedCookieException {
        String wZs = null;
        if(request.getCookies() != null) {
        	for (Cookie cookie : request.getCookies()) {
        		if (COOKIE_WZS.equals(cookie.getName())) {
        			wZs = URLEncoder.encode(cookie.getValue(), StandardCharsets.UTF_8.toString());
        			break;
        		}
        	}
        }
        if (wZs == null) {
            throw new MalformedCookieException("Cookie with the name wZs is missing");
        }
        return wZs;
    }
    
    public static String getUserAgent(HttpServletRequest request) {
		return request.getHeader(HEADERS_USER_AGENT);
	}
    
    public static String getCountry(HttpServletRequest request) {
    		return request.getHeader(HEADERS_COUNTRY);
	}
	
}