package slash_actions

object Question extends SlashAction {

  def execute(votingSession: String, username: String, args: Any*) {
    val question = args(0).asInstanceOf[String]
    val message = questionMessage(question)
    slack.IncomingWebhookClient.postInChannel(questionMessage(message))
  }

  def questionMessage(question:String):String = {
    s"A user has asked the following question:\n" + question + "\n" + 
    "Get it right this time, eh?"
  }

}