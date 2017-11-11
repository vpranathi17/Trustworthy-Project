import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.ArrayList;

public class ServerOnClass {
	
	static ArrayList clients;
	
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, ClassNotFoundException
	{
		int numOfConnection = 0;
		InetAddress address = InetAddress.getLocalHost();
		System.out.println(address);
		ServerSocket server = new ServerSocket(5000);
		System.out.println("TCP server is waiting on 5000");
		
		clients = new ArrayList();
		
		while (true) {
			Socket connect = server.accept();
			System.out.println("Connection established");
			System.out.println( ++numOfConnection);
			clients.add(connect);
			//Server.main("Connection Established");
			
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
			keyGen.initialize(512);
			KeyPair serverKey = keyGen.generateKeyPair();
			// send server's public key to client
			ObjectOutputStream oos = new ObjectOutputStream(connect.getOutputStream());
			oos.writeObject(serverKey.getPublic());
			System.out.println(serverKey.getPublic());
			oos.flush();
			ObjectInputStream ois = new ObjectInputStream(connect.getInputStream());
			Object rmessage = ois.readObject();
			ois.toString();
			//Server.main((String)rmessage);
		}
	}
	
}
