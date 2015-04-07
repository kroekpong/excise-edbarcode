//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.05 at 02:28:23 PM ICT 
//


package th.go.excise.edbarcode.ws.oxm;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for entrepreneur complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="entrepreneur">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="licenseNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="licenseAllowedName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="factoryId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="factoryName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="licenseStartDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="licenseEndDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="taxNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="factoryAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="productList" type="{http://www.excise.go.th/xsd/barcode}product" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "entrepreneur", propOrder = {
    "licenseNo",
    "licenseAllowedName",
    "factoryId",
    "factoryName",
    "licenseStartDate",
    "licenseEndDate",
    "taxNo",
    "factoryAddress",
    "productList"
})
public class Entrepreneur {

    @XmlElement(required = true)
    protected String licenseNo;
    @XmlElement(required = true)
    protected String licenseAllowedName;
    @XmlElement(required = true)
    protected String factoryId;
    @XmlElement(required = true)
    protected String factoryName;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar licenseStartDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar licenseEndDate;
    @XmlElement(required = true)
    protected String taxNo;
    @XmlElement(required = true)
    protected String factoryAddress;
    protected List<Product> productList;

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
     * Gets the value of the licenseAllowedName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLicenseAllowedName() {
        return licenseAllowedName;
    }

    /**
     * Sets the value of the licenseAllowedName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLicenseAllowedName(String value) {
        this.licenseAllowedName = value;
    }

    /**
     * Gets the value of the factoryId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFactoryId() {
        return factoryId;
    }

    /**
     * Sets the value of the factoryId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFactoryId(String value) {
        this.factoryId = value;
    }

    /**
     * Gets the value of the factoryName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFactoryName() {
        return factoryName;
    }

    /**
     * Sets the value of the factoryName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFactoryName(String value) {
        this.factoryName = value;
    }

    /**
     * Gets the value of the licenseStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLicenseStartDate() {
        return licenseStartDate;
    }

    /**
     * Sets the value of the licenseStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLicenseStartDate(XMLGregorianCalendar value) {
        this.licenseStartDate = value;
    }

    /**
     * Gets the value of the licenseEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLicenseEndDate() {
        return licenseEndDate;
    }

    /**
     * Sets the value of the licenseEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLicenseEndDate(XMLGregorianCalendar value) {
        this.licenseEndDate = value;
    }

    /**
     * Gets the value of the taxNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxNo() {
        return taxNo;
    }

    /**
     * Sets the value of the taxNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxNo(String value) {
        this.taxNo = value;
    }

    /**
     * Gets the value of the factoryAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFactoryAddress() {
        return factoryAddress;
    }

    /**
     * Sets the value of the factoryAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFactoryAddress(String value) {
        this.factoryAddress = value;
    }

    /**
     * Gets the value of the productList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Product }
     * 
     * 
     */
    public List<Product> getProductList() {
        if (productList == null) {
            productList = new ArrayList<Product>();
        }
        return this.productList;
    }

}