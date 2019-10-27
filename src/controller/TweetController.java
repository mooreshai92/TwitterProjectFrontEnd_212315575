package controller;

import java.io.*;
import java.io.IOException;

import java.net.URLEncoder;
import java.sql.Date;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import model.Tweet;
import model.TweetEntity;
import service.TweetEJB;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;




@ManagedBean(name="tweetcontroller")
@SessionScoped
public class TweetController {
	
	@EJB
	TweetEJB tweetservice;
	
	@ManagedProperty(value="#{tweet}")
	private Tweet tweet;
	
	static String consumerKeyStr = "xH21utMnN9KPdj3N5bZ8zBi5b";
	static String consumerSecretStr = "GtkZb5iAwhfejmdJgBKKpXY7oIjQ3HYsQsapyUh5F9jw18v3N6";
	static String accessTokenStr = "1187448088446803968-SCI3y058PicAi9HpPy1uJdtXW7DA9X";
	static String accessTokenSecretStr = "aRmWEYFa12VncRzsoZmZW7EVW8ARcZ78YKEYp2hlQEmQY";
	
	 
    public void start(String text) throws TwitterException, IOException {
 
 Twitter twitter = new TwitterFactory().getInstance();
 twitter.setOAuthConsumer(consumerKeyStr, consumerSecretStr);
 RequestToken requestToken = twitter.getOAuthRequestToken();
 System.out.println("Authorization URL: \n"
  + requestToken.getAuthorizationURL());
 
 AccessToken accessToken =  new AccessToken("1187448088446803968-SCI3y058PicAi9HpPy1uJdtXW7DA9X","aRmWEYFa12VncRzsoZmZW7EVW8ARcZ78YKEYp2hlQEmQY");
 
 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 while (null == accessToken) {
     System.out.print("Input PIN here: ");
	  String pin = br.readLine();
 }
 twitter.setOAuthAccessToken(accessToken);
 twitter.updateStatus(text);
 
    }
	
	public void sendTweet(String tweet) {
			try {
				start(tweet);
			} catch (TwitterException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}


	public void addTweet()
	{
		System.out.println(java.time.LocalDateTime.now());
		tweet.setDate(java.time.LocalDateTime.now());
		TweetEntity entry = tweet.getEntity();
		sendTweet(entry.getTweet());
		tweetservice.addNew(tweet.getEntity());
	}

	public Tweet getTweet() {
		return tweet;
	}
	
	public ArrayList<TweetEntity> getTweets(){
		try {
			return tweetservice.getTweets();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void setTweet(Tweet tweet) {
		this.tweet = tweet;
	}

}
