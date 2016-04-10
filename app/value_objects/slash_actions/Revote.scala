package slash_actions
import models._

object Revote extends SlashAction {

  def execute(
    votingSession:Option[VotingSession],
    username:String,
    data:String) = {
    if (votingSession.isDefined) {
      val session = votingSession.get
      val ticketDescription = session.ticket
      clearVotes(session)
      sendRevoteCalledMessage(ticketDescription)
    } else {
      // TODO warn that session hasn't started
    }
  }

  def clearVotes(session:VotingSession) = {
    VotingSession.clearVotes(session)
  }

  def sendRevoteCalledMessage(ticketDescription:String) = {
    val message = s"@here Given the discussion above about #ticketDescription, " + 
    "please place a new vote using `/chasm vote`. Maybe try coming to a " + 
    "consensus this time so I can take a break."
    slack.IncomingWebhookClient.postInChannel(message)   
  }

}