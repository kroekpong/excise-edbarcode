//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.13 at 08:52:57 PM ICT 
//


package th.go.excise.edbarcode.ws.client.pcc.insert0112.oxm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Credit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Credit">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rtnCtlNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="credtiMoiTax" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="creditExciseTax" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Credit", propOrder = {
    "rtnCtlNo",
    "credtiMoiTax",
    "creditExciseTax"
})
public class Credit {

    @XmlElement(required = true, nillable = true)
    protected String rtnCtlNo;
    @XmlElement(required = true, nillable = true)
    protected String credtiMoiTax;
    @XmlElement(required = true, nillable = true)
    protected String creditExciseTax;

    /**
     * Gets the value of the rtnCtlNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRtnCtlNo() {
        return rtnCtlNo;
    }

    /**
     * Sets the value of the rtnCtlNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRtnCtlNo(String value) {
        this.rtnCtlNo = value;
    }

    /**
     * Gets the value of the credtiMoiTax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCredtiMoiTax() {
        return credtiMoiTax;
    }

    /**
     * Sets the value of the credtiMoiTax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCredtiMoiTax(String value) {
        this.credtiMoiTax = value;
    }

    /**
     * Gets the value of the creditExciseTax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditExciseTax() {
        return creditExciseTax;
    }

    /**
     * Sets the value of the creditExciseTax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditExciseTax(String value) {
        this.creditExciseTax = value;
    }

}
