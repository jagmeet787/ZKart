package zkart.jsp;



import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class CreateProperties
{
	public CreateProperties()
	{

		Properties prop = new Properties();
		OutputStream output = null;

		try
		{
//			System.out.println(System.getProperty("user.dir"));
			output = new FileOutputStream("/home/satyam/Desktop/serverdir/config.properties");

			// set the properties value
			prop.setProperty("serverURL", "http://localhost:8080/");
			prop.setProperty("imageServerURL", "http://127.0.0.1:8087/");
			prop.setProperty("serverPath", "C:\\Users\\utkar\\Desktop\\img");


			// save properties to project root folder
			prop.store(output, null);

		}
		catch (IOException io)
		{
			io.printStackTrace();
		}
		finally
		{
			if (output != null)
			{
				try
				{
					output.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}

		}
	}
}
