//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.18 at 05:28:18 PM ICT 
//


package th.go.excise.edbarcode.ws.client.barcode.submitonline.oxm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SubmitOnlineHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubmitOnlineHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RegistratronId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CusId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CompanyId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CompanyUserId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CompanyUserPwd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TaxpayerId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ExciseOfficeId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="InternetUniqueId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IpAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SubmissionEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubmitOnlineHeader", propOrder = {
    "registratronId",
    "cusId",
    "companyId",
    "companyUserId",
    "companyUserPwd",
    "taxpayerId",
    "exciseOfficeId",
    "internetUniqueId",
    "ipAddress",
    "submissionEmail"
})
public class SubmitOnlineHeader {

    @XmlElement(name = "RegistratronId")
    protected String registratronId;
    @XmlElement(name = "CusId", required = true)
    protected String cusId;
    @XmlElement(name = "CompanyId", required = true)
    protected String companyId;
    @XmlElement(name = "CompanyUserId", required = true)
    protected String companyUserId;
    @XmlElement(name = "CompanyUserPwd", required = true)
    protected String companyUserPwd;
    @XmlElement(name = "TaxpayerId", required = true)
    protected String taxpayerId;
    @XmlElement(name = "ExciseOfficeId", required = true)
    protected String exciseOfficeId;
    @XmlElement(name = "InternetUniqueId", required = true)
    protected String internetUniqueId;
    @XmlElement(name = "IpAddress", required = true)
    protected String ipAddress;
    @XmlElement(name = "SubmissionEmail")
    protected String submissionEmail;

    /**
     * Gets the value of the registratronId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistratronId() {
        return registratronId;
    }

    /**
     * Sets the value of the registratronId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistratronId(String value) {
        this.registratronId = value;
    }

    /**
     * Gets the value of the cusId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCusId() {
        return cusId;
    }

    /**
     * Sets the value of the cusId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCusId(String value) {
        this.cusId = value;
    }

    /**
     * Gets the value of the companyId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * Sets the value of the companyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyId(String value) {
        this.companyId = value;
    }

    /**
     * Gets the value of the companyUserId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyUserId() {
        return companyUserId;
    }

    /**
     * Sets the value of the companyUserId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyUserId(String value) {
        this.companyUserId = value;
    }

    /**
     * Gets the value of the companyUserPwd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyUserPwd() {
        return companyUserPwd;
    }

    /**
     * Sets the value of the companyUserPwd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyUserPwd(String value) {
        this.companyUserPwd = value;
    }

    /**
     * Gets the value of the taxpayerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxpayerId() {
        return taxpayerId;
    }

    /**
     * Sets the value of the taxpayerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxpayerId(String value) {
        this.taxpayerId = value;
    }

    /**
     * Gets the value of the exciseOfficeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExciseOfficeId() {
        return exciseOfficeId;
    }

    /**
     * Sets the value of the exciseOfficeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExciseOfficeId(String value) {
        this.exciseOfficeId = value;
    }

    /**
     * Gets the value of the internetUniqueId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInternetUniqueId() {
        return internetUniqueId;
    }

    /**
     * Sets the value of the internetUniqueId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInternetUniqueId(String value) {
        this.internetUniqueId = value;
    }

    /**
     * Gets the value of the ipAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Sets the value of the ipAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpAddress(String value) {
        this.ipAddress = value;
    }

    /**
     * Gets the value of the submissionEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubmissionEmail() {
        return submissionEmail;
    }

    /**
     * Sets the value of the submissionEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubmissionEmail(String value) {
        this.submissionEmail = value;
    }

}
