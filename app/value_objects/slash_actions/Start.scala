package slash_actions
import models._

object Start extends SlashAction {

  def execute(
    votingSession:Option[VotingSession],
    username:String,
    data:String) = {
    val ticketDescription = data

    VotingSession.destroyCurrent

    startNewSession(ticketDescription)
    sendVotingStartedMessage(ticketDescription)
  
  }

  def startNewSession(ticketDescription:String) = {
    VotingSession.create(ticketDescription, Map[String, Int]())
  }

  def sendVotingStartedMessage(ticketDescription:String) = {
    val message = s"@here Ticket $ticketDescription is ready for voting. " +
    "Please respond using `/chasm vote` to place your vote."
    slack.IncomingWebhookClient.postInChannel(message)
  }

}