import gov.va.vinci.leo.pcl.listeners.PclDatabaseListener
import gov.va.vinci.leo.tools.LeoUtils

int batchSize = 1000
String url = "jdbc:sqlserver://vhacdwrb02:1433;databasename=***ORD***;integratedSecurity=true"
String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
String dbUser = ""
String dbPwd = ""

String dbsName = "***ORD***"
String timeStamp = LeoUtils.getTimestampDateDotTime().replaceAll("[.]", "_")
String tableName = "[nlp].[PCL_" + timeStamp.substring(0, 8) + "Initial_Test]"

fieldList = [
        ["DocID", "0", "bigint"],
        ["PCL_Type", "-1", "varchar(50)"],
        ["PCL_Score", "-1", "varchar(50)"],
        ["Snippet", "-1", "varchar(5000)"],
        ["SpanStart", "-1", "int"],
        ["SpanEnd", "-1", "int"],
        //["patientsid", "1", "varchar(50)"]
]

boolean dropExisting = true;
listener = PclDatabaseListener.createNewListener(driver, url, dbUser, dbPwd, dbsName, tableName, batchSize, fieldList)
//create table
listener.createTable(listener.createStatement, dropExisting, tableName)
println(listener.createStatement)
println(listener.preparedStatementSQL)
  