import gov.va.vinci.leo.cr.DatabaseCollectionReader;

reader = new DatabaseCollectionReader("com.microsoft.sqlserver.jdbc.SQLServerDriver",
        "jdbc:sqlserver://vhacdwrb02:1433;" +
        "databasename=***ORD***;" +
         "integratedSecurity=true",
        "", "",
        "SELECT  tiudocumentsid" +
                "      ,reporttext" +
                " , '' as patientsid" +
               " FROM  [***ORD***].[NLP].[PCL_Test_DOCS]  ",

"tiudocumentsid",
"reporttext");


