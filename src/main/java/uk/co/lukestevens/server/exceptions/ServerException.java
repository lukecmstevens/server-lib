package uk.co.lukestevens.server.exceptions;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

/**
 * An exception class used to represent errors thrown from
 * API methods
 * 
 * @author luke.stevens
 */
public class ServerException extends IOException {

	private static final long serialVersionUID = 5690456456223971520L;
	
	/**
	 * Creates a standard error response:<br>
	 * This indicates there was something invalid but not unexpected
	 * about the request. This could be a missing or invalid field
	 * in the body, or query parameters. The server will respond with a
	 * 200 status code, and errors in the body
	 */
	public static ServerExceptionBuilder invalidRequest() {
		return new ServerExceptionBuilder(HttpURLConnection.HTTP_OK);
	}
	
	/**
	 * Creates a 400 bad request error:<br>
	 * The server cannot or will not process the request
	 * due to an apparent client error (e.g., malformed request
	 * syntax, size too large, invalid request message framing,
	 * or deceptive request routing).
	 */
	public static ServerExceptionBuilder badRequest() {
		return new ServerExceptionBuilder(HttpURLConnection.HTTP_BAD_REQUEST);
	}
	
	
	/**
	 * Creates a 401 Unauthorized error:<br>
	 * Similar to <i>403 Forbidden</i>, but specifically for
	 * use when authentication is required and has failed or
	 * has not yet been provided.
	 */
	public static ServerExceptionBuilder unauthorized() {
		return new ServerExceptionBuilder(HttpURLConnection.HTTP_UNAUTHORIZED);
	}
	
	
	/**
	 * Creates a 403 forbidden error:<br>
	 * The request was valid, but the server is refusing action.
	 * The user might not have the necessary permissions for a
	 * resource, or may need an account of some sort.
	 */
	public static ServerExceptionBuilder forbidden() {
		return new ServerExceptionBuilder(HttpURLConnection.HTTP_FORBIDDEN);
	}
	
	
	/**
	 * Creates a 404 not found error:<br>
	 * The origin server did not find a current representation
	 * for the target resource or is not willing to disclose that one exists.
	 */
	public static ServerExceptionBuilder notFound() {
		return new ServerExceptionBuilder(HttpURLConnection.HTTP_NOT_FOUND);
	}
	
	
	private final int httpCode;
	private final Map<String, String> errors;
	private final boolean shouldLog;
	
	// Constructor for client only error
	ServerException(int httpCode, Map<String, String> errors) {
		this.shouldLog = false;
		this.httpCode = httpCode;
		this.errors = errors;
	}
	
	// Constructor for client errors with server side logging
	ServerException(int httpCode, Map<String, String> errors, String logMessage) {
		super(logMessage);
		this.shouldLog = true;
		this.httpCode = httpCode;
		this.errors = errors;
	}

	/**
	 * @return The HTTP status code
	 */
	public int getHttpCode() {
		return httpCode;
	}

	/**
	 * @return A map of errors to be returned to the client
	 */
	public Map<String, String> getErrors() {
		return errors;
	}

	/**
	 * @return Whether a message should be logged by the server
	 */
	public boolean shouldLog() {
		return shouldLog;
	}
	
	

}
