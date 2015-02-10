package object web {

  import util.Properties
  import twitter4j._

  def tweet(text: String) {
    val twitterConfig = new twitter4j.conf.ConfigurationBuilder()
      .setOAuthConsumerKey("")
      .setOAuthConsumerSecret(Properties.envOrElse("API_SECRET", ""))
      .setOAuthAccessToken("")
      .setOAuthAccessTokenSecret(Properties.envOrElse("ACCESS_TOKEN_SECRET", ""))
      .build()

    val twitter = new TwitterFactory(twitterConfig).getInstance()
    twitter.updateStatus(text.take(140))
  }
}
