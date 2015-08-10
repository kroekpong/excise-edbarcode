//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.11 at 01:06:24 AM ICT 
//


package th.go.excise.edbarcode.ws.client.pcc.oxm;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FormInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FormInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FormType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FormCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FormEffectiveDate" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="FormReferenceNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FormData" type="{http://www.excise.go.th/xsd/barcode}FormData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FormInformation", propOrder = {
    "formType",
    "formCode",
    "formEffectiveDate",
    "formReferenceNumber",
    "formData"
})
public class FormInformation {

    @XmlElement(name = "FormType")
    protected String formType;
    @XmlElement(name = "FormCode", required = true)
    protected String formCode;
    @XmlElement(name = "FormEffectiveDate", required = true)
    protected BigDecimal formEffectiveDate;
    @XmlElement(name = "FormReferenceNumber", required = true)
    protected String formReferenceNumber;
    @XmlElement(name = "FormData")
    protected FormData formData;

    /**
     * Gets the value of the formType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormType() {
        return formType;
    }

    /**
     * Sets the value of the formType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormType(String value) {
        this.formType = value;
    }

    /**
     * Gets the value of the formCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormCode() {
        return formCode;
    }

    /**
     * Sets the value of the formCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormCode(String value) {
        this.formCode = value;
    }

    /**
     * Gets the value of the formEffectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFormEffectiveDate() {
        return formEffectiveDate;
    }

    /**
     * Sets the value of the formEffectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFormEffectiveDate(BigDecimal value) {
        this.formEffectiveDate = value;
    }

    /**
     * Gets the value of the formReferenceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormReferenceNumber() {
        return formReferenceNumber;
    }

    /**
     * Sets the value of the formReferenceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormReferenceNumber(String value) {
        this.formReferenceNumber = value;
    }

    /**
     * Gets the value of the formData property.
     * 
     * @return
     *     possible object is
     *     {@link FormData }
     *     
     */
    public FormData getFormData() {
        return formData;
    }

    /**
     * Sets the value of the formData property.
     * 
     * @param value
     *     allowed object is
     *     {@link FormData }
     *     
     */
    public void setFormData(FormData value) {
        this.formData = value;
    }

}
