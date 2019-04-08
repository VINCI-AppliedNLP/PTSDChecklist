package listeners

import gov.va.vinci.leo.pcl.listeners.ChexListener
import gov.va.vinci.leo.tools.LeoUtils

String timeStamp = LeoUtils.getTimestampDateDotTime().replaceAll("[.]", "_")


String chexSchema = "validation"  //
String chexSuffix = "_xxx_" + timeStamp.substring(0, 8) // Change the suffix for each run, otherwise the data WILL BE OVERWRITTEN!
def chexTypes= [
        "gov.va.vinci.leo.pcl.types.Logic"  ] // when blank, SimanListener outputs all annotations
boolean chexOverwrite = false
String chexDocumentTextSelectQuery = ""
String chexColumnPrefix = "["
String chexColumnSuffix ="]"
int batchSize = 1000
String url = "jdbc:sqlserver://vhacdwrb02:1433;databasename=ORD_Maguen_201412067D;integratedSecurity=true"
String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"


listener = ChexListener.newChexListener(
        driver,
        url,
        chexDocumentTextSelectQuery,
        chexSchema,
        chexSuffix,
        chexColumnPrefix,
        chexColumnSuffix,
        chexTypes,
        batchSize,
        chexOverwrite)
