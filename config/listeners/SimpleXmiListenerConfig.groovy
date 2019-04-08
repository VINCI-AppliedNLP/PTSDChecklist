package listeners

import gov.va.vinci.leo.listener.SimpleXmiListener
import gov.va.vinci.leo.tools.LeoUtils

String timeStamp = LeoUtils.getTimestampDateDotTime().replaceAll("[.]", "_")
//String xmiDir =  "data/output/xmi/"
String xmiDir =  "P:\\ORD_Maguen_201412067D\\NLP\\Test\\Output"

if(!(new File(xmiDir)).exists()) (new File(xmiDir)).mkdirs()

listener = new SimpleXmiListener(new File(xmiDir))
listener.setLaunchAnnotationViewer(true)

