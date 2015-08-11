//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.11 at 01:43:02 PM ICT 
//


package th.go.excise.edbarcode.ws.client.sta.oxm;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Goods complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Goods">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="GoodsCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GoodsDescriptionText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ProductTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ProductTypeDescriptionText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GoodsSize" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GoodsSizeUnitDescriptionText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GoodsPrice" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="TaxRateByPriceAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="TaxRateByQuantityAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="FundSSSRateAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="FundSSTRateAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="Degree" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PriceFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DeclarePrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="UnitCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GoodsUnitsDescriptionText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IncomeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SeqNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BrandName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SubbrandName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ModelName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InformDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProductNameEng" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RatePerLitre" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="DegreeMin" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="RateDegreeOver" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="WholesaleMin" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="RateWholesaleOver" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="RatePerLitreMax" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Goods", propOrder = {

})
public class Goods {

    @XmlElement(name = "GoodsCode", required = true)
    protected String goodsCode;
    @XmlElement(name = "GoodsDescriptionText", required = true)
    protected String goodsDescriptionText;
    @XmlElement(name = "ProductTypeCode", required = true)
    protected String productTypeCode;
    @XmlElement(name = "ProductTypeDescriptionText", required = true)
    protected String productTypeDescriptionText;
    @XmlElement(name = "GoodsSize")
    protected String goodsSize;
    @XmlElement(name = "GoodsSizeUnitDescriptionText")
    protected String goodsSizeUnitDescriptionText;
    @XmlElement(name = "GoodsPrice", required = true)
    protected BigDecimal goodsPrice;
    @XmlElement(name = "TaxRateByPriceAmount", required = true)
    protected BigDecimal taxRateByPriceAmount;
    @XmlElement(name = "TaxRateByQuantityAmount", required = true)
    protected BigDecimal taxRateByQuantityAmount;
    @XmlElement(name = "FundSSSRateAmount", required = true)
    protected BigDecimal fundSSSRateAmount;
    @XmlElement(name = "FundSSTRateAmount", required = true)
    protected BigDecimal fundSSTRateAmount;
    @XmlElement(name = "Degree")
    protected String degree;
    @XmlElement(name = "PriceFlag", required = true)
    protected String priceFlag;
    @XmlElement(name = "DeclarePrice")
    protected BigDecimal declarePrice;
    @XmlElement(name = "UnitCode", required = true)
    protected String unitCode;
    @XmlElement(name = "GoodsUnitsDescriptionText", required = true)
    protected String goodsUnitsDescriptionText;
    @XmlElement(name = "IncomeCode", required = true)
    protected String incomeCode;
    @XmlElement(name = "SeqNo", required = true)
    protected String seqNo;
    @XmlElement(name = "BrandName")
    protected String brandName;
    @XmlElement(name = "SubbrandName")
    protected String subbrandName;
    @XmlElement(name = "ModelName")
    protected String modelName;
    @XmlElement(name = "InformDate")
    protected String informDate;
    @XmlElement(name = "ProductNameEng")
    protected String productNameEng;
    @XmlElement(name = "RatePerLitre", required = true)
    protected BigDecimal ratePerLitre;
    @XmlElement(name = "DegreeMin", required = true)
    protected BigDecimal degreeMin;
    @XmlElement(name = "RateDegreeOver", required = true)
    protected BigDecimal rateDegreeOver;
    @XmlElement(name = "WholesaleMin", required = true)
    protected BigDecimal wholesaleMin;
    @XmlElement(name = "RateWholesaleOver", required = true)
    protected BigDecimal rateWholesaleOver;
    @XmlElement(name = "RatePerLitreMax", required = true)
    protected BigDecimal ratePerLitreMax;

    /**
     * Gets the value of the goodsCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoodsCode() {
        return goodsCode;
    }

    /**
     * Sets the value of the goodsCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoodsCode(String value) {
        this.goodsCode = value;
    }

    /**
     * Gets the value of the goodsDescriptionText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoodsDescriptionText() {
        return goodsDescriptionText;
    }

    /**
     * Sets the value of the goodsDescriptionText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoodsDescriptionText(String value) {
        this.goodsDescriptionText = value;
    }

    /**
     * Gets the value of the productTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductTypeCode() {
        return productTypeCode;
    }

    /**
     * Sets the value of the productTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductTypeCode(String value) {
        this.productTypeCode = value;
    }

    /**
     * Gets the value of the productTypeDescriptionText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductTypeDescriptionText() {
        return productTypeDescriptionText;
    }

    /**
     * Sets the value of the productTypeDescriptionText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductTypeDescriptionText(String value) {
        this.productTypeDescriptionText = value;
    }

    /**
     * Gets the value of the goodsSize property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoodsSize() {
        return goodsSize;
    }

    /**
     * Sets the value of the goodsSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoodsSize(String value) {
        this.goodsSize = value;
    }

    /**
     * Gets the value of the goodsSizeUnitDescriptionText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoodsSizeUnitDescriptionText() {
        return goodsSizeUnitDescriptionText;
    }

    /**
     * Sets the value of the goodsSizeUnitDescriptionText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoodsSizeUnitDescriptionText(String value) {
        this.goodsSizeUnitDescriptionText = value;
    }

    /**
     * Gets the value of the goodsPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    /**
     * Sets the value of the goodsPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setGoodsPrice(BigDecimal value) {
        this.goodsPrice = value;
    }

    /**
     * Gets the value of the taxRateByPriceAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTaxRateByPriceAmount() {
        return taxRateByPriceAmount;
    }

    /**
     * Sets the value of the taxRateByPriceAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTaxRateByPriceAmount(BigDecimal value) {
        this.taxRateByPriceAmount = value;
    }

    /**
     * Gets the value of the taxRateByQuantityAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTaxRateByQuantityAmount() {
        return taxRateByQuantityAmount;
    }

    /**
     * Sets the value of the taxRateByQuantityAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTaxRateByQuantityAmount(BigDecimal value) {
        this.taxRateByQuantityAmount = value;
    }

    /**
     * Gets the value of the fundSSSRateAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFundSSSRateAmount() {
        return fundSSSRateAmount;
    }

    /**
     * Sets the value of the fundSSSRateAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFundSSSRateAmount(BigDecimal value) {
        this.fundSSSRateAmount = value;
    }

    /**
     * Gets the value of the fundSSTRateAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFundSSTRateAmount() {
        return fundSSTRateAmount;
    }

    /**
     * Sets the value of the fundSSTRateAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFundSSTRateAmount(BigDecimal value) {
        this.fundSSTRateAmount = value;
    }

    /**
     * Gets the value of the degree property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDegree() {
        return degree;
    }

    /**
     * Sets the value of the degree property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDegree(String value) {
        this.degree = value;
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
     * Gets the value of the declarePrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDeclarePrice() {
        return declarePrice;
    }

    /**
     * Sets the value of the declarePrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDeclarePrice(BigDecimal value) {
        this.declarePrice = value;
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

    /**
     * Gets the value of the goodsUnitsDescriptionText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoodsUnitsDescriptionText() {
        return goodsUnitsDescriptionText;
    }

    /**
     * Sets the value of the goodsUnitsDescriptionText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoodsUnitsDescriptionText(String value) {
        this.goodsUnitsDescriptionText = value;
    }

    /**
     * Gets the value of the incomeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncomeCode() {
        return incomeCode;
    }

    /**
     * Sets the value of the incomeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncomeCode(String value) {
        this.incomeCode = value;
    }

    /**
     * Gets the value of the seqNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * Sets the value of the seqNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeqNo(String value) {
        this.seqNo = value;
    }

    /**
     * Gets the value of the brandName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * Sets the value of the brandName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrandName(String value) {
        this.brandName = value;
    }

    /**
     * Gets the value of the subbrandName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubbrandName() {
        return subbrandName;
    }

    /**
     * Sets the value of the subbrandName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubbrandName(String value) {
        this.subbrandName = value;
    }

    /**
     * Gets the value of the modelName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * Sets the value of the modelName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModelName(String value) {
        this.modelName = value;
    }

    /**
     * Gets the value of the informDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInformDate() {
        return informDate;
    }

    /**
     * Sets the value of the informDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInformDate(String value) {
        this.informDate = value;
    }

    /**
     * Gets the value of the productNameEng property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductNameEng() {
        return productNameEng;
    }

    /**
     * Sets the value of the productNameEng property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductNameEng(String value) {
        this.productNameEng = value;
    }

    /**
     * Gets the value of the ratePerLitre property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRatePerLitre() {
        return ratePerLitre;
    }

    /**
     * Sets the value of the ratePerLitre property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRatePerLitre(BigDecimal value) {
        this.ratePerLitre = value;
    }

    /**
     * Gets the value of the degreeMin property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDegreeMin() {
        return degreeMin;
    }

    /**
     * Sets the value of the degreeMin property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDegreeMin(BigDecimal value) {
        this.degreeMin = value;
    }

    /**
     * Gets the value of the rateDegreeOver property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRateDegreeOver() {
        return rateDegreeOver;
    }

    /**
     * Sets the value of the rateDegreeOver property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRateDegreeOver(BigDecimal value) {
        this.rateDegreeOver = value;
    }

    /**
     * Gets the value of the wholesaleMin property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getWholesaleMin() {
        return wholesaleMin;
    }

    /**
     * Sets the value of the wholesaleMin property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setWholesaleMin(BigDecimal value) {
        this.wholesaleMin = value;
    }

    /**
     * Gets the value of the rateWholesaleOver property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRateWholesaleOver() {
        return rateWholesaleOver;
    }

    /**
     * Sets the value of the rateWholesaleOver property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRateWholesaleOver(BigDecimal value) {
        this.rateWholesaleOver = value;
    }

    /**
     * Gets the value of the ratePerLitreMax property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRatePerLitreMax() {
        return ratePerLitreMax;
    }

    /**
     * Sets the value of the ratePerLitreMax property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRatePerLitreMax(BigDecimal value) {
        this.ratePerLitreMax = value;
    }

}
