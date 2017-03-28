package listeners

import gov.va.vinci.leo.elite.listeners.CsvListener_2;

File filePath = new File("data/output/outputData.csv");
listener = new CsvListener_2(filePath);
listener.writeHeaders()
