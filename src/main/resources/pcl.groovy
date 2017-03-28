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
                'PCL',
                'PCL-score',
                'Post(\\s*|-)Traumatic\\s*checklist',
                'Post(\\s*|-)Traumatic\\s*checklist\\s\\(PCL\\)',
                'PostTraumatic\\s*checklist\\s*\\(PCL\\)',
                'PTSD\\s*Checklist'
        ]
        concept_feature_value = "PCL"
        outputType = "gov.va.vinci.leo.pcl.types.Term"
    }
    "PCLM_concept" {
        expressions = [
                'PCL-M',
                'PCL\\s*M',
                'PCL\\s*-\\s*M',
                'PCLM',
                'PCL(-|\\s*)Military',
                'Disorder\\s*Checklist-M\\s*Version\\s*\\(PCL\\)\\s*assessment',
                'PTSD\\s*Checklist(-|\\s*)Military',
                'PTSD\\s*Checklist\\s*\\(PCL(-|\\s*)M\\)',
                'Post(\\s*|-)Traumatic\\s*stress\\s*disorder\\s*checklist(\\s*|\\s*-\\s*)Military\\s*(\\(PCL(\\s*|\\s*-\\s*)M\\))?'
        ]
        concept_feature_value = "PCL_M"
        outputType = "gov.va.vinci.leo.pcl.types.Term"
    }
    "PCLC_concept" {
        expressions = [
                'PCL-C',
                'PCL\\s*-\\s*C',
                'PCLC',
                'PCL\\s*C',
                'PCL(-|\\s*)Civilian',
                'Disorder\\s*Checklist-C\\s*Version\\s*\\(PCL\\)\\s*assessment',
                'PTSD\\s*Checklist\\s*\\(PCL(-|\\s*)C\\)',
                'PTSD\\s*Checklist(-|\\s*)Civilian',
                'Post(\\s*|-)Traumatic\\s*stress\\s*disorder\\s*checklist(\\s*|\\s*-\\s*)Civilian\\s*(\\(PCL(\\s*|\\s*-\\s*)C\\))?'

        ]
        concept_feature_value = "PCL_C"
        outputType = "gov.va.vinci.leo.pcl.types.Term"
    }
    "PCLS_concept" {
        expressions = [
            'PCL-S',
            'PCL\\s*-\\s*S',
            'PCL\\s*S',
            'PCLS',
            'PCL(-|\\s*)Specific',
            'Disorder\\s*Checklist-S\\s*Version\\s*\\(PCL\\)\\s*assessment',
            'PTSD\\s*Checklist(-|\\s*)Specific',
            'PTSD\\s*Checklist\\s*\\(PCL(-|\\s*)S\\)',
            'Post(\\s*|-)Traumatic\\s*stress\\s*disorder\\s*checklist(\\s*|\\s*-\\s*)specific\\s*(\\(PCL(\\s*|\\s*-\\s*)S\\))?'

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

//    "CHF_concept" {
//        /* This concept is similar to NYHA concept,
//        however more restrictive in the Pattern Annotator
//        due to the Imprecise nature of the terms.
//
//        */
//        expressions = [
//                'C?HF',
//                'heart\\s*failure',
//                '(s|d)\\s*C?hf',
//                'functional\\s*status',
//                'hfref',
//                'heart\\s*failure\\s*stage'
//
//        ]
//
//        outputType = "gov.va.vinci.leo.pcl.types.CHF_Term"
//    }

    //I had to include middle words in some of the NYHA_concept

    // patterns in order to restrict the types of patterns that are allowed
    // for terms that are too common.(CHF_TERM)

    "PCL_Middle" {
        expressions = ['class',
                       '-class',
                       'grade',
                       'score(d)?',
                       'stage',
                        'total\\s*score',
                       '\\bis\\b',
                       'classification',
                        'was', //May need to make this a separate pattern rule instead depending on PR
                        '(level|score)\\s*(was|is|=|of)',
                        'with(\\s*a)?\\s*score\\s*of',
                        'class/stage',
                        'and\\s*scored\\s*a?',
                        'scoring\\s*a',
                        'of',
                        '(with\\s*)?results:\\s*total\\s*score\\s*of',
                        'and\\s*(his|her|their)?\\s*score\\s*was',
                        'scores\\s*a',
                        'today\\s*=',
                        'on\\s*the',
                        'rating\\s*of',
                        '(the\\s*)?veteran\\s*scored\\s*a',
                        'was\\s*given,?\\s*(with\\s*a)?\\s*(score|rating)(\\s*of)?',
                        'score\\s*today\\s*(was|is)',
                       'today\\s*(is|was)\\s*(a)?',
                        'today\\s*and\\s*scored\\s*a?',
                        'today\\s*with\\s*a\\s*(score|rating)\\s*of',
                        ';\\s*scored\\s*a',
                        'on\\s*this\\s*date\\s*was',
                        'was\\s*performed\\s*and\\s*was\\s*(positive|negative)\\.\\s*the\\s*score\\s*(was|is)\\s*',
                        '\\(score(-|\\s)',
                        'score\\s*for\\s*session\\s*\\d\\d?:',
                        ',?\\s*score\\s*=\\s*',
                        ',?\\s*(ths\\s*veteran)?scored\\s*a',
                        ',?yielded\\s(a\\s*)?score\\s*of',
                        'score\\s*this\\s*visit:?',
                        '(and\\s*)?obtained\\s*a\\s*(score|rating)\\s*(of)?',
                         'recording\\s*a\\s*score\\s*of',
                        'Today\\s*at',
                        'weekly\\s*w/score'




        ]

        concept_feature_value = "NYHA_Middle"
        outputType = "gov.va.vinci.leo.pcl.types.Middle"
    }

    "PCL_score" {
        expressions = ['\\d\\d' ]
        concept_feature_value = "Digit_Score"
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

