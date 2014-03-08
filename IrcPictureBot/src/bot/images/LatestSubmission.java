package bot.images;

import java.util.List;
import java.util.TimerTask;

import com.omrlnr.jreddit.Submission;
import com.omrlnr.jreddit.Submissions;
import com.omrlnr.jreddit.User;

public class LatestSubmission extends TimerTask {
		String username     = "swordplay";
        String password     = "egres899";
        String subreddit    = "TestDocPleaseIgnore";
        Bot bot = null;
    public LatestSubmission(Bot bot){
    	this.bot = bot;
    }
    
	@Override
	public void run() {
		System.out.println("Checking submissions!");
        User user = new User(username, password);
        try {
			user.connect();
	        List<Submission> submissions = Submissions.getSubmissions(subreddit,Submissions.NEW,Submissions.NEW, user);
	        if(!Main.latestSubmission.equals(submissions.get(0).getTitle()) && Main.latestSubmission != ""){
	        	bot.sendMessageToChannels("[Reddit] By: " + submissions.get(0).getAuthor() + " " + submissions.get(0).getTitle());
	        }
	        Main.latestSubmission = submissions.get(0).getTitle();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
