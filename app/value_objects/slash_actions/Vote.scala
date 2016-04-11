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
    } else {
      // TODO warn that session hasn't started
    }

    Some("Thank you for your vote")
  }

  def addVote(votingSession:VotingSession, username:String, vote:Int) = {
    VotingSession.addVote(votingSession, username, vote)
  }

}