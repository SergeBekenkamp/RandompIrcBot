package bot.images;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.TimerTask;

import javax.net.SocketFactory;

public class ServerStatusCheck extends TimerTask {
	
	private String url = "127.0.0.1";
	private int port = 25565;
	
	public ServerStatusCheck(String url, int port){
		this.port = port;
		this.url = url;
	}
	
	@Override
	public void run() {
		System.out.println("Checking if server is up!");
		Main.isServerUp = isServerUp();
	}
	
	public boolean isServerUp() {
		boolean open = true;
		try {
			Socket socket = SocketFactory.getDefault().createSocket();
			try {
				socket.setSoTimeout(5000);
				socket.connect(new InetSocketAddress(url, port));
				socket.close();
			} catch (ConnectException e) {
				open = false;
				return false;
			}
		} catch (IOException e) {

		}
		return open;

	}

}


