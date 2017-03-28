package listeners

import gov.va.vinci.siman.listener.SimanDatabaseListener
import gov.va.vinci.siman.tools.DbConnectionInfo
import gov.va.vinci.siman.tools.SimanUtils
import java.sql.DriverManager

//-----------THESE NEED TO BE CHANGED-----------
PROJECT_ID = "eb510a6c-cb05-4a3a-9bda-bc29e00a2823" // Grab this from CR url
CLINICAL_ELEMENT_CONFIG_ID = "4f192164-3804-4d8e-9084-81331204c9a5" // Grab this from CEC url
ANNOTATION_GROUP = "pre_" // Specify the pre_annotation group name

DATABASE = 'elite'
DB_DRIVER = "com.mysql.jdbc.Driver"
DB_URL = "jdbc:mysql://localhost/" + DATABASE + "?useUnicode=yes&characterEncoding=UTF-8"
DB_USERNAME = "root"
DB_PASSWORD = "root"
//----------------------------------------------

dbConnectionInfo = new DbConnectionInfo(DB_DRIVER, DB_URL, DB_USERNAME, DB_PASSWORD);
dialect = SimanUtils.getSQLTemplate(dbConnectionInfo.getDriver(), DATABASE);
Class.forName(dbConnectionInfo.getDriver()).newInstance()
connection = DriverManager.getConnection(dbConnectionInfo.getUrl(), dbConnectionInfo.getUsername(), dbConnectionInfo.getPassword())
listener = new SimanDatabaseListener(connection, dialect)
listener.setProjectId(PROJECT_ID).setAnnotationGroup(ANNOTATION_GROUP).setInsertOnly(true).setSchema(DATABASE)
        .setClinicalElementConfigurationID(CLINICAL_ELEMENT_CONFIG_ID)