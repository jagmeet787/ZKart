package zkart.jsp;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AccessProperties {

	private String imageServerURL;
	private String serverPath;
	private String serverURL;
	private String contextPath;
	private String restPath;

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public String getRestPath() {
		return restPath;
	}

	public void setRestPath(String restPath) {
		this.restPath = restPath;
	}

	public String getImageServerURL() {
		return imageServerURL;
	}

	public void setImageServerURL(String imageServerURL) {
		this.imageServerURL = imageServerURL;
	}

	public String getServerURL() {
		return serverURL;
	}

	public void setServerURL(String serverURL) {
		this.serverURL = serverURL;
	}

	public String getServerPath() {
		return serverPath;
	}

	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}

//	public static void main(String[] args)
	public AccessProperties() {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("/home/satyam/Desktop/serverdir/config.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
//			System.out.println(prop.getProperty("serverURL"));
//			System.out.println(prop.getProperty("imageServerURL"));
//			System.out.println(prop.getProperty("serverPath"));
			this.setServerURL(prop.getProperty("serverURL"));
			this.setImageServerURL(prop.getProperty("imageServerURL"));
			this.setServerPath(prop.getProperty("serverPath"));

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
