package slash_actions

object Justify extends SlashAction {

  def execute(votingSession: String, username: String, args: Any*) {
    var justificationMesage = args(0).asInstanceOf[String]
    slack.IncomingWebhookClient.postInChannel(justificationMesage)
  }

}