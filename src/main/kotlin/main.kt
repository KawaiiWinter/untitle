@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS",
    "RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS"
)

import discord4j.core.DiscordClient
import discord4j.core.event.domain.message.MessageCreateEvent


fun main(args: Array<String>) {
    val client = DiscordClient.create(token)
    val gateway = client.login().block()
    gateway.on(MessageCreateEvent::class.java)
        .subscribe { event: MessageCreateEvent ->
            val message = event.message
            if ("겨울이 핑" == message.content) {
                val channel = message.channel.block()
                channel.createMessage("Pong!").block()
            }
        }
    gateway.onDisconnect().block()
}


