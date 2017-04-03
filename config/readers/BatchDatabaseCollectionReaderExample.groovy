package readers

import gov.va.vinci.leo.cr.BatchDatabaseCollectionReader;

reader = new BatchDatabaseCollectionReader("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://vhacdwrb02:1433;databasename=ORD_Bress_201607061D;integratedSecurity=true",
        "", "",
        "SELECT a.Tiudocumentsid_c as tiudocumentsid " +
        ",b.reporttext " +
        "FROM  [***ORD***].[nlp].[FullInputDocumentSet_20160701] a " +
                " join [***ORD***].[Src].[TIU_TIUDocument_8925] b" +
                " on a.tiudocumentsid_c=b.tiudocumentsid" +
                " where rowNo >{min} and rowNo<{max}   ",
        "tiudocumentsid", "reporttext",
        6000000,  7000000,
        30000);

//Processing Begin: 8,173,027 Docs
//Start 11:55
//0, 500,000 Table_1 4:09:45.237
//500,000, 1,000,000 Table_2 04:12:55.293
//1,000,000, 1,500,000 Table_3 04:01:54.028
//1,500,000, 2,000,000 Table_4
//2,000,000, 2,500,000 Table_5
//2,500,000, 3,000,000 Table_6
//3,000,000, 3,500,000 Table_7
//3,500,000, 4,000,000 Table_8
//4,000,000, 4,500,000 Table_9
//4,500,000, 5,000,000 Table_10
//5,000,000, 5,500,000 Table_11
//5,500,000, 6,000,000 Table_12
//6,000,000, 7,000,000 Table_13
//7,000,000, 8,000,000 Table_14
//07:48:16.450.
//21:42:11.752
//500,000 07:09:40.576
//500,000 07:31:17.927
//500,000 07:56:55.020
//500,000 07:56:55.020