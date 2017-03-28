import groovy.sql.Sql;
import groovy.util.XmlSlurper
import static groovy.json.JsonOutput.*

def jdbcConnectString = "jdbc:mysql://localhost:3306/elite?user=root&password=root";
def sql = Sql.newInstance(jdbcConnectString);

//-----------THESE NEED TO BE CHANGED-----------

// Add the path to the annotation schema xml file exported from ChartReview
SCHEMA_FILE_PATH = "EliteSchema.xml"

// Map the UIMA/Leo annotation types to the ChartReview classification types used
annotation_classification_map = [
        "gov.va.vinci.leo.sentence.types.AnchoredSentence" : "AnchoredSentence",
        "gov.va.vinci.leo.regex.types.RegularExpressionType" : "RegularExpressionType",
        "gov.va.vinci.leo.annotationpattern.types.AnnotationPatternType" : "AnnotationPatternType",
        "gov.va.vinci.leo.elite.types.Term" : "Term",
        "gov.va.vinci.leo.elite.types.TermPattern" : "TermPattern",
        "gov.va.vinci.leo.elite.types.TermContext" : "TermContext"
]

// Map the UIMA/Leo feature names to the ChartReview attribute names
feature_attribute_map = [
        "Anchor" : "Anchor",
        "pattern" : "pattern",
        "concept" : "concept",
        "group" : "group",
        "anchor" : "anchor",
        "anchorPattern" : "anchorPattern",
        "pattern" : "pattern",
        "target" : "target",
        "targetPattern" : "targetPattern",
        "Experiencer" : "Experiencer",
        "ExperiencerPattern" : "ExperiencerPattern",
        "Negation" : "Negation",
        "NegationPattern" : "NegationPattern",
        "Temporality" : "Temporality",
        "TemporalityPattern" : "TemporalityPattern",
        "Window" : "Window"
]
//-----------------------------------------------

annotation_to_siman_map = new HashMap<>()
feature_to_siman_map = new HashMap<>()

// XML parser
def schema = new XmlSlurper().parse(new File(SCHEMA_FILE_PATH))

schema.classDefs.classDef.each { item ->
    annotation_to_siman_map[item.name.toString()] = "annotationSchema:" + schema.id + ";classDef:" + item.@id
}

schema.attributeDefs.attributeDef.each { item ->
    s = "annotationSchema:" + schema.id + ";attributeDef:" + item.@id

    // Only update the tables if the attributes are not drop down values
    if (item.attributeDefOptionDefs.size() == 1) {
        feature_to_siman_map[item.name.toString()] = s
    }
//    else {
//        // Need to fix this
//    }
}


annotation_classification_map.each { k, v ->
    sql.executeUpdate("update annotation set annotation_type = '" + annotation_to_siman_map[v] + "' where annotation_type = '" + k + "'")
}

feature_attribute_map.each { k, v ->
    sql.executeUpdate("update feature set feature_type = '" + feature_to_siman_map[v] + "' where feature_type = '" + k + "'")
}