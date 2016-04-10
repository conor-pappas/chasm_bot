package slash_actions
import models._

trait SlashAction {

  val admin = "conor"

  def execute(
    votingSession:Option[VotingSession],
    username:String,
    data:String)

}