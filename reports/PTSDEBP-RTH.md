# PCL Score Concordance: NLP-Extracted TIU Notes vs. CDW Survey Domain

## Assessing the Feasibility of Augmenting Structured PCL Data with NLP-Extracted Scores from VA Clinical Notes

**Project:** ORD PTSDEBP-RTH — PCL Concordance Analysis  
**Author:** ABE (Adam.Batten@va.gov)

**Date:** February 2026


---

## Background

The VA's evidence-based psychotherapy (EBP) research program relies on PCL (PTSD Checklist) scores to measure treatment outcomes for Veterans with PTSD. The primary source for these scores is the CDW Mental Health Survey domain — a structured data repository where clinicians enter PCL results through standardized electronic instruments.

However, prior work estimated that approximately **28% of PCL-5 scores are only documented in the free text of clinical notes** (TIU — Text Integration Utilities), and never entered into the structured survey domain. If true, this would represent substantial underascertainment of PCL data, potentially limiting analytic cohort sizes and introducing selection bias into PTSD treatment research.

This project set out to determine whether natural language processing (NLP) extraction of PCL scores from TIU clinical notes could meaningfully supplement the structured CDW data in a subset of patients receiving EBP between January 2017 and September 2024.

## Study Design

### Data Sources

| Source | Records | Description |
|--------|---------|-------------|
| **CDW Survey Domain** | 715,845 | Structured PCL scores |
| **NLP TIU Extraction** | 116,140 | Scores extracted from clinical note text by NLP pipeline |
| **CDW PCL History** | 4,676,140 | Full historical CDW scores (no date restriction) for prior-match validation |

### EBP Cohort

The analysis was anchored to **125,047 EBP-eligible patients** with documented evidence-based psychotherapy episodes. The study design required one baseline PCL score (collected within 6 months prior to the first EBP) and one follow-up PCL score (within 3 months following the last EBP).

## Analytical Approach

### Phase 1: NLP Score Classification

Each of the 116,140 NLP-extracted scores was classified by examining its position within the source document, concordance with same-day CDW scores, and contextual indicators in the surrounding note text.

**Position classification:** Where the extracted score appeared within its clinical note.

**Score classification hierarchy:**

| Classification | Count | Description |
|---------------|-------|-------------|
| Concordant | 17,584 | Matches a same-day CDW score |
| Only-LikelyCurrent | 13,670 | Single-score document, no historical indicators |
| MultiDoc-Historical | 62,818 | From multi-score document (imported template) |
| PriorMatch-Historical | 16,990 | Score matches a prior CDW score (historical reference) |
| Keyword-Historical | 3,179 | Contains historical keywords |
| EmbeddedDate-Historical | 1,880 | Contains embedded date patterns |
| NumberedPCL-Historical | 19 | Contains numbered PCL sequences |

```sql
, CASE WHEN Extended_Snippet LIKE '%prior%' 
    OR Extended_Snippet LIKE '%previous%'
    OR Extended_Snippet LIKE '%baseline%'
    OR Extended_Snippet LIKE '%history%'
    OR Extended_Snippet LIKE '%initial%'
    OR Extended_Snippet LIKE '%last visit%'
    OR Extended_Snippet LIKE '%last session%'
  THEN 1 ELSE 0 
  END AS HasHistoricalKeyword
, CASE WHEN Extended_Snippet LIKE '%[0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9]%'
    OR Extended_Snippet LIKE '%[0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9]%'
    OR Extended_Snippet LIKE '%[0-9][0-9]-[0-9][0-9]-[0-9][0-9][0-9][0-9]%'
    OR Extended_Snippet LIKE '%[0-9][0-9]/20[0-9][0-9]%'
    OR Extended_Snippet LIKE '%[0-9]/20[0-9][0-9]%'
    OR Extended_Snippet LIKE '%January 20[0-9][0-9]%'
    OR Extended_Snippet LIKE '%February 20[0-9][0-9]%'
    OR Extended_Snippet LIKE '%March 20[0-9][0-9]%'
    OR Extended_Snippet LIKE '%April 20[0-9][0-9]%'
    OR Extended_Snippet LIKE '%May 20[0-9][0-9]%'
    OR Extended_Snippet LIKE '%June 20[0-9][0-9]%'
    OR Extended_Snippet LIKE '%July 20[0-9][0-9]%'
    OR Extended_Snippet LIKE '%August 20[0-9][0-9]%'
    OR Extended_Snippet LIKE '%September 20[0-9][0-9]%'
    OR Extended_Snippet LIKE '%October 20[0-9][0-9]%'
    OR Extended_Snippet LIKE '%November 20[0-9][0-9]%'
    OR Extended_Snippet LIKE '%December 20[0-9][0-9]%'
    OR Extended_Snippet LIKE '%Jan 20[0-9][0-9]%'
    OR Extended_Snippet LIKE '%Feb 20[0-9][0-9]%'
    OR Extended_Snippet LIKE '%Mar 20[0-9][0-9]%'
    OR Extended_Snippet LIKE '%Apr 20[0-9][0-9]%'
    OR Extended_Snippet LIKE '%Jun 20[0-9][0-9]%'
    OR Extended_Snippet LIKE '%Jul 20[0-9][0-9]%'
    OR Extended_Snippet LIKE '%Aug 20[0-9][0-9]%'
    OR Extended_Snippet LIKE '%Sep 20[0-9][0-9]%'
    OR Extended_Snippet LIKE '%Oct 20[0-9][0-9]%'
    OR Extended_Snippet LIKE '%Nov 20[0-9][0-9]%'
    OR Extended_Snippet LIKE '%Dec 20[0-9][0-9]%'
  THEN 1 ELSE 0
  END AS HasEmbeddedDate
, CASE WHEN Extended_Snippet LIKE '%PCL[1-3]%'
    OR Extended_Snippet LIKE '%PCL[6-9]%'
    OR Extended_Snippet LIKE '%PCL [1-3]%'
    OR Extended_Snippet LIKE '%PCL [6-9]%'
    OR Extended_Snippet LIKE '%PCL#[1-3]%'
    OR Extended_Snippet LIKE '%PCL#[6-9]%'
    OR Extended_Snippet LIKE '%1st PCL%'
    OR Extended_Snippet LIKE '%2nd PCL%'
    OR Extended_Snippet LIKE '%3rd PCL%'
    OR Extended_Snippet LIKE '%[4-9]th PCL%'
    OR Extended_Snippet LIKE '%PCL%#1%'
    OR Extended_Snippet LIKE '%PCL%#2%'
    OR Extended_Snippet LIKE '%PCL%#3%'
  THEN 1 ELSE 0
  END AS HasNumberedPCL
```
### Phase 2: Concordance Rates by Position

The key finding emerged early: **document structure was the primary determinant of score validity**. Multi-score documents were confirmed via text mining to contain **imported Measurement-Based Care (MBC) dashboards and tracking grids** — not narrative clinical text with a current score. Discriminating terms were structural patterns like "pcl pcl pcl" and "total pcl total" rather than temporal keywords.

```r
snippetLower = str_to_lower(Extended_Snippet)
  # MBC Dashboard patterns
  , hasMBC = str_detect(snippetLower, "mbc|measurement.?based")
  , hasSession = str_detect(snippetLower, "session\\s*\\d|session\\s*pcl")
  , hasGrid = str_detect(snippetLower, "(pcl|phq|gad|bdi)\\s*(pcl|phq|gad|bdi)")
  , hasSeverityLabel = str_detect(snippetLower, "(severe|moderate|mild|minimal)\\s*(last|pcl|phq)")
  , hasTotal = str_detect(snippetLower, "total\\s*pcl|pcl\\s*total")
  # Date patterns
  , hasDate = str_detect(snippetLower, "\\d{1,2}/\\d{1,2}/\\d{2,4}")
  # Comparison language
  , hasCompared = str_detect(snippetLower, "compared|decrease|improvement|down from")
  # Tabular structure (multiple colons or repeated measure names)
  , hasTabular = str_detect(snippetLower, "pcl.*pcl.*pcl|phq.*phq|gad.*gad|bdi.*bdi")
```

### Phase 3: Design Impact Assessment

After excluding historical scores and validating single-score documents, the remaining **13,670 NLP scores** (Only-LikelyCurrent) were combined with CDW scores to estimate potential cohort expansion:

| Current N | Potential Gain (pre-exclusion) | After Exclusions | % Increase |
|-----------|-------------------------------|------------------|------------|
| 77,441 | +3,330 | **+1,338** | **+1.7%** |

The 3,330 initially identified patients were reduced to **1,338** after applying study exclusion criteria (group/both-format EBP, missing data, etc.).

### Phase 4: PCL Version Type Validation

The NLP pipeline labeled scores with various type tags:

| NLP Type | Description |
|----------|-------------|
| PCL | Generic — no version specified |
| PCL_5 | Labeled as PCL-5 |
| PCL_C | PCL-Civilian |
| PCL_M | PCL-Military |
| PCL_S | PCL-Specific |
| PCL4 / PCL5 | EMR-sourced (structured survey domain) |

For the 1,338 patients, a multi-layered classification was applied:

1. **Score-based**: Scores < 17 → probably PCL-5 (below PCL-4 floor); scores > 80 → probably PCL-4 (above PCL-5 ceiling)
2. **Keyword-based**: Snippet text searched for version-specific terms (e.g., "20-item," "DSM-5," "PCL-5")
3. **Combined classification**: EMR source > score range > keyword evidence > needs review

```r
## PCL-5 indicators
patternPCL5 <- regex("PCL[- ]?5|DSM[- ]?5|DSM[- ]?V|20[- ]?item|0[- ]?(to )?80", ignore_case = TRUE)

## PCL-4 indicators
patternPCL4 <- regex("PCL[- ]?4|DSM[- ]?IV|17[- ]?item|17[- ]?(to )?85|1[- ]?(to )?5 scale", ignore_case = TRUE)

## Subscale indicators (PCL-4 specific terms)
patternPCL4Subscale <- regex("re-?experiencing|hyperarousal|numbing", ignore_case = TRUE)

## Subscale indicators (PCL-5 specific terms)
patternPCL5Subscale <- regex("intrusion|negative cognitions|negative alterations", ignore_case = TRUE)

## EMR types have no snippets - classify directly from score type
emrTypes <- c("PCL4", "PCL5")

## Helper function for keyword-based classification from snippet text
f_keywordClassify <- function(snippetText) {
  case_when(
    is.na(snippetText) ~ "No-Snippet"
    , str_detect(snippetText, patternPCL5) & !str_detect(snippetText, patternPCL4) ~ "Keyword-PCL5"
    , str_detect(snippetText, patternPCL4) & !str_detect(snippetText, patternPCL5) ~ "Keyword-PCL4"
    , str_detect(snippetText, patternPCL5) & str_detect(snippetText, patternPCL4) ~ "Keyword-Conflict"
    , str_detect(snippetText, patternPCL5Subscale) & !str_detect(snippetText, patternPCL4Subscale) ~ "Subscale-PCL5"
    , str_detect(snippetText, patternPCL4Subscale) & !str_detect(snippetText, patternPCL5Subscale) ~ "Subscale-PCL4"
    , TRUE ~ "No-Keyword"
  )
}
```

## Critical Finding: NLP Extraction of Survey Descriptions

**Manual review of the validation sample revealed a fundamental problem with NLP-extracted scores.**

The NLP pipeline was designed to extract PCL scores from clinical notes by identifying numeric values near PCL-related terms. However, many clinical notes contain **boilerplate descriptions of the PCL instrument itself**, such as:

> *"The PCL is a **17**-item self-report measure"*  
> *"The PCL is a **20**-item self-report measure"*

The NLP algorithm interpreted these item counts as PCL scores — producing extracted "scores" of 17 or 20 that were actually descriptions of the survey instrument. This pattern was pervasive in the validation sample:

- **Generic "PCL" type with score of 17**: Nearly all were the phrase "The PCL is a 17-item self-report measure" — the number 17 (item count for PCL-4) was misidentified as a score
- **Generic "PCL" type with score of 20**: Nearly all were "The PCL is a 20-item self-report measure" — the number 20 (item count for PCL-5) was misidentified as a score
- **"Keyword-Conflict" cases**: Notes containing "PTSD CHECKLIST - CIVILIAN FORM (PCL-5)" followed by "The PCL is a 17-item, face-valid, self-report measure" — mixing PCL-5 headers with PCL-4 instrument descriptions in the same template

This artifact was not limited to edge cases. Among the generic "PCL" type (the largest NLP-extracted category), **only 10.6% could be automatically validated** through score range or keyword evidence. The vast majority (780 of 880 in the snippet analysis, 88.6%) fell into the "NeedsReview-NoEvidence" category — scores with no contextual evidence that they represent actual patient assessments rather than instrument metadata.

By contrast, the "PCL_5" type — where the NLP algorithm explicitly detected a PCL-5 label — achieved **99.8% automated validation**, confirming that when the NLP pipeline had explicit version information, it performed well.

## Conclusions

### What Worked

1. **CDW concordance validation** successfully identified 17,584 NLP scores that matched structured data, confirming the NLP pipeline can detect real scores
2. **Position-based filtering** correctly identified that multi-score documents (~63.7% of non-concordant scores) contain imported MBC templates, not current assessments
3. **Historical indicator detection** flagged an additional ~22% of scores as prior references through keyword and date-pattern analysis
4. **Explicitly labeled PCL-5 extractions** (`PCL_5` type) were highly reliable (99.8% validated)

### Why the Project Is Being Concluded

The fundamental limitation is that the NLP extraction pipeline **conflates survey instrument descriptions with actual scores**. The numbers appearing in standard clinical note templates describing the PCL instrument (17 items for PCL-4, 20 items for PCL-5) are extracted as patient scores. This creates a systematic source of false positives that cannot be reliably distinguished from true scores through automated means alone.

The practical impact for cohort expansion is modest:

- **Cohort gain of +1.7%** — After all study exclusions, only 1,338 of the initial 3,330 patients remained eligible, and version-type ambiguity plus instrument-description contamination undermine confidence in these scores
- The gains do not justify the validation burden required to separate genuine scores from NLP artifacts

### Implications for VA NLP-Based PCL Research

1. **NLP pipelines extracting PCL scores from clinical notes must implement exclusion rules** for instrument description text (e.g., "N-item self-report measure" patterns)
2. **Multi-score documents should be excluded entirely** — they contain imported MBC dashboards, not current assessments
3. **The CDW Mental Health Survey domain remains the most reliable source** for PCL scores in VA research
4. **The original estimate that "28% of PCL-5 scores are only in free text"** may overstate the true rate of unique, valid scores — a substantial portion of NLP-detected "scores" are instrument metadata, historical references, or template artifacts

### Limitations

This analysis focused on a specific subset of Veterans — those receiving evidence-based psychotherapy for PTSD with treatment episodes beginning in January 2017 or later. Concordance rates, NLP extraction accuracy, and the magnitude of potential cohort gains may differ for other patient populations, clinical settings, or temporal windows. In particular, note templates, MBC dashboard formats, and clinician documentation practices have evolved over time, meaning that NLP performance observed in this 2017–2024 cohort may not generalize to earlier or later periods.

---

*This work was conducted as part of the ORD PTSDEBP-RTH project examining evidence-based psychotherapy outcomes for Veterans with PTSD. All analyses used de-identified data within VA secure environments.*
