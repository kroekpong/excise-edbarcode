//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2558.08.21 at 01:35:58 ��ѧ���§ ICT 
//


package th.go.excise.edbarcode.ws.provider.oxm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetSR12011ReportStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GetSR12011ReportDesc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PDFDocument" type="{http://www.excise.go.th/xsd/barcode}PDFDocument"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getSR12011ReportStatus",
    "getSR12011ReportDesc",
    "pdfDocument"
})
@XmlRootElement(name = "EbarcodeGetSR12011ReportResponse")
public class EbarcodeGetSR12011ReportResponse {

    @XmlElement(name = "GetSR12011ReportStatus", required = true)
    protected String getSR12011ReportStatus;
    @XmlElement(name = "GetSR12011ReportDesc", required = true)
    protected String getSR12011ReportDesc;
    @XmlElement(name = "PDFDocument", required = true)
    protected PDFDocument pdfDocument;

    /**
     * Gets the value of the getSR12011ReportStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetSR12011ReportStatus() {
        return getSR12011ReportStatus;
    }

    /**
     * Sets the value of the getSR12011ReportStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetSR12011ReportStatus(String value) {
        this.getSR12011ReportStatus = value;
    }

    /**
     * Gets the value of the getSR12011ReportDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetSR12011ReportDesc() {
        return getSR12011ReportDesc;
    }

    /**
     * Sets the value of the getSR12011ReportDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetSR12011ReportDesc(String value) {
        this.getSR12011ReportDesc = value;
    }

    /**
     * Gets the value of the pdfDocument property.
     * 
     * @return
     *     possible object is
     *     {@link PDFDocument }
     *     
     */
    public PDFDocument getPDFDocument() {
        return pdfDocument;
    }

    /**
     * Sets the value of the pdfDocument property.
     * 
     * @param value
     *     allowed object is
     *     {@link PDFDocument }
     *     
     */
    public void setPDFDocument(PDFDocument value) {
        this.pdfDocument = value;
    }

}
