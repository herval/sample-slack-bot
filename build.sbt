enablePlugins(JavaAppPackaging)

name := "slack_relay_bot_sample"

version := "0.1"

scalaVersion := "2.12.4"

mainClass := Some("hervalicious.slackrelay.sample.Bot")


herokuAppName in Compile := "testslackrelaybot" // change this to match you app name