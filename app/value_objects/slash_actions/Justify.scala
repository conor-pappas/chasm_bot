package slash_actions
import models._

object Justify extends SlashAction {

  def execute(
    votingSession:Option[VotingSession],
    username:String,
    data:String) = {
    val justification = data
    if (votingSession.isDefined) {
      val currentVote = votingSession.get.votes(username)
      postJustification(justification, currentVote)
    }
  }

  def postJustification(justification:String, currentVote:Integer) = {
    val message = s"A user has justified their vote of $currentVote with the " + 
    "following:\n" + justification + "\n" + "*Insert cheeky comment here*"
    slack.IncomingWebhookClient.postInChannel(message)
  }

}