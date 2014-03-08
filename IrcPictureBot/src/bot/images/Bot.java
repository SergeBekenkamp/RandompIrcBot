package bot.images;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;
import org.jibble.pircbot.PircBot;

public class Bot extends PircBot {

	private String[] channels = null;
	private HashMap<String, ArrayList<String>> history = new HashMap<String, ArrayList<String>>();

	public Bot(String name, String server) {
		connect(name, server, new String[0]);
	}

	public Bot(String name, String server, String[] channels) {
		this.channels = channels;
		connect(name, server, channels);
	}

	public boolean connect(String name, String server, String[] channels) {
		this.setVerbose(true);
		this.setName(name);
		this.setMessageDelay(200);
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
		joinChannels(channels);

		return true;
	}

	public void joinChannels(String[] channels) {

		for (String t : channels) {
			this.joinChannel(t);
			history.put(t, new ArrayList<String>());
		}
	}

	public void onMessage(String channel, String sender, String login, String hostname, String message) {
		history.get(channel).add(sender + " " + message);
		String message2 = message.split(" ")[0];
		if (message2.startsWith(Main.prefix)) {
			message2 = message2.substring(2, message2.length());
			switch (message2.toLowerCase()) {
				case "help":
					help(channel);
					break;
				case "latest":
					this.sendMessage(channel, "Newest submission on the subreddit is: " + Main.latestSubmission);
					break;
				case "up":
					sendServerIsUp(channel);
					break;
				case "history":
					sendHistory(sender, channel);
					break;
				default:
					break;
			}
		}
	}

	public void help(String channel) {
		this.sendMessage(channel, "This bot currently does nothing");
	}

	public void sendMessageToChannels(String message) {
		for (String t : channels) {
			this.sendMessage(t, message);
		}
	}

	public void sendServerIsUp(String channel) {
		if (Main.isServerUp) {
			this.sendMessage(channel, "Server seems to be up and or reachable");
		} else {
			this.sendMessage(channel, "Server is down :(");
		}
	}

	public void sendHistory(String user, String channel) {
		ArrayList<String> his = history.get(channel);
		for (int i = his.size() - 10; i < his.size(); i++) {
			if (i < 0) i = 0;
				this.sendMessage(user, his.get(i));
		}
	}

}
