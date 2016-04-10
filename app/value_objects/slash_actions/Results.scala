package slash_actions

object Results extends SlashAction {

  def execute(votingSession:String, username:String, data:String) {
    val message = currentResults(votingSession)
    slack.IncomingWebhookClient.directMessage(admin, message)
  }

  def currentResults(votingSession:String):String = {
    "NOBODY IS AGREEING"
  }

}