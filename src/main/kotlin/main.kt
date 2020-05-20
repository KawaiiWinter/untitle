import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

fun main(){
    query("show databases")
}

fun query(sql: String) {
    val conn = DriverManager.getConnection("$url$db?user=$user&password=$pw")

    val smt: Statement?
    var result: ResultSet?

    try {
        smt = conn!!.createStatement()
        result = smt!!.executeQuery(sql)
        if (smt.execute(sql)) {
            result = smt.resultSet
        }
        while (result!!.next()) {
            println(result)
        }
    } catch (ex: SQLException) {
        println(ex.localizedMessage)
    }


}