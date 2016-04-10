package slack

object IncomingWebhookClient {
  import play.api._
  import play.api.mvc._
  import play.api.libs.ws._
  import play.api.libs.json._

  val WebhookUrl = "https://hooks.slack.com/services/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"

  def sendMessage(message: String) {
    val raw = s"""
    {
      "username": "ChASM Bot",
      "text": "$message"
    }
    """

    val data = Json.parse(raw)
    var request = WS.url(WebhookUrl).post(data)
  }

}