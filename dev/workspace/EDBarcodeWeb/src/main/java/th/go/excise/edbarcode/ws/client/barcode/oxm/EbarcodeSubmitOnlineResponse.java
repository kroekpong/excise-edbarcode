//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.11 at 12:50:04 AM ICT 
//


package th.go.excise.edbarcode.ws.client.barcode.oxm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SubmitOnlineStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SubmitOnlineDesc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ReferenceNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "submitOnlineStatus",
    "submitOnlineDesc",
    "referenceNumber"
})
@XmlRootElement(name = "EbarcodeSubmitOnlineResponse")
public class EbarcodeSubmitOnlineResponse {

    @XmlElement(name = "SubmitOnlineStatus", required = true)
    protected String submitOnlineStatus;
    @XmlElement(name = "SubmitOnlineDesc", required = true)
    protected String submitOnlineDesc;
    @XmlElement(name = "ReferenceNumber", required = true)
    protected String referenceNumber;

    /**
     * Gets the value of the submitOnlineStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubmitOnlineStatus() {
        return submitOnlineStatus;
    }

    /**
     * Sets the value of the submitOnlineStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubmitOnlineStatus(String value) {
        this.submitOnlineStatus = value;
    }

    /**
     * Gets the value of the submitOnlineDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubmitOnlineDesc() {
        return submitOnlineDesc;
    }

    /**
     * Sets the value of the submitOnlineDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubmitOnlineDesc(String value) {
        this.submitOnlineDesc = value;
    }

    /**
     * Gets the value of the referenceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceNumber() {
        return referenceNumber;
    }

    /**
     * Sets the value of the referenceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceNumber(String value) {
        this.referenceNumber = value;
    }

}
