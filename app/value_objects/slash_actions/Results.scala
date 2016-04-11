package slash_actions
import models._

object Results extends SlashAction {

  def execute(
    votingSession:Option[VotingSession],
    username:String,
    data:String):Option[String] = {
    if (votingSession.isDefined) {
      sendCurrentResults(votingSession.get)
      None
    } else {
      noActiveSessionWarning
    }
  }

  def sendCurrentResults(votingSession:VotingSession) = {
    val message = votingSession.votes.toString()
    slack.IncomingWebhookClient.directMessage(admin, message)
  }

}