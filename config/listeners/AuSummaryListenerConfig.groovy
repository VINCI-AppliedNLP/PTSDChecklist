import gov.va.vinci.leo.aucompare.comparators.SpanAuComparator
import gov.va.vinci.leo.aucompare.listener.AuSummaryListener;

auMap=["gov.va.vinci.leo.types.RefSt_Current"   :"gov.va.vinci.leo.types.Current",
       "gov.va.vinci.leo.types.RefSt_Past"      :"gov.va.vinci.leo.types.Past",
       "gov.va.vinci.leo.types.RefSt_Nondrinker":"gov.va.vinci.leo.types.Never"]
listener = new AuSummaryListener(new SpanAuComparator(auMap, true));
