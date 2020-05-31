import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.events.message.MessageUpdateEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.Instant

object LoggingTable : Table() {
    val timestamp = text("timestamp")
    val server = text("guild")
    val server_id = text("guild_id")
    val channel = text("channel")
    val channel_id = text("channel_id")
    val author = text("user")
    val user_id = text("user_id")
    val message = text("message")
}


//object EditLoggingTable : Table() {
//    val timestamp = text("timestamp")
//    val server = text("guild")
//    val server_id = text("guild_id")
//    val channel = text("channel")
//    val channel_id = text("channel_id")
//    val author = text("user")
//    val user_id = text("user_id")
//    val before_message = text("before_message")
//    val after_message = text("after_message")
//}

class Listener : ListenerAdapter() {


    override fun onMessageReceived(event: MessageReceivedEvent) {

        Class.forName("org.mariadb.jdbc.Driver")
        Database.connect(url, driver = "org.mariadb.jdbc.Driver", user = user, password = pw)

        val user = event.author
        val guild = event.guild
        val tc = event.textChannel
        val msg = event.message

        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(LoggingTable)
            LoggingTable.insert {
                it[timestamp] = Instant.now().toString()
                it[server] = guild.name
                it[server_id] = guild.id
                it[channel] = tc.name
                it[channel_id] = tc.id
                it[author] = user.name
                it[user_id] = user.id
                it[message] = msg.contentRaw
            }
        }
        if (user.isBot) return
        if (msg.contentRaw.equals("겨울이 안녕")) {
            tc.sendMessage("안녕! ${user.name}").queue()
        }
    }

//    override fun onMessageUpdate(event: MessageUpdateEvent) {
//
//        Class.forName("org.mariadb.jdbc.Driver")
//        Database.connect(url, driver = "org.mariadb.jdbc.Driver", user = user, password = pw)
//
//        val user = event.author
//        val guild = event.guild
//        val tc = event.textChannel
//        val msg = event.message
//        transaction {
//            addLogger(StdOutSqlLogger)
//            SchemaUtils.create(EditLoggingTable)
//            EditLoggingTable.insert {
//                it[timestamp] = Instant.now().toString()
//                it[server] = guild.name
//                it[server_id] = guild.id
//                it[channel] = tc.name
//                it[channel_id] = tc.id
//                it[author] = user.name
//                it[user_id] = user.id
//                it[before_message] = msg.contentRaw
//                it[after_message] = msg.con
//            }
//        }
//    }

}