package slash_actions
import models._

object Question extends SlashAction {

  def execute(
    votingSession:Option[VotingSession],
    username:String,
    data:String) = {
    val question = data
    if (votingSession.isDefined) {
      postQuestionInChannel(question)
    } else {
      // TODO warn that session hasn't started
    }
  }

  def postQuestionInChannel(question:String) = {
    val message = s"A user has asked the following question:\n" + question + 
    "\nGet it right this time, eh?"
    slack.IncomingWebhookClient.postInChannel(message)
  }

}