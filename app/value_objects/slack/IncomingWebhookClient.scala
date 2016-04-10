package slack

object IncomingWebhookClient {
  import play.api._
  import play.api.mvc._
  import play.api.libs.ws._
  import play.api.libs.json._
  import collection.mutable.HashMap

  val WebhookUrl = "https://hooks.slack.com/services/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"

  def sendMessage(message:String, channel:Option[String]) {
    var data = HashMap(
      "username" -> "ChASM BOT",
      "text" -> message
    )
    if(channel.isDefined) {
      data += "channel" -> channel.get
    }

    var json = Json.toJson(data.toMap) 
    var request = WS.url(WebhookUrl).post(json)
  }

  def directMessage(username:String, message:String) {
    sendMessage(message, Some(s"@$username"))
  }

  def postInChannel(message:String, channel:String = null) {
    sendMessage(message, Some(channel))
  }

}