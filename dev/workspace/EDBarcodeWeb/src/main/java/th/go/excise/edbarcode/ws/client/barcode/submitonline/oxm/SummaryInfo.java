//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.10 at 02:08:11 PM ICT 
//


package th.go.excise.edbarcode.ws.client.barcode.submitonline.oxm;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SummaryInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SummaryInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SumAllTaxByValue" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="SumAllTaxByQuantity" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="SumAllTax" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="TaxLessFrom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TaxLessAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="TaxDeductionOnBookNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TaxDeductionOnBookAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="PaymentExciseAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="PaymentMunicipalAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="PaymentFundHealthAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="PaymentFundTVAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="PaymentFundSportAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="MoiRate" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="MoiTax" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="SumCreditMoiTax" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="PrintType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PaymentExciseAndMunicipalTaxAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="PaymentOtherAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="PaymentNetTaxAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="ExciseAmountSubtractTaxLessAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SummaryInfo", propOrder = {
    "sumAllTaxByValue",
    "sumAllTaxByQuantity",
    "sumAllTax",
    "taxLessFrom",
    "taxLessAmount",
    "taxDeductionOnBookNo",
    "taxDeductionOnBookAmount",
    "paymentExciseAmount",
    "paymentMunicipalAmount",
    "paymentFundHealthAmount",
    "paymentFundTVAmount",
    "paymentFundSportAmount",
    "moiRate",
    "moiTax",
    "sumCreditMoiTax",
    "printType",
    "paymentExciseAndMunicipalTaxAmount",
    "paymentOtherAmount",
    "paymentNetTaxAmount",
    "exciseAmountSubtractTaxLessAmount"
})
public class SummaryInfo {

    @XmlElement(name = "SumAllTaxByValue", required = true)
    protected BigDecimal sumAllTaxByValue;
    @XmlElement(name = "SumAllTaxByQuantity", required = true)
    protected BigDecimal sumAllTaxByQuantity;
    @XmlElement(name = "SumAllTax", required = true)
    protected BigDecimal sumAllTax;
    @XmlElement(name = "TaxLessFrom")
    protected String taxLessFrom;
    @XmlElement(name = "TaxLessAmount")
    protected BigDecimal taxLessAmount;
    @XmlElement(name = "TaxDeductionOnBookNo")
    protected String taxDeductionOnBookNo;
    @XmlElement(name = "TaxDeductionOnBookAmount")
    protected BigDecimal taxDeductionOnBookAmount;
    @XmlElement(name = "PaymentExciseAmount", required = true)
    protected BigDecimal paymentExciseAmount;
    @XmlElement(name = "PaymentMunicipalAmount", required = true)
    protected BigDecimal paymentMunicipalAmount;
    @XmlElement(name = "PaymentFundHealthAmount", required = true)
    protected BigDecimal paymentFundHealthAmount;
    @XmlElement(name = "PaymentFundTVAmount", required = true)
    protected BigDecimal paymentFundTVAmount;
    @XmlElement(name = "PaymentFundSportAmount", required = true)
    protected BigDecimal paymentFundSportAmount;
    @XmlElement(name = "MoiRate", required = true)
    protected BigDecimal moiRate;
    @XmlElement(name = "MoiTax", required = true)
    protected BigDecimal moiTax;
    @XmlElement(name = "SumCreditMoiTax", required = true)
    protected BigDecimal sumCreditMoiTax;
    @XmlElement(name = "PrintType", required = true)
    protected String printType;
    @XmlElement(name = "PaymentExciseAndMunicipalTaxAmount", required = true)
    protected BigDecimal paymentExciseAndMunicipalTaxAmount;
    @XmlElement(name = "PaymentOtherAmount")
    protected BigDecimal paymentOtherAmount;
    @XmlElement(name = "PaymentNetTaxAmount", required = true)
    protected BigDecimal paymentNetTaxAmount;
    @XmlElement(name = "ExciseAmountSubtractTaxLessAmount", required = true)
    protected BigDecimal exciseAmountSubtractTaxLessAmount;

    /**
     * Gets the value of the sumAllTaxByValue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSumAllTaxByValue() {
        return sumAllTaxByValue;
    }

    /**
     * Sets the value of the sumAllTaxByValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSumAllTaxByValue(BigDecimal value) {
        this.sumAllTaxByValue = value;
    }

    /**
     * Gets the value of the sumAllTaxByQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSumAllTaxByQuantity() {
        return sumAllTaxByQuantity;
    }

    /**
     * Sets the value of the sumAllTaxByQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSumAllTaxByQuantity(BigDecimal value) {
        this.sumAllTaxByQuantity = value;
    }

    /**
     * Gets the value of the sumAllTax property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSumAllTax() {
        return sumAllTax;
    }

    /**
     * Sets the value of the sumAllTax property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSumAllTax(BigDecimal value) {
        this.sumAllTax = value;
    }

    /**
     * Gets the value of the taxLessFrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxLessFrom() {
        return taxLessFrom;
    }

    /**
     * Sets the value of the taxLessFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxLessFrom(String value) {
        this.taxLessFrom = value;
    }

    /**
     * Gets the value of the taxLessAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTaxLessAmount() {
        return taxLessAmount;
    }

    /**
     * Sets the value of the taxLessAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTaxLessAmount(BigDecimal value) {
        this.taxLessAmount = value;
    }

    /**
     * Gets the value of the taxDeductionOnBookNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxDeductionOnBookNo() {
        return taxDeductionOnBookNo;
    }

    /**
     * Sets the value of the taxDeductionOnBookNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxDeductionOnBookNo(String value) {
        this.taxDeductionOnBookNo = value;
    }

    /**
     * Gets the value of the taxDeductionOnBookAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTaxDeductionOnBookAmount() {
        return taxDeductionOnBookAmount;
    }

    /**
     * Sets the value of the taxDeductionOnBookAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTaxDeductionOnBookAmount(BigDecimal value) {
        this.taxDeductionOnBookAmount = value;
    }

    /**
     * Gets the value of the paymentExciseAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPaymentExciseAmount() {
        return paymentExciseAmount;
    }

    /**
     * Sets the value of the paymentExciseAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPaymentExciseAmount(BigDecimal value) {
        this.paymentExciseAmount = value;
    }

    /**
     * Gets the value of the paymentMunicipalAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPaymentMunicipalAmount() {
        return paymentMunicipalAmount;
    }

    /**
     * Sets the value of the paymentMunicipalAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPaymentMunicipalAmount(BigDecimal value) {
        this.paymentMunicipalAmount = value;
    }

    /**
     * Gets the value of the paymentFundHealthAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPaymentFundHealthAmount() {
        return paymentFundHealthAmount;
    }

    /**
     * Sets the value of the paymentFundHealthAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPaymentFundHealthAmount(BigDecimal value) {
        this.paymentFundHealthAmount = value;
    }

    /**
     * Gets the value of the paymentFundTVAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPaymentFundTVAmount() {
        return paymentFundTVAmount;
    }

    /**
     * Sets the value of the paymentFundTVAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPaymentFundTVAmount(BigDecimal value) {
        this.paymentFundTVAmount = value;
    }

    /**
     * Gets the value of the paymentFundSportAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPaymentFundSportAmount() {
        return paymentFundSportAmount;
    }

    /**
     * Sets the value of the paymentFundSportAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPaymentFundSportAmount(BigDecimal value) {
        this.paymentFundSportAmount = value;
    }

    /**
     * Gets the value of the moiRate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMoiRate() {
        return moiRate;
    }

    /**
     * Sets the value of the moiRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMoiRate(BigDecimal value) {
        this.moiRate = value;
    }

    /**
     * Gets the value of the moiTax property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMoiTax() {
        return moiTax;
    }

    /**
     * Sets the value of the moiTax property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMoiTax(BigDecimal value) {
        this.moiTax = value;
    }

    /**
     * Gets the value of the sumCreditMoiTax property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSumCreditMoiTax() {
        return sumCreditMoiTax;
    }

    /**
     * Sets the value of the sumCreditMoiTax property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSumCreditMoiTax(BigDecimal value) {
        this.sumCreditMoiTax = value;
    }

    /**
     * Gets the value of the printType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrintType() {
        return printType;
    }

    /**
     * Sets the value of the printType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrintType(String value) {
        this.printType = value;
    }

    /**
     * Gets the value of the paymentExciseAndMunicipalTaxAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPaymentExciseAndMunicipalTaxAmount() {
        return paymentExciseAndMunicipalTaxAmount;
    }

    /**
     * Sets the value of the paymentExciseAndMunicipalTaxAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPaymentExciseAndMunicipalTaxAmount(BigDecimal value) {
        this.paymentExciseAndMunicipalTaxAmount = value;
    }

    /**
     * Gets the value of the paymentOtherAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPaymentOtherAmount() {
        return paymentOtherAmount;
    }

    /**
     * Sets the value of the paymentOtherAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPaymentOtherAmount(BigDecimal value) {
        this.paymentOtherAmount = value;
    }

    /**
     * Gets the value of the paymentNetTaxAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPaymentNetTaxAmount() {
        return paymentNetTaxAmount;
    }

    /**
     * Sets the value of the paymentNetTaxAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPaymentNetTaxAmount(BigDecimal value) {
        this.paymentNetTaxAmount = value;
    }

    /**
     * Gets the value of the exciseAmountSubtractTaxLessAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getExciseAmountSubtractTaxLessAmount() {
        return exciseAmountSubtractTaxLessAmount;
    }

    /**
     * Sets the value of the exciseAmountSubtractTaxLessAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setExciseAmountSubtractTaxLessAmount(BigDecimal value) {
        this.exciseAmountSubtractTaxLessAmount = value;
    }

}
