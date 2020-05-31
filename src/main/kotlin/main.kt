import net.dv8tion.jda.api.AccountType
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import javax.security.auth.login.LoginException

fun main() {
    val jda: JDA

    val jb = JDABuilder(AccountType.BOT)
    jb.setAutoReconnect(true)
    jb.setStatus(OnlineStatus.ONLINE)
    jb.setToken(token)
    jb.addEventListeners(Listener())

    try {
        jda = jb.build()
    } catch (e: LoginException){
        e.printStackTrace()
    } catch (e: InterruptedException){
        e.printStackTrace()
    }
}