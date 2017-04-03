
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
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
<<<<<<< HEAD
 * Updated by JCasGen Fri Mar 31 17:13:38 CDT 2017
=======
 * Updated by JCasGen Tue Mar 28 15:35:13 CDT 2017
>>>>>>> 824d78e811eb738ee13aafa62eb8d4d21fab9847
 * @generated */
public class Logic_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Logic_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Logic_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Logic(addr, Logic_Type.this);
  			   Logic_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Logic(addr, Logic_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Logic.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("gov.va.vinci.leo.pcl.types.Logic");
 
  /** @generated */
  final Feature casFeat_PCL_Value;
  /** @generated */
  final int     casFeatCode_PCL_Value;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getPCL_Value(int addr) {
        if (featOkTst && casFeat_PCL_Value == null)
      jcas.throwFeatMissing("PCL_Value", "gov.va.vinci.leo.pcl.types.Logic");
    return ll_cas.ll_getStringValue(addr, casFeatCode_PCL_Value);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setPCL_Value(int addr, String v) {
        if (featOkTst && casFeat_PCL_Value == null)
      jcas.throwFeatMissing("PCL_Value", "gov.va.vinci.leo.pcl.types.Logic");
    ll_cas.ll_setStringValue(addr, casFeatCode_PCL_Value, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Term_Value;
  /** @generated */
  final int     casFeatCode_Term_Value;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getTerm_Value(int addr) {
        if (featOkTst && casFeat_Term_Value == null)
      jcas.throwFeatMissing("Term_Value", "gov.va.vinci.leo.pcl.types.Logic");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Term_Value);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTerm_Value(int addr, String v) {
        if (featOkTst && casFeat_Term_Value == null)
      jcas.throwFeatMissing("Term_Value", "gov.va.vinci.leo.pcl.types.Logic");
    ll_cas.ll_setStringValue(addr, casFeatCode_Term_Value, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Logic_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_PCL_Value = jcas.getRequiredFeatureDE(casType, "PCL_Value", "uima.cas.String", featOkTst);
    casFeatCode_PCL_Value  = (null == casFeat_PCL_Value) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_PCL_Value).getCode();

 
    casFeat_Term_Value = jcas.getRequiredFeatureDE(casType, "Term_Value", "uima.cas.String", featOkTst);
    casFeatCode_Term_Value  = (null == casFeat_Term_Value) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Term_Value).getCode();

  }
}



    