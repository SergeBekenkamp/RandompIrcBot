package bot.images;

import java.util.List;
import java.util.TimerTask;

import com.omrlnr.jreddit.Submission;
import com.omrlnr.jreddit.Submissions;
import com.omrlnr.jreddit.User;

public class LatestSubmission extends TimerTask {
		String username     = "";
        String password     = "";
        String subreddit    = "";
        
	@Override
	public void run() {
        User user = new User(username, password);
        try {
			user.connect();
	        List<Submission> submissions = Submissions.getSubmissions(subreddit,Submissions.NEW,Submissions.NEW, user);
	        if(Main.latestSubmission != submissions.get(0).getTitle()){
	        	//doSomething();
	        }
	        Main.latestSubmission = submissions.get(0).getTitle();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
