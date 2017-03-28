import groovy.sql.Sql;

def INPUT_DIRECTORY = "../data/input"
def jdbcConnectString = "jdbc:mysql://localhost:3306/elite?user=root&password=root";
def createTable = "create table if not exists document (id varchar(1000), note varchar(10000));"
def insertSql = "insert into document (id, note) values (?, ?);";

def sql = Sql.newInstance(jdbcConnectString);

sql.execute(createTable);

new File(INPUT_DIRECTORY).eachFile() {
    String documentId = it.name
    println("Got file: ${documentId}...");
    sql.execute(insertSql, [documentId, it.text]);
}