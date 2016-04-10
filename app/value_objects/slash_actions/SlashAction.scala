package slash_actions

trait SlashAction {

  def execute(votingSession: String, username: String, args: Any*)

}