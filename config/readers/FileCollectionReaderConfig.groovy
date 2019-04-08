package readers

import gov.va.vinci.leo.cr.FileCollectionReader;
//String pathToFiles = "data/input/"
String pathToFiles = "P:\\ORD_Maguen_201412067D\\NLP\\Test\\Input"
boolean recurse = false

reader = new FileCollectionReader(new File(pathToFiles), recurse);

