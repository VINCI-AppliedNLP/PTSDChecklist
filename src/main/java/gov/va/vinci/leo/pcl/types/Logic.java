

/* First created by JCasGen Mon Jun 18 10:10:52 CDT 2018 */
package gov.va.vinci.leo.pcl.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Jun 18 10:10:52 CDT 2018
 * XML source: C:/Users/VHASLC~2/AppData/Local/Temp/6/leoTypeDescription_a8758c83-aa84-4b66-ab54-c6f6272a26807732608476961252042.xml
 * @generated */
public class Logic extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Logic.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Logic() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Logic(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Logic(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Logic(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: PCL_Value

  /** getter for PCL_Value - gets 
   * @generated
   * @return value of the feature 
   */
  public String getPCL_Value() {
    if (Logic_Type.featOkTst && ((Logic_Type)jcasType).casFeat_PCL_Value == null)
      jcasType.jcas.throwFeatMissing("PCL_Value", "gov.va.vinci.leo.pcl.types.Logic");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Logic_Type)jcasType).casFeatCode_PCL_Value);}
    
  /** setter for PCL_Value - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setPCL_Value(String v) {
    if (Logic_Type.featOkTst && ((Logic_Type)jcasType).casFeat_PCL_Value == null)
      jcasType.jcas.throwFeatMissing("PCL_Value", "gov.va.vinci.leo.pcl.types.Logic");
    jcasType.ll_cas.ll_setStringValue(addr, ((Logic_Type)jcasType).casFeatCode_PCL_Value, v);}    
   
    
  //*--------------*
  //* Feature: Term_Value

  /** getter for Term_Value - gets 
   * @generated
   * @return value of the feature 
   */
  public String getTerm_Value() {
    if (Logic_Type.featOkTst && ((Logic_Type)jcasType).casFeat_Term_Value == null)
      jcasType.jcas.throwFeatMissing("Term_Value", "gov.va.vinci.leo.pcl.types.Logic");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Logic_Type)jcasType).casFeatCode_Term_Value);}
    
  /** setter for Term_Value - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setTerm_Value(String v) {
    if (Logic_Type.featOkTst && ((Logic_Type)jcasType).casFeat_Term_Value == null)
      jcasType.jcas.throwFeatMissing("Term_Value", "gov.va.vinci.leo.pcl.types.Logic");
    jcasType.ll_cas.ll_setStringValue(addr, ((Logic_Type)jcasType).casFeatCode_Term_Value, v);}    
  }

    