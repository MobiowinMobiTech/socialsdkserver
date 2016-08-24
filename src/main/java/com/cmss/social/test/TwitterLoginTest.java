package com.cmss.social.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.codehaus.jackson.JsonFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import twitter4j.IDs;
import twitter4j.PagableResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterLoginTest {

	public static void main(String[] args) {
		// String testStatus = "Hello from social banking";

		ConfigurationBuilder cb = new ConfigurationBuilder();

		// the following is set without accesstoken- desktop client
		cb.setDebugEnabled(true).setOAuthConsumerKey("BZHC4regJXoIG7IbSFp52keqs").setOAuthConsumerSecret("m6h2J8uDs33oWYr9sTL7QIMOtVx1jphzKHNRnyGFAZ8geiEWnL");
	
		AccessToken accessToken = null;
		try {
			TwitterFactory tf = new TwitterFactory(cb.build());
			Twitter twitter = tf.getInstance();

			try {
				System.out.println("-----");

				// get request token.
				// this will throw IllegalStateException if access token is
				// already available
				// this is oob, desktop client version
				
				RequestToken requestToken = twitter.getOAuthRequestToken();

				String reqToken = requestToken.toString();

				System.out.println("Got request token.");
				System.out.println("Request token: " + requestToken.getToken());
				System.out.println("Request token secret: " + requestToken.getTokenSecret());

				System.out.println("|-----");

				accessToken = null;

				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

				while (null == accessToken) {
					System.out.println("Open the following URL and grant access to your account:");
					System.out.println(requestToken.getAuthorizationURL());
					System.out.print("Enter the PIN(if available) and hit Enter after you granted access.[PIN]");
					String pin = br.readLine();
					System.out.print("Enter the OAuth Verifier : ");
					String oauth_verifier = br.readLine();
					try {
						if (pin.length() > 0) {
							accessToken = twitter.getOAuthAccessToken(requestToken, pin);
						} else {
							System.out.println("Inside else");
						
							RequestToken newReqToken = new RequestToken(requestToken.getToken(), requestToken.getTokenSecret());
							
							accessToken = twitter.getOAuthAccessToken(newReqToken, oauth_verifier);
						}
					} catch (TwitterException te) {
						if (401 == te.getStatusCode()) {
							System.out.println("Unable to get the access token." + te.getMessage());
						} else {
							te.printStackTrace();
						}

					}
				}
				System.out.println("Got access token.");
				System.out.println("Access token: " + accessToken.getToken());
				System.out.println("Access token secret: " + accessToken.getTokenSecret());

			} catch (IllegalStateException ie) {
				// access token is already available, or consumer key/secret is
				// not set.
				if (!twitter.getAuthorization().isEnabled()) {
					System.out.println("OAuth consumer key/secret is not set.");
					System.exit(-1);
				}
			}

			
			long cursor = -1;
			IDs ids;
			long lngUsersList[] = new long[1];
			System.out.println("Listing following ids.");
				
			PagableResponseList<User> users = twitter.getFriendsList(accessToken.getUserId(), -1, 200);
			
			JSONArray followerList = new JSONArray();
			for (User user : users) {
				
				JSONObject follower = new JSONObject(); 

				System.out.println("@" + user.getScreenName());
				System.out.println("@" + user.getName());
				System.out.println("@" + user.getProfileImageURLHttps());

				follower.put("id", user.getId() + "");
				follower.put("ScreenName", user.getScreenName());
				follower.put("Name", user.getName());
				follower.put("Image", user.getProfileImageURLHttps());
				followerList.put(follower);
			}

			System.out.println("My Follower list is :" + followerList);

			System.out.println("ready exit");

			System.exit(0);
		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to get timeline: " + te.getMessage());
			System.exit(-1);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("Failed to read the system input.");
			System.exit(-1);
		}
		catch(Exception ex)
		{
			System.out.println("Exception in fetching follower list" + ex.getMessage());
		}
	}

}
