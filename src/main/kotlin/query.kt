import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

fun query(sql: String) {
    try {
        val conn = DriverManager.getConnection("$url$db?user=$user&password=$pw")
        var stmt: Statement? = null

        stmt?.execute(sql)
    } catch (e: SQLException) {
        e.printStackTrace()
    }
}
