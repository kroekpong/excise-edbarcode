//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.11 at 02:03:07 PM ICT 
//


package th.go.excise.edbarcode.ws.provider.oxm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BinaryInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BinaryInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="XmlDataBinary" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BinaryInformation", propOrder = {
    "xmlDataBinary"
})
public class BinaryInformation {

    @XmlElement(name = "XmlDataBinary", required = true)
    protected String xmlDataBinary;

    /**
     * Gets the value of the xmlDataBinary property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXmlDataBinary() {
        return xmlDataBinary;
    }

    /**
     * Sets the value of the xmlDataBinary property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXmlDataBinary(String value) {
        this.xmlDataBinary = value;
    }

}
