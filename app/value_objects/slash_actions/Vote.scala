package slash_actions
import models._

object Vote extends SlashAction {

  def execute(
    votingSession:Option[VotingSession],
    username:String,
    data:String):Option[String] = {
    var vote = data.toInt
    if (votingSession.isDefined) {
      addVote(votingSession.get, username, vote)
      Some("Thank you for your vote")
    } else {
      noActiveSessionWarning
    }
  }

  def addVote(votingSession:VotingSession, username:String, vote:Int) = {
    VotingSession.addVote(votingSession, username, vote)
  }

}