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
 * <p>Java class for FundList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FundList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FundEntry" type="{http://www.excise.go.th/xsd/barcode}FundEntry"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FundList", propOrder = {
    "fundEntry"
})
public class FundList {

    @XmlElement(name = "FundEntry", required = true)
    protected FundEntry fundEntry;

    /**
     * Gets the value of the fundEntry property.
     * 
     * @return
     *     possible object is
     *     {@link FundEntry }
     *     
     */
    public FundEntry getFundEntry() {
        return fundEntry;
    }

    /**
     * Sets the value of the fundEntry property.
     * 
     * @param value
     *     allowed object is
     *     {@link FundEntry }
     *     
     */
    public void setFundEntry(FundEntry value) {
        this.fundEntry = value;
    }

}