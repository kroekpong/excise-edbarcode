//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.05 at 09:14:13 PM ICT 
//


package th.go.excise.edbarcode.ws.provider.oxm;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TaxpayerInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TaxpayerInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CompanyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Tin" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LicenseNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EffectiveDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ExpireDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TaxpayerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TaxpayerAddressInfo" type="{http://www.excise.go.th/xsd/barcode}TaxpayerAddressInfo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TaxpayerInfo", propOrder = {
    "companyName",
    "tin",
    "licenseNo",
    "effectiveDate",
    "expireDate",
    "taxpayerName",
    "taxpayerAddressInfo"
})
public class TaxpayerInfo {

    @XmlElement(name = "CompanyName", required = true)
    protected String companyName;
    @XmlElement(name = "Tin", required = true)
    protected String tin;
    @XmlElement(name = "LicenseNo", required = true)
    protected String licenseNo;
    @XmlElement(name = "EffectiveDate", required = true)
    protected String effectiveDate;
    @XmlElement(name = "ExpireDate", required = true)
    protected String expireDate;
    @XmlElement(name = "TaxpayerName", required = true)
    protected String taxpayerName;
    @XmlElement(name = "TaxpayerAddressInfo")
    protected List<TaxpayerAddressInfo> taxpayerAddressInfo;

    /**
     * Gets the value of the companyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets the value of the companyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyName(String value) {
        this.companyName = value;
    }

    /**
     * Gets the value of the tin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTin() {
        return tin;
    }

    /**
     * Sets the value of the tin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTin(String value) {
        this.tin = value;
    }

    /**
     * Gets the value of the licenseNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLicenseNo() {
        return licenseNo;
    }

    /**
     * Sets the value of the licenseNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLicenseNo(String value) {
        this.licenseNo = value;
    }

    /**
     * Gets the value of the effectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Sets the value of the effectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEffectiveDate(String value) {
        this.effectiveDate = value;
    }

    /**
     * Gets the value of the expireDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpireDate() {
        return expireDate;
    }

    /**
     * Sets the value of the expireDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpireDate(String value) {
        this.expireDate = value;
    }

    /**
     * Gets the value of the taxpayerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxpayerName() {
        return taxpayerName;
    }

    /**
     * Sets the value of the taxpayerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxpayerName(String value) {
        this.taxpayerName = value;
    }

    /**
     * Gets the value of the taxpayerAddressInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the taxpayerAddressInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTaxpayerAddressInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TaxpayerAddressInfo }
     * 
     * 
     */
    public List<TaxpayerAddressInfo> getTaxpayerAddressInfo() {
        if (taxpayerAddressInfo == null) {
            taxpayerAddressInfo = new ArrayList<TaxpayerAddressInfo>();
        }
        return this.taxpayerAddressInfo;
    }

}