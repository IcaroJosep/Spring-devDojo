import java.net.InetAddress;
import java.net.UnknownHostException;

public class test {

	
	    public static void main(String[] args)  {        
	        System.out.println(System.getProperty("os.name"));
	        System.out.println(System.getProperty("device.name"));
	        try {
				System.out.println(InetAddress.getLocalHost().getHostName());
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	    }
	
}
