package slash_actions

object Results extends SlashAction {

  def execute(votingSession: String, username: String, args: Any*) {
    val currentResults = currentResults(votingSession)
    slack.IncomingWebhookClient.directMessage(admin, currentResults)
  }

  def currentResults(votingSession):String {
    "NOBODY IS AGREEING"
  }

}