import gov.va.vinci.leo.regex.types.RegularExpressionType

/* An arbitrary name for this annotator. Used in the pipeline for the name of this annotation. */
name = "MyRegexAnnotator"

configuration {
    /* All configuration for this annotator. */
    defaults {
        /* Global for all configrations below if a property specified here is not overridden in a section below. */
        //keep the same (for patrick)
        outputType = RegularExpressionType.class.canonicalName
        case_sensitive = false
        matchedPatternFeatureName = "pattern"
        concept_feature_name = "concept"
        groupFeatureName = "group"
    }

    /* An arbitrary name for this set of patterns/config. */

    "PCL_concept" {
        expressions = [
                'PCL'
        ]
        concept_feature_value = "PCL"
        outputType = "gov.va.vinci.leo.pcl.types.Term"
    }
    "PCLM_concept" {
        expressions = [
                'PCL-M',
                'PCL M'
        ]
        concept_feature_value = "PCL-M"
        outputType = "gov.va.vinci.leo.pcl.types.Term"
    }
    "PCLC_concept" {
        expressions = [
                'PCL-C',
                'PCLC',
                'PCL\\s*C'
        ]
        concept_feature_value = "PCL_C"
        outputType = "gov.va.vinci.leo.pcl.types.Term"
    }
    "PCLS_concept" {
        expressions = [
            'PCL-S',
            'PCL\\s*S'


    ]
    concept_feature_value = "PCL_S"
    outputType = "gov.va.vinci.leo.pcl.types.Term"
    }
    "PCL4_concept" {
        expressions = [
            'PCL-4',
            'PCL\\s*4'


    ]
    concept_feature_value = "PCL_4"
    outputType = "gov.va.vinci.leo.pcl.types.Term"
    }
    "PCL5_concept" {
        expressions = [
            'PCL-5',
            'PCL\\s*5'


    ]
    concept_feature_value = "PCL_5"
    outputType = "gov.va.vinci.leo.pcl.types.Term"
}

    "CHF_concept" {
        /* This concept is similar to NYHA concept,
        however more restrictive in the Pattern Annotator
        due to the Imprecise nature of the terms.

        */
        expressions = [
                'C?HF',
                'heart\\s*failure',
                '(s|d)\\s*C?hf',
                'functional\\s*status',
                'hfref',
                'heart\\s*failure\\s*stage'

        ]

        outputType = "gov.va.vinci.leo.pcl.types.CHF_Term"
    }

    //I had to include middle words in some of the NYHA_concept

    // patterns in order to restrict the types of patterns that are allowed
    // for terms that are too common.(CHF_TERM)

    "PCL_Middle" {
        expressions = ['class',
                       '-class',
                       'grade',
                       'score',
                       'stage',
                       '\\bis\\b',
                       'classification',
                        '\bwas\b',
                        'score\\s*was\b',
                        'wiht\\s*score\\s*of',
                        'class/stage'
        ]

        concept_feature_value = "NYHA_Middle"
        outputType = "gov.va.vinci.leo.pcl.types.Middle"
    }

    "PCL_score" {
        expressions = ['\\d\\d' ]
        concept_feature_value = "NYHA_1"
        outputType = "gov.va.vinci.leo.pcl.types.PCL_score"
    }


    "Exclude" {
        expressions = [
                'CCS',
                'canadian\\s*card\\w+\\s*society\\s*functional\\s*class',
                '\\d{3,}',
                '\\d+.\\d{2,}',
                ': *\\[\\] *yes,?',
                '\\s*episodes?',
                '\\s*times',
                '\\s*nights?',
                '\\s*/?days?',
                '\\s*/?weeks?',
                '\\s*/?wks?',
                '\\s*months?',
                '\\s*minutes?',
                '\\s*years?',
                '\\s*MO\\.?',
                'x *(/ *)?(weeks?|wks?)', //  2x/week
                '.\\dx?/?\\s*(time|week|wk|day|month|minute|y)', //  2-3x/week
                '2/2',
                '%',
                '-[5-9]',
                //Irrelevant Measurements and dates
                '\\d\\d/\\d\\d+',
                '\\d+\\.\\d+',
                '\\d+.\\d+.\\d+',
                '\\( ?\\d+ ?\\) ?((\\d+|\\-|\\*) ?)+',
                'smtwthf',
                '\\d{1,4}\\)',
                //'systolic',
                //'diastolic',
                'functional\\s*problem\\s*solving',
               // 'shf',
                '\\[\\s*\\]\\s*NYHA\\s*Class',
                '2\\s*vessel\\s*disease',
                'functional\\s*status',
                'CHADS2?\\s*score',
                'MADIT\\s*HF',
                'CKD\\s*stage',
                'cfc-f',
                'FCC',
                'FCR',
                'ccs\\s*functional\\s*\\)\\s*class',
                //Specific template found
                'Not\\s*NYHA\\s*FC',
                'ONE OF PATIENT\'S DISCHARGE'


        ]
        concept_feature_value = "Exclude"
        outputType = "gov.va.vinci.leo.pcl.types.Exclude"
    }
}

