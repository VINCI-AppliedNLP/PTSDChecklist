## PTSDChecklist

NLP System to extract PCL scores from clinic notes. The PTSD Checklist (PCL) is a standardized self-report rating scale for PTSD comprising of several items that correspond to the key symptoms of PTSD.
Three types of the PCL exist:  1) PCL-M (PCL-Military) is specific to PTSD caused by military experiences; 2) PCL-C (PCL-Civilian) is applied generally to any traumatic event; and 3) PCL-S (PCL-Specific) is applied to a specific stressful life situation.
There are also two versions of PCL: 1) PCL-4 based on DSM-IV, which was in use prior to 2013 and 2) PCL-5 based on DSM-5, which came in effect in 2013.
The goal of this project is to apply Natural Language Processing (NLP) techniques to extract mentions of the specific PCL administered to a patient and the total score that was acquired as a result.

### Example values extracted: 

“PCL score = 77.”

“PCL score was a 55.”

“PCL): 65”

“PCL-S score of 62”

“PCL=41”

“PCL-5): current score of 58”

“PCL was administered; score was 55.”

“PCL-M score = 75”

“PCL-S) yielded a score of 67%”

“PCL-S, getting a score of 39”

### Validation  
This system was validated for precision with 1000 random notes from Psycotherapy visits being selected.

 It was validated to reach 98% positive predictive value. We did not measure sensitivity. 
We found PCL values in approximately 65% of these documents.

* REMARK: BFG removed ORD in the history
An IE system for the PTSD Checklist Score(PCL)

