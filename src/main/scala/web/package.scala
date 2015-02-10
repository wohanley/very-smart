package object web {

  import util.Properties
  import twitter4j._

  def tweet(text: String) {
    val twitterConfig = new twitter4j.conf.ConfigurationBuilder()
      .setOAuthConsumerKey("M4EOFJPkK08hpMoeOMLmKtIaE")
      .setOAuthConsumerSecret(Properties.envOrElse("API_SECRET", ""))
      .setOAuthAccessToken("3015960198-3RKVdaRbPwwkVg1UGLQuO0PLysdta4aT1iSK1fe")
      .setOAuthAccessTokenSecret(Properties.envOrElse("ACCESS_TOKEN_SECRET", ""))
      .build()

    val twitter = new TwitterFactory(twitterConfig).getInstance()
    twitter.updateStatus(text.take(140))
  }
}
