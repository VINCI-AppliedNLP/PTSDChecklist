package readers

import gov.va.vinci.leo.cr.BatchDatabaseCollectionReader;

reader = new BatchDatabaseCollectionReader("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://vhacdwrb02:1433;databasename=ORD_Bress_201607061D;integratedSecurity=true",
        "", "",
        "SELECT a.Tiudocumentsid " +
        ",b.reporttext " +
        "FROM  [ORD_Bress_201607061D].[nlp].[Final_Cohort_RowNum] a " +
                " join [ORD_Bress_201607061D].[Src].[TIU_TIUDocument_8925] b" +
                " on a.tiudocumentsid=b.tiudocumentsid" +
                " where rowid >{min} and rowid<{max}   ",
        "tiudocumentsid", "reporttext",
        33000000,  34500000,
        30000);

