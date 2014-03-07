package bot.images;

import java.util.ArrayList;
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
	
	public static void main(String[] args0){
		
		
		List<String> channels = new ArrayList<String>();
		channels.add("#TestDocPleaseIgnore");
		Bot b = new Bot("HateMeh|Bot", "irc.esper.net", channels.toArray(new String[0]));
		
		Timer timer = new Timer();
		timer.schedule(new LatestSubmission(b), 0, 5000);
		
	}
	
}


