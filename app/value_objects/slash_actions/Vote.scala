package slash_actions

object Vote extends SlashAction {

  def execute(votingSession: String, username: String, args: Any*) {
    var vote = args(0).asInstanceOf[Integer]
    // TODO: Set vote on votingSession
  }

}