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
		if (Main.prefixUser.get(sender) != null) {
			System.out.println(Main.prefixUser.get(sender));
			if (message.startsWith(Main.prefixUser.get(sender))) {
				message = message.substring(Main.prefixUser.get(sender).length());
				System.out.println(message);
				messageLogic(channel, sender, login, hostname, message.split(" "));
			}
		} else if (message.startsWith(Main.prefix)) {
			message = message.substring(Main.prefix.length());
			System.out.println(message);
			messageLogic(channel, sender, login, hostname, message.split(" "));
		} else {
			history.get(channel).add(sender + " " + message);
		}
	}

	public void messageLogic(String channel, String sender, String login, String hostname, String[] message) {
		for (String t : message) {
			System.out.println(t);
		}
		switch (message[0].toLowerCase()) {
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
			case "prefix":
				changePrefix(sender, message[1]);
				break;
			default:
				break;
		}
	}

	public void changePrefix(String sender, String prefix) {
		Main.prefixUser.remove(sender);
		Main.prefixUser.put(sender, prefix);
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
			if (i < 0)
				i = 0;
			this.sendMessage(user, his.get(i));
		}
	}

}
