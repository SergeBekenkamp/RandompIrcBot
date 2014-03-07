package bot.images;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import com.omrlnr.jreddit.Comment;
import com.omrlnr.jreddit.Comments;
import com.omrlnr.jreddit.Submission;
import com.omrlnr.jreddit.Submissions;
import com.omrlnr.jreddit.User;


public class Main {
	public static String latestSubmission = "";
	
	public static void main(String[] args0){
		
		LatestSubmission latest = new LatestSubmission();//getLatestSubmission();
		latest.run();
		List<String> channels = new ArrayList<String>();
		channels.add("#TestDocPleaseIgnore");
		Bot b = new Bot("HateMeh|Bot", "192.184.93.117", channels.toArray(new String[0]));
		
	}
	
}


