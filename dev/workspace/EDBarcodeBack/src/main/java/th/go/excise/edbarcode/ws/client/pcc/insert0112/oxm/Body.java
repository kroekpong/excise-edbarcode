//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.10 at 11:32:33 AM ICT 
//


package th.go.excise.edbarcode.ws.client.pcc.insert0112.oxm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Body complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Body">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="poso112FormInfo" type="{http://bcsservice.pccth.com/}POSO0112FormInfo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Body", propOrder = {
    "poso112FormInfo"
})
public class Body {

    @XmlElement(required = true, nillable = true)
    protected POSO0112FormInfo poso112FormInfo;

    /**
     * Gets the value of the poso112FormInfo property.
     * 
     * @return
     *     possible object is
     *     {@link POSO0112FormInfo }
     *     
     */
    public POSO0112FormInfo getPoso112FormInfo() {
        return poso112FormInfo;
    }

    /**
     * Sets the value of the poso112FormInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link POSO0112FormInfo }
     *     
     */
    public void setPoso112FormInfo(POSO0112FormInfo value) {
        this.poso112FormInfo = value;
    }

}
