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
 * <p>Java class for PSO112Goods complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PSO112Goods">
 *   &lt;complexContent>
 *     &lt;extension base="{http://bcsservice.pccth.com/}GoodsInformation">
 *       &lt;sequence>
 *         &lt;element name="rateFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="taxQuantity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="taxQuantityNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="taxQuantityPerUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="taxValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="priceFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="informPrice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="declarePrice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="unitPirce" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="goodsNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="goodsValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="taxvalUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="taxqtyUnit1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="taxqtyUnit2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="taxvalAmt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="taxqtyAmt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PSO112Goods", propOrder = {
    "rateFlag",
    "taxQuantity",
    "taxQuantityNumber",
    "taxQuantityPerUnit",
    "taxValue",
    "priceFlag",
    "informPrice",
    "declarePrice",
    "unitPirce",
    "goodsNum",
    "goodsValue",
    "taxvalUnit",
    "taxqtyUnit1",
    "taxqtyUnit2",
    "taxvalAmt",
    "taxqtyAmt"
})
public class PSO112Goods
    extends GoodsInformation
{

    @XmlElement(required = true, nillable = true)
    protected String rateFlag;
    @XmlElement(required = true, nillable = true)
    protected String taxQuantity;
    @XmlElement(required = true, nillable = true)
    protected String taxQuantityNumber;
    @XmlElement(required = true, nillable = true)
    protected String taxQuantityPerUnit;
    @XmlElement(required = true, nillable = true)
    protected String taxValue;
    @XmlElement(required = true, nillable = true)
    protected String priceFlag;
    @XmlElement(required = true, nillable = true)
    protected String informPrice;
    @XmlElement(required = true, nillable = true)
    protected String declarePrice;
    @XmlElement(required = true, nillable = true)
    protected String unitPirce;
    @XmlElement(required = true, nillable = true)
    protected String goodsNum;
    @XmlElement(required = true, nillable = true)
    protected String goodsValue;
    @XmlElement(required = true, nillable = true)
    protected String taxvalUnit;
    @XmlElement(required = true, nillable = true)
    protected String taxqtyUnit1;
    @XmlElement(required = true, nillable = true)
    protected String taxqtyUnit2;
    @XmlElement(required = true, nillable = true)
    protected String taxvalAmt;
    @XmlElement(required = true, nillable = true)
    protected String taxqtyAmt;

    /**
     * Gets the value of the rateFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateFlag() {
        return rateFlag;
    }

    /**
     * Sets the value of the rateFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateFlag(String value) {
        this.rateFlag = value;
    }

    /**
     * Gets the value of the taxQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxQuantity() {
        return taxQuantity;
    }

    /**
     * Sets the value of the taxQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxQuantity(String value) {
        this.taxQuantity = value;
    }

    /**
     * Gets the value of the taxQuantityNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxQuantityNumber() {
        return taxQuantityNumber;
    }

    /**
     * Sets the value of the taxQuantityNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxQuantityNumber(String value) {
        this.taxQuantityNumber = value;
    }

    /**
     * Gets the value of the taxQuantityPerUnit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxQuantityPerUnit() {
        return taxQuantityPerUnit;
    }

    /**
     * Sets the value of the taxQuantityPerUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxQuantityPerUnit(String value) {
        this.taxQuantityPerUnit = value;
    }

    /**
     * Gets the value of the taxValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxValue() {
        return taxValue;
    }

    /**
     * Sets the value of the taxValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxValue(String value) {
        this.taxValue = value;
    }

    /**
     * Gets the value of the priceFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriceFlag() {
        return priceFlag;
    }

    /**
     * Sets the value of the priceFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriceFlag(String value) {
        this.priceFlag = value;
    }

    /**
     * Gets the value of the informPrice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInformPrice() {
        return informPrice;
    }

    /**
     * Sets the value of the informPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInformPrice(String value) {
        this.informPrice = value;
    }

    /**
     * Gets the value of the declarePrice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeclarePrice() {
        return declarePrice;
    }

    /**
     * Sets the value of the declarePrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeclarePrice(String value) {
        this.declarePrice = value;
    }

    /**
     * Gets the value of the unitPirce property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitPirce() {
        return unitPirce;
    }

    /**
     * Sets the value of the unitPirce property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitPirce(String value) {
        this.unitPirce = value;
    }

    /**
     * Gets the value of the goodsNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoodsNum() {
        return goodsNum;
    }

    /**
     * Sets the value of the goodsNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoodsNum(String value) {
        this.goodsNum = value;
    }

    /**
     * Gets the value of the goodsValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoodsValue() {
        return goodsValue;
    }

    /**
     * Sets the value of the goodsValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoodsValue(String value) {
        this.goodsValue = value;
    }

    /**
     * Gets the value of the taxvalUnit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxvalUnit() {
        return taxvalUnit;
    }

    /**
     * Sets the value of the taxvalUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxvalUnit(String value) {
        this.taxvalUnit = value;
    }

    /**
     * Gets the value of the taxqtyUnit1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxqtyUnit1() {
        return taxqtyUnit1;
    }

    /**
     * Sets the value of the taxqtyUnit1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxqtyUnit1(String value) {
        this.taxqtyUnit1 = value;
    }

    /**
     * Gets the value of the taxqtyUnit2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxqtyUnit2() {
        return taxqtyUnit2;
    }

    /**
     * Sets the value of the taxqtyUnit2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxqtyUnit2(String value) {
        this.taxqtyUnit2 = value;
    }

    /**
     * Gets the value of the taxvalAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxvalAmt() {
        return taxvalAmt;
    }

    /**
     * Sets the value of the taxvalAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxvalAmt(String value) {
        this.taxvalAmt = value;
    }

    /**
     * Gets the value of the taxqtyAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxqtyAmt() {
        return taxqtyAmt;
    }

    /**
     * Sets the value of the taxqtyAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxqtyAmt(String value) {
        this.taxqtyAmt = value;
    }

}
