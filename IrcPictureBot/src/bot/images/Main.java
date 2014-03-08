package bot.images;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.omrlnr.jreddit.Comment;
import com.omrlnr.jreddit.Comments;
import com.omrlnr.jreddit.Submission;
import com.omrlnr.jreddit.Submissions;
import com.omrlnr.jreddit.User;


public class Main {
	public static String latestSubmission = "";
	public static boolean isServerUp = true;
	public static String prefix = ".!";
	public static HashMap<String, String> prefixUser = new HashMap<String, String>();
	
	public static void main(String[] args0){
		isServerUp = new ServerStatusCheck("tppi.testdocpleaseignore.com", 25565).isServerUp();
		
		List<String> channels = new ArrayList<String>();
		channels.add("#TestDocPleaseIgnore");
		Bot b = new Bot("HateMeh|Bot", "irc.esper.net", channels.toArray(new String[0]));
		
		Timer timer = new Timer();
		timer.schedule(new LatestSubmission(b), 0, 60000);
		timer.schedule(new ServerStatusCheck("tppi.testdocpleaseignore.com", 25565), 0 , 60000);

	}
	
}


