//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.10 at 10:41:37 AM ICT 
//


package th.go.excise.edbarcode.ws.client.barcode.submitonline.oxm;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FundEntryInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FundEntryInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FundType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FundAmt" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="FundRate" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="CreditAmt" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="NetAmt" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FundEntryInfo", propOrder = {
    "fundType",
    "fundAmt",
    "fundRate",
    "creditAmt",
    "netAmt"
})
public class FundEntryInfo {

    @XmlElement(name = "FundType", required = true)
    protected String fundType;
    @XmlElement(name = "FundAmt", required = true)
    protected BigDecimal fundAmt;
    @XmlElement(name = "FundRate", required = true)
    protected BigDecimal fundRate;
    @XmlElement(name = "CreditAmt", required = true)
    protected BigDecimal creditAmt;
    @XmlElement(name = "NetAmt", required = true)
    protected BigDecimal netAmt;

    /**
     * Gets the value of the fundType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFundType() {
        return fundType;
    }

    /**
     * Sets the value of the fundType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFundType(String value) {
        this.fundType = value;
    }

    /**
     * Gets the value of the fundAmt property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFundAmt() {
        return fundAmt;
    }

    /**
     * Sets the value of the fundAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFundAmt(BigDecimal value) {
        this.fundAmt = value;
    }

    /**
     * Gets the value of the fundRate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFundRate() {
        return fundRate;
    }

    /**
     * Sets the value of the fundRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFundRate(BigDecimal value) {
        this.fundRate = value;
    }

    /**
     * Gets the value of the creditAmt property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCreditAmt() {
        return creditAmt;
    }

    /**
     * Sets the value of the creditAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCreditAmt(BigDecimal value) {
        this.creditAmt = value;
    }

    /**
     * Gets the value of the netAmt property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNetAmt() {
        return netAmt;
    }

    /**
     * Sets the value of the netAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNetAmt(BigDecimal value) {
        this.netAmt = value;
    }

}
