package slash_actions

object Question extends SlashAction {

  def execute(votingSession: String, username: String, args: Any*) {
    var question = args(0).asInstanceOf[String]
  }

}