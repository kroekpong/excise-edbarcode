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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GoodsInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GoodsInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="categoryCode1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="categoryName1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="categoryCode2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="categoryName2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="categoryCode3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="categoryName3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="categoryCode4" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="categoryName4" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="categoryCode5" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="categoryName5" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="unitCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GoodsInformation", propOrder = {
    "productCode",
    "categoryCode1",
    "categoryName1",
    "categoryCode2",
    "categoryName2",
    "categoryCode3",
    "categoryName3",
    "categoryCode4",
    "categoryName4",
    "categoryCode5",
    "categoryName5",
    "unitCode"
})
@XmlSeeAlso({
    PSO112Goods.class
})
public class GoodsInformation {

    @XmlElement(required = true, nillable = true)
    protected String productCode;
    @XmlElement(required = true, nillable = true)
    protected String categoryCode1;
    @XmlElement(required = true, nillable = true)
    protected String categoryName1;
    @XmlElement(required = true, nillable = true)
    protected String categoryCode2;
    @XmlElement(required = true, nillable = true)
    protected String categoryName2;
    @XmlElement(required = true, nillable = true)
    protected String categoryCode3;
    @XmlElement(required = true, nillable = true)
    protected String categoryName3;
    @XmlElement(required = true, nillable = true)
    protected String categoryCode4;
    @XmlElement(required = true, nillable = true)
    protected String categoryName4;
    @XmlElement(required = true, nillable = true)
    protected String categoryCode5;
    @XmlElement(required = true, nillable = true)
    protected String categoryName5;
    @XmlElement(required = true, nillable = true)
    protected String unitCode;

    /**
     * Gets the value of the productCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * Sets the value of the productCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCode(String value) {
        this.productCode = value;
    }

    /**
     * Gets the value of the categoryCode1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryCode1() {
        return categoryCode1;
    }

    /**
     * Sets the value of the categoryCode1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryCode1(String value) {
        this.categoryCode1 = value;
    }

    /**
     * Gets the value of the categoryName1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryName1() {
        return categoryName1;
    }

    /**
     * Sets the value of the categoryName1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryName1(String value) {
        this.categoryName1 = value;
    }

    /**
     * Gets the value of the categoryCode2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryCode2() {
        return categoryCode2;
    }

    /**
     * Sets the value of the categoryCode2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryCode2(String value) {
        this.categoryCode2 = value;
    }

    /**
     * Gets the value of the categoryName2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryName2() {
        return categoryName2;
    }

    /**
     * Sets the value of the categoryName2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryName2(String value) {
        this.categoryName2 = value;
    }

    /**
     * Gets the value of the categoryCode3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryCode3() {
        return categoryCode3;
    }

    /**
     * Sets the value of the categoryCode3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryCode3(String value) {
        this.categoryCode3 = value;
    }

    /**
     * Gets the value of the categoryName3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryName3() {
        return categoryName3;
    }

    /**
     * Sets the value of the categoryName3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryName3(String value) {
        this.categoryName3 = value;
    }

    /**
     * Gets the value of the categoryCode4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryCode4() {
        return categoryCode4;
    }

    /**
     * Sets the value of the categoryCode4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryCode4(String value) {
        this.categoryCode4 = value;
    }

    /**
     * Gets the value of the categoryName4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryName4() {
        return categoryName4;
    }

    /**
     * Sets the value of the categoryName4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryName4(String value) {
        this.categoryName4 = value;
    }

    /**
     * Gets the value of the categoryCode5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryCode5() {
        return categoryCode5;
    }

    /**
     * Sets the value of the categoryCode5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryCode5(String value) {
        this.categoryCode5 = value;
    }

    /**
     * Gets the value of the categoryName5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryName5() {
        return categoryName5;
    }

    /**
     * Sets the value of the categoryName5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryName5(String value) {
        this.categoryName5 = value;
    }

    /**
     * Gets the value of the unitCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitCode() {
        return unitCode;
    }

    /**
     * Sets the value of the unitCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitCode(String value) {
        this.unitCode = value;
    }

}
