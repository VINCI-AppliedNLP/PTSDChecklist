package readers

import gov.va.vinci.leo.cr.BatchDatabaseCollectionReader;

reader = new BatchDatabaseCollectionReader("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://vhacdwrb02:1433;databasename=ORD_Cohen_201607127D;integratedSecurity=true",
        "", "",
        "SELECT a.Tiudocumentsid" +
        ",b.reporttext " +
        "FROM  [ORD_Cohen_201607127D].[nlp].[tiusid_4Olga_01042019_withrows] a " +
                " join CDW_TIU.TIU.TIUDocument_8925_02 b with(nolock) " +
                " on a.tiudocumentsid=b.tiudocumentsid" +
                " where RowID >{min} and RowID<{max}   ",
        "tiudocumentsid", "reporttext",
        120000000,  130000000,
        30000);

/*
Cohen run -
[nlp].[PCL_output_10_20mil ] Client finished in: 44:45:03.981.
20-30 Client finished in: 37:06:44.571.
30-40 Client finished in: 44:02:14.441.
40-50 stuck after 42309999 ??

50-60

Shiner Run -
//77,329,693 Total
[PCL_20180508_Output_1] 0, 1000000/
    Errored out @where rowNo >389999 and rowNo<420000
    [PCL_20180508_Output_1-5] 389999, 1000000 - Client finished in: 10:04:00.586.
[PCL_20180508_Output_2] 1000000, 2000000
    Errored out @where rowNo >1389999 and rowNo<1420000
    [PCL_20180508_Output_2-5] 1389999, 2000000 - Client finished in: 08:22:40.523
[PCL_20180508_Output_3] 2000000, 3000000
    Errored out @where where rowNo >2359999 and rowNo<2390000
    [PCL_20180508_Output_3-5] 2359999, 3000000 - Client finished in: 07:36:47.108. - Received CAS Count: 639997
[PCL_20180509_Output_4] 3000000, 4000000 - Client finished in: 12:32:44.653.
[PCL_20180509_Output_5] 4000000, 5000000 - Client finished in: 09:53:20.501.
[PCL_20180509_Output_6] 5000000, 6000000
            --Errored out on last ~100 docs
[PCL_20180509_Output_7] 6000000, 7000000
    Error @ where rowNo >6539999 and rowNo<6570000 - Client finished in: 02:57:47.600.
[PCL_20180509_Output_8] 7000000, 8000000 - Client finished in: 07:02:25.279.
[PCL_20180509_Output_9] 8000000, 9000000 - Client finished in: 07:24:19.981.
[PCL_20180509_Output_10] 9000000, 10000000 - Client finished in: 11:44:20.272.
[PCL_20180514_Output_11] 10000000, 11000000 - Client finished in: 07:00:14.766.
[PCL_20180514_Output_12] 11000000, 12000000 - Client finished in: 07:54:47.239.
[PCL_20180516_Output_13] 12000000, 13000000 - Client finished in: 08:41:28.835.
[PCL_20180516_Output_14] 13000000, 14000000 - Client finished in: 08:45:46.604.
[PCL_20180516_Output_15] 14000000, 15000000 - Client finished in: 12:05:19.227.
[PCL_20180516_Output_16] 15000000, 16000000 - Client finished in: 12:23:21.152.
[PCL_20180517_Output_17] 16000000, 17000000
[PCL_20180517_Output_18] 17000000, 18000000 - Client finished in: 13:47:55.106.
[PCL_20180517_Output_19] 18000000, 19000000 - Client finished in: 08:40:14.560.
[PCL_20180521_Output_20] 19000000, 20000000 - Client finished in: 18:07:26.785.
[PCL_20180521_Output_21] 20000000, 21500000 - Client finished in: 07:32:19.148.
[PCL_20180521_Output_22] 21500000, 23000000
     Errored out  >22699999 and rowNo<22730000 - Client finished in: 06:05:26.541.
[PCL_20180521_Output_23] 23000000, 25000000
    Errored out rowNo >24199999 and rowNo<24230000 - Client finished in: 09:15:35.208.
[PCL_20180521_Output_24] 25000000, 27000000 - Client finished in: 08:12:55.920.
[PCL_20180524_Output_25] 27000000, 29000000 - Client finished in: 79:14:24.451.
[PCL_20180524_Output_26] 29000000, 31000000 - Client finished in: 08:03:37.256.
[PCL_20180524_Output_27] 31000000, 33000000 - Client finished in: 80:31:42.072.
[PCL_20180529_Output_28] 33000000, 35000000 - RERUN, RB03 DOWN >34019999 and rowNo<34,050,000
                                            - RERUN, DAVDEV04 SHut down at rowNo<34050000
[PCL_20180529_Output_29] 35000000, 37000000 - Client finished in: 09:57:34.274.
[PCL_20180529_Output_30] 37000000, 39000000 - Client finished in: 09:21:38.220.
[PCL_20180529_Output_31] 39000000, 41000000 - Client finished in: 10:24:36.846.
[PCL_20180529_Output_32] 41000000, 43000000 - Client finished in: 10:22:08.627.
[PCL_20180529_Output_33] 43000000, 45000000 - Client finished in: 10:12:32.354.
[PCL_20180529_Output_42] 59000000, 61000000 Error @ rowNo >60289999 and rowNo<60320000
                [PCL_20180529_Output_42-2]  - Client finished in: 08:58:37.360.
[PCL_20180529_Output_43] 61000000, 63000000 Error @ rowNo >62199999 and rowNo<62230000
                [PCL_20180529_Output_43-2]  - Client finished in: 10:12:03.038.
[PCL_20180529_Output_44] 63000000, 65000000 Error @ rowNo >64319999 and rowNo<64350000
                [PCL_20180529_Output_44-2] - Client finished in: 10:45:40.444.
[PCL_20180529_Output_45] 65000000, 67000000 Error @ rowNo >66289999 and rowNo<66320000
                [PCL_20180529_Output_45-2]  - Client finished in: 07:28:15.327.
[PCL_20180614_Output_46] 67000000, 69000000 - 1808285
    [PCL_20180614_Output_46-2] 67800000, 69000000 - 1808285
[PCL_20180614_Output_47] 69000000, 71000000 - 1972724
    [PCL_20180614_Output_47] 70800000, 71000000 - 1972724 - Client finished in: 01:39:16.644.
[PCL_20180614_Output_48] 71000000, 73000000 rowNo<72500000 - Client finished in: 06:49:40.402.
    [PCL_20180614_Output_48] 72500000, 73000000 rowNo<72500000 - Client finished in: 00:10:03.896.
[PCL_20180614_Output_49] 73000000, 75500000 - 1909241 - Client finished in: 08:45:34.342.
[PCL_20180614_Output_50] 75500000, 77533000 - 1829370

//77,329,693 Total




 */
//0,    500,000
//500,000 - 1,000,000
//1,000,000 - 1,500,000

//2,000,000 - 2,250,000
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