# Slack Relay Bot Sample App

This is a Docker-based implementation of the [Slack Relay Bot] that exposes a bunch of commands as Slack operations


# Implemented commands

## Slash command example: /ping

Responds with "pong" on the same room or private conversation

## Interactive component example: /yes-no


# Up and running in 5 minutes

You'll need Scala, Docker and a Heroku account to build this.

## Setup a Slack Bot

You'll need a functioning Slack "[App](https://api.slack.com/apps)" to start. Follow the instructions to setup one from scratch. The relevant configs you have to make here are:

- The "request URL" for all *Slash Commands* should be `https://<your heroku app name>.herokuapp.com/commands/callback`

- The "request URL" for all *Interactive Components* should be `https://<your heroku app name>/interactive-components/callback`.


## Heroku configuration

Add the following env variables to your Heroku app:

```
heroku config:set SLACK_CLIENT_ID=<your bot client id>
heroku config:set SLACK_CLIENT_SECRET=<your bot client secret>
heroku config:set SLACK_VERIFICATION_TOKEN=<your bot verification token> 
```


## Deploy the app
```
# Clone this repo
git clone ????

# Build the app as a Docker container
sbt docker:publishLocal

# Push it to Heroku (replace <app name> with your Heroku app name)
docker image tag slack_relay_bot_sample:0.1 registry.heroku.com/<app name>/web
docker push registry.heroku.com/<app name>/web
```
