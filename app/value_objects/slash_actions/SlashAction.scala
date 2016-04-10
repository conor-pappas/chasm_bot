package slash_actions

trait SlashAction {

  val admin = "conor"

  def execute(votingSession: String, username: String, args: Any*)

}