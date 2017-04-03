
<<<<<<< HEAD
/* First created by JCasGen Fri Mar 31 17:13:38 CDT 2017 */
=======
/* First created by JCasGen Tue Mar 28 15:35:13 CDT 2017 */
>>>>>>> 824d78e811eb738ee13aafa62eb8d4d21fab9847
package gov.va.vinci.leo.pcl.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import gov.va.vinci.leo.regex.types.RegularExpressionType_Type;

/** 
<<<<<<< HEAD
 * Updated by JCasGen Fri Mar 31 17:13:38 CDT 2017
=======
 * Updated by JCasGen Tue Mar 28 15:35:13 CDT 2017
>>>>>>> 824d78e811eb738ee13aafa62eb8d4d21fab9847
 * @generated */
public class CHF_Term_Type extends RegularExpressionType_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (CHF_Term_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = CHF_Term_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new CHF_Term(addr, CHF_Term_Type.this);
  			   CHF_Term_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new CHF_Term(addr, CHF_Term_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = CHF_Term.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("gov.va.vinci.leo.pcl.types.CHF_Term");



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public CHF_Term_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

  }
}



    