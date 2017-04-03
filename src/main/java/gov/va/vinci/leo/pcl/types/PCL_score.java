

<<<<<<< HEAD
/* First created by JCasGen Fri Mar 31 17:13:38 CDT 2017 */
=======
/* First created by JCasGen Tue Mar 28 15:35:13 CDT 2017 */
>>>>>>> 824d78e811eb738ee13aafa62eb8d4d21fab9847
package gov.va.vinci.leo.pcl.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import gov.va.vinci.leo.regex.types.RegularExpressionType;


/** 
<<<<<<< HEAD
 * Updated by JCasGen Fri Mar 31 17:13:38 CDT 2017
 * XML source: C:/Users/VHASLC~4/AppData/Local/Temp/2/leoTypeDescription_03c2e3b4-fa52-44ae-be27-19217264b6eb9055840640407033550.xml
=======
 * Updated by JCasGen Tue Mar 28 15:35:13 CDT 2017
 * XML source: C:/Users/VHASLC~1/AppData/Local/Temp/5/leoTypeDescription_f4bc64bd-97ea-4254-b58e-a1e80616ddca2966411619069272335.xml
>>>>>>> 824d78e811eb738ee13aafa62eb8d4d21fab9847
 * @generated */
public class PCL_score extends RegularExpressionType {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(PCL_score.class);
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
  protected PCL_score() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public PCL_score(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public PCL_score(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public PCL_score(JCas jcas, int begin, int end) {
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
     
}

    