package listeners

import gov.va.vinci.leo.aucompare.comparators.SpanAuComparator
import gov.va.vinci.leo.aucompare.listener.AuCompareCSVListener;

auMap=["gov.va.vinci.leo.types.RefSt_Current"   :"gov.va.vinci.leo.types.Current",
       "gov.va.vinci.leo.types.RefSt_Past"      :"gov.va.vinci.leo.types.Past",
       "gov.va.vinci.leo.types.RefSt_Nondrinker":"gov.va.vinci.leo.types.Never"]

File outFile = new File("data/output/csvCompare.csv")
listener = new AuCompareCSVListener(outFile, new SpanAuComparator(auMap, true));
