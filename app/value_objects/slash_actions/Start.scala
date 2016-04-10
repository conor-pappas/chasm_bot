package slash_actions

object Start extends SlashAction {

  def execute(votingSession: String, username: String, args: Any*) {
    val ticketDescription = args(0).asInstanceOf[String]
    val message = votingStartedMessage(ticketDescription)
    slack.IncomingWebhookClient.postInChannel(message)
  }

  def votingStartedMessage(ticketDescription:String):String = {
    s"@here Ticket $ticketDescription is ready for voting. Please respond " +
    "using `/chasm vote` to place your vote."
  }

}