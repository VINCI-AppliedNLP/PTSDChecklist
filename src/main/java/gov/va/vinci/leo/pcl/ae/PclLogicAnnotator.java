package gov.va.vinci.leo.pcl.ae;

/*
 * #%L
 * Angina project
 * %%
 * Copyright (C) 2010 - 2014 Department of Veterans Affairs
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import gov.va.vinci.leo.ae.LeoBaseAnnotator;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;
import gov.va.vinci.leo.pcl.types.Logic;
import gov.va.vinci.leo.pcl.types.PCL_score;
import gov.va.vinci.leo.pcl.types.Term;
import gov.va.vinci.leo.pcl.types.TermPattern;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

/**
 * @author vhaslcalbap
 * @author vhaslcpatteo
 */
public class PclLogicAnnotator extends LeoBaseAnnotator {

    @Override
    public void initialize(UimaContext context) throws ResourceInitializationException {
        super.initialize(context);
    }

    @Override

    public void annotate(JCas aJCas) throws AnalysisEngineProcessException {
        FSIterator<Annotation> patternList = this.getAnnotationListForType(aJCas, TermPattern.class.getCanonicalName());
        try {
            while (patternList.hasNext()) {
                TermPattern p = (TermPattern) patternList.next();
                Logic outAnnotation = (Logic) this.addOutputAnnotation(Logic.class.getCanonicalName(),
                        aJCas, p.getBegin(), p.getEnd());
                String value = "";
                String term_value = "";


                if (p.getTarget() != null) {
                    if (p.getTarget() instanceof Term) {
                        Term pr = (Term) p.getTarget();
                        if (pr.getConcept() != null) {
                            term_value = pr.getConcept();


                        }
                    }

                    if (p.getAnchor() != null) {
                        if (p.getAnchor() instanceof PCL_score) {
                            PCL_score c = (PCL_score) p.getAnchor();
                                value = c.getCoveredText();
                        }
                    }

                    outAnnotation.setPCL_Value(value);
                    outAnnotation.setTerm_Value(term_value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new AnalysisEngineProcessException(e);
        }

    }

    @Override
    public LeoTypeSystemDescription getLeoTypeSystemDescription() {
        return new LeoTypeSystemDescription();
    }


}
