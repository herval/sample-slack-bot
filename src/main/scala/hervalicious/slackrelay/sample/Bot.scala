package hervalicious.slackrelay.sample

import com.twitter.util.Await
import hervalicious.slackrelay.SlackBot
import hervalicious.slackrelay.api.slashcommands._

object Bot extends App {

  val clientId = sys.env.get("SLACK_CLIENT_ID")
  val secret = sys.env.get("SLACK_CLIENT_SECRET")
  val token = sys.env.get("SLACK_VERIFICATION_TOKEN")
  val port = sys.env.get("PORT")

  (clientId, secret, token, port) match {
    case (Some(clientId), Some(secret), Some(token), Some(port)) =>

      val bot = new SlackBot(
        clientId = clientId,
        clientSecret = secret,
        verificationToken = token,
        port = port.toInt,
        slashCommands = Seq(
          Ping
        )
      )

      // waits until the server resources are released
      Await.ready(
        bot.start()
      )

    case _ =>
      throw new IllegalArgumentException("Please specify the following environment variables: SLACK_API_KEY, SLACK_CLIENT_SECRET, SLACK_VERIFICATION_TOKEN")

  }
}


object Ping extends SlashCommandHandler {
  override val command = "/ping"

  override def handler(command: SlashCommand, responder: SlashCommandResponder) = {
    for {
      _ <- responder.send( // send a response...
        text = s"pong: ${command.text}",
        respondTo = ResponseContext.InChannel // ...to the channel where the command was invoked
      )

      _ <- responder.send( // you can respond multiple times!
        text = "✌️",
        respondTo = ResponseContext.RequesterOnly // only the requester will see this response
      )
    } yield Unit
  }
}