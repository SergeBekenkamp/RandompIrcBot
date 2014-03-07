package bot.images;

import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;
import org.jibble.pircbot.PircBot;

public class Bot extends PircBot {

	public Bot(String name, String server) {
		connect(name, server, new String[0]);
	}

	public Bot(String name, String server, String[] channels) {
		connect(name, server, channels);
	}

	public boolean connect(String name, String server, String[] channels) {
		this.setVerbose(true);
		this.setName(name);

		try {
			this.connect(server);
		} catch (NickAlreadyInUseException e) {
			// TODO Auto-generated catch block
			this.setName(name + "|2");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IrcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String t : channels) {
			this.joinChannel(t);
		}

		return true;
	}

	public void onMessage(String channel, String sender, String login, String hostname, String message) {
		switch (message.toLowerCase()) {
			case ".!help":
				help(channel);
				break;
			case ".!latest":
				this.sendMessage(channel, "Newest submission on the subreddit is: " + Main.latestSubmission);
				
			default:
				break;
		}

	}

	public void help(String channel) {
		this.sendMessage(channel, "This bot currently does nothing");
	}
	

}
