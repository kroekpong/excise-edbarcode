//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.05 at 09:56:46 PM ICT 
//


package th.go.excise.edbarcode.ws.provider.oxm;

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
 *         &lt;element name="SR12011ReportData" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "sr12011ReportData",
    "referenceNumber"
})
@XmlRootElement(name = "EbarcodeGetSR12011ReportRequest")
public class EbarcodeGetSR12011ReportRequest {

    @XmlElement(name = "SR12011ReportData", required = true)
    protected String sr12011ReportData;
    @XmlElement(name = "ReferenceNumber", required = true)
    protected String referenceNumber;

    /**
     * Gets the value of the sr12011ReportData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSR12011ReportData() {
        return sr12011ReportData;
    }

    /**
     * Sets the value of the sr12011ReportData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSR12011ReportData(String value) {
        this.sr12011ReportData = value;
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