package model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "tweet")
@SessionScoped
public class Tweet {
	
	
	private int id;
	private String tweet;
	private LocalDateTime date;

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public TweetEntity getEntity()
	{
		TweetEntity tweetEntity = new TweetEntity();
		tweetEntity.setId(id);
		tweetEntity.setTweet(tweet);
		tweetEntity.setDate(date);
		
		return tweetEntity;
	}

	
}
