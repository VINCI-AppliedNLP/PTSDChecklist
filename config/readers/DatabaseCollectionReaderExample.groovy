import gov.va.vinci.leo.cr.DatabaseCollectionReader;

reader = new DatabaseCollectionReader("com.microsoft.sqlserver.jdbc.SQLServerDriver",
        "jdbc:sqlserver://vhacdwrb02:1433;" +
        "databasename=ORD_Maguen_201412067D;" +
         "integratedSecurity=true",
        "", "",
        "SELECT  tiudocumentsid" +
                "      ,reporttext" +
                " , '' as patientsid" +
               " FROM  [ORD_Maguen_201412067D].[NLP].[PCL_Test_DOCS]  ",

"tiudocumentsid",
"reporttext");


