package readers

import gov.va.vinci.leo.cr.BatchDatabaseCollectionReader;

reader = new BatchDatabaseCollectionReader("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://vhacdwrb02:1433;databasename=ORD_Cohen_201607127D;integratedSecurity=true",
        "", "",
        "SELECT a.Tiudocumentsid" +
        ",b.reporttext " +
        "FROM  NOTES Table a " +
                " join text_table b with(nolock) " +
                " on a.tiudocumentsid=b.tiudocumentsid" +
                " where RowID >{min} and RowID<{max}   ",
        "tiudocumentsid", "reporttext",
        120000000,  130000000,
        30000);
