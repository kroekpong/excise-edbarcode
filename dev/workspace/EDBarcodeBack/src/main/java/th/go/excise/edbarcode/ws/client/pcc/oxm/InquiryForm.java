//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.11 at 01:06:24 AM ICT 
//


package th.go.excise.edbarcode.ws.client.pcc.oxm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InquiryForm complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InquiryForm">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TaxpayerInformation" type="{http://www.excise.go.th/xsd/barcode}TaxpayerInformation" minOccurs="0"/>
 *         &lt;element name="SubmissionInformation" type="{http://www.excise.go.th/xsd/barcode}SubmissionInformation" minOccurs="0"/>
 *         &lt;element name="FormInformation" type="{http://www.excise.go.th/xsd/barcode}FormInformation" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InquiryForm", propOrder = {
    "taxpayerInformation",
    "submissionInformation",
    "formInformation"
})
public class InquiryForm {

    @XmlElement(name = "TaxpayerInformation")
    protected TaxpayerInformation taxpayerInformation;
    @XmlElement(name = "SubmissionInformation")
    protected SubmissionInformation submissionInformation;
    @XmlElement(name = "FormInformation")
    protected FormInformation formInformation;

    /**
     * Gets the value of the taxpayerInformation property.
     * 
     * @return
     *     possible object is
     *     {@link TaxpayerInformation }
     *     
     */
    public TaxpayerInformation getTaxpayerInformation() {
        return taxpayerInformation;
    }

    /**
     * Sets the value of the taxpayerInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxpayerInformation }
     *     
     */
    public void setTaxpayerInformation(TaxpayerInformation value) {
        this.taxpayerInformation = value;
    }

    /**
     * Gets the value of the submissionInformation property.
     * 
     * @return
     *     possible object is
     *     {@link SubmissionInformation }
     *     
     */
    public SubmissionInformation getSubmissionInformation() {
        return submissionInformation;
    }

    /**
     * Sets the value of the submissionInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubmissionInformation }
     *     
     */
    public void setSubmissionInformation(SubmissionInformation value) {
        this.submissionInformation = value;
    }

    /**
     * Gets the value of the formInformation property.
     * 
     * @return
     *     possible object is
     *     {@link FormInformation }
     *     
     */
    public FormInformation getFormInformation() {
        return formInformation;
    }

    /**
     * Sets the value of the formInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link FormInformation }
     *     
     */
    public void setFormInformation(FormInformation value) {
        this.formInformation = value;
    }

}
