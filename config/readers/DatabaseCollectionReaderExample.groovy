import gov.va.vinci.leo.cr.DatabaseCollectionReader;

reader = new DatabaseCollectionReader("com.microsoft.sqlserver.jdbc.SQLServerDriver",
        "jdbc:sqlserver://vhacdwrb02:1433;" +
        "databasename=ORD_Bress_201607061D;" +
         "integratedSecurity=true",
        "", "",
        "SELECT  tiudocumentsid" +
                "      ,reporttext" +
                //", patientsid" +
               // " FROM [ORD_Bress_201607061D].[nlp].[01-29-2017_Sample_Recall_set] a",
               " FROM  [ORD_Bress_201607061D].[nlp].[03-04-2017_Final_Recall]   a ",
                // " FROM [ORD_Bress_201607061D].[nlp].[Combined_trainsetCorpus_02282017] a" ,
                //" FROM [ORD_Bress_201607061D].[nlp].[20170219_Precision02172017] a" +
               // " join  " +
                //" [ORD_Bress_201607061D].[Src].[TIU_TIUDocument_8925] d" +
                //" on a.tiudocumentsid=d.tiudocumentsid",
               // "  FROM [ORD_Bress_201607061D].[nlp].[Combined_trainsetCorpus]  ",
                //"  FROM [ORD_Bress_201607061D].[nlp].[01-29-2017_Sample_Recall_set]  ",
     //   "Select Tiudocumentsid, reporttext, '' as patientsid FROM [ORD_Bress_201607061D].[nlp].[03-11-2017_Final_Precision]  ",
       /* "SELECT  a.Tiudocumentsid " +
                ",b.reporttext " +
                " FROM [ORD_Bress_201607061D].[nlp].[Combined_trainsetCorpus_02282017] a " +
                " join [ORD_Bress_201607061D].[Src].[TIU_TIUDocument_8925] b" +
                " on a.tiudocumentsid=b.tiudocumentsid   ",*/
"tiudocumentsid",
"reporttext");


//Second Index must be Patientsid


/*
[ORD_Bress_201607061D].[nlp].[01-20-2017TrainingValidation_Set]


SELECT [TIUDocumentSID]
" +
                "      ,[Snippet]\n" +
                "  FROM [ORD_Bress_201607061D].[validation].[Knowledgebase060816]
 */

/*
[ORD_Bress_201607061D].[nlp].[20170113_Initial_knowledgebase_BigSet]
*/
/*


 */
