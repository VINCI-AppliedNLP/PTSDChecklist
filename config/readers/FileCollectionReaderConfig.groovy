package readers

import gov.va.vinci.leo.cr.FileCollectionReader;
//String pathToFiles = "data/input/"
String pathToFiles = "NLP\\Test\\Input"
boolean recurse = false

reader = new FileCollectionReader(new File(pathToFiles), recurse);

