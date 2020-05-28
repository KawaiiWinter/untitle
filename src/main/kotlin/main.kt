@file:Suppress(
    "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS",
    "RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS"
)

import discord4j.core.DiscordClient
import discord4j.core.`object`.entity.Attachment
import discord4j.core.`object`.entity.channel.MessageChannel
import discord4j.core.event.domain.message.MessageCreateEvent
import discord4j.core.util.ImageUtil.getUrl
import jdk.nashorn.internal.objects.NativeArray.indexOf
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.Instant

object LoggingTable : Table(){
    val timestamp = text("timestamp")
//    val guild = text("guild")
    val guild_id = text("guild_id")
    val user = text("user")
    val user_id = text("user_id")
    val command = text("command")
//    val file_link = text("file_link")
}


fun main(args: Array<String>) {
    Class.forName("org.mariadb.jdbc.Driver")
    Database.connect(url, driver = "org.mariadb.jdbc.Driver", user = user, password = pw)


    val client = DiscordClient.create(token)
    val gateway = client.login().block()
    gateway.on(MessageCreateEvent::class.java)
        .subscribe { event: MessageCreateEvent ->
            val message = event.message
            val channel: MessageChannel = message.channel.block()
            if ("겨울이 내 정보" == message.content) {
                channel.createEmbed {
                    it.addField(
                        "이름",
                        message.author.get().username,
                        true
                    )
                    it.addField(
                        "ID",
                        message.author.get().id.asString(),
                        true
                    )
                }.block()
            }
            if (message.channelId.equals(333193886946295808)) {
                transaction {
                    addLogger(StdOutSqlLogger)
                    SchemaUtils.create(LoggingTable)

                    LoggingTable.insert() { it ->
                        it[timestamp] = Instant.now().toString()
//                    it[guild] = message.channel.ofType(GuildChannel::class.java).toString()
                        it[guild_id] = message.channelId.asString()
                        it[user] = message.author.get().username
                        it[user_id] = message.author.get().id.asString()
                        it[command] = message.content
                        val url = message.attachments.toString()
                        println(url.indexOf("url="))
                    }
                }
            }

        }
    gateway.onDisconnect().block()
}


