import gov.va.vinci.leo.pcl.listeners.PclDatabaseListener
import gov.va.vinci.leo.tools.LeoUtils

int batchSize = 1000
String url = "jdbc:sqlserver://vhacdwrb02:1433;databasename=ORD_Bress_201607061D;integratedSecurity=true"
String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
String dbUser = ""
String dbPwd = ""

String dbsName = "ORD_Bress_201607061D"
String timeStamp = LeoUtils.getTimestampDateDotTime().replaceAll("[.]", "_")
String tableName = "[nlp].[" + timeStamp.substring(0, 8) + "Final_Combined_5-2]"

fieldList = [
        ["DocID", "0", "bigint"],

        ["NyhaValue", "-1", "varchar(50)"],
        ["Snippet", "-1", "varchar(5000)"],
        ["SpanStart", "-1", "int"],
        ["SpanEnd", "-1", "int"],
        //["patientsid", "1", "varchar(50)"]
]

boolean dropExisting = false;
listener = PclDatabaseListener.createNewListener(driver, url, dbUser, dbPwd, dbsName, tableName, batchSize, fieldList)
//create table
listener.createTable(listener.createStatement, dropExisting, tableName)
println(listener.createStatement)
println(listener.preparedStatementSQL)
  