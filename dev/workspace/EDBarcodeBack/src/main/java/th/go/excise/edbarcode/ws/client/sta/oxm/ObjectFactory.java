//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.11 at 12:25:38 AM ICT 
//


package th.go.excise.edbarcode.ws.client.sta.oxm;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the th.go.excise.edbarcode.ws.client.sta.oxm package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: th.go.excise.edbarcode.ws.client.sta.oxm
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DataInformation }
     * 
     */
    public DataInformation createDataInformation() {
        return new DataInformation();
    }

    /**
     * Create an instance of {@link BinaryInformation }
     * 
     */
    public BinaryInformation createBinaryInformation() {
        return new BinaryInformation();
    }

    /**
     * Create an instance of {@link HeaderResponse }
     * 
     */
    public HeaderResponse createHeaderResponse() {
        return new HeaderResponse();
    }

    /**
     * Create an instance of {@link StaBacResponse }
     * 
     */
    public StaBacResponse createStaBacResponse() {
        return new StaBacResponse();
    }

    /**
     * Create an instance of {@link StaBacRequest }
     * 
     */
    public StaBacRequest createStaBacRequest() {
        return new StaBacRequest();
    }

    /**
     * Create an instance of {@link Error }
     * 
     */
    public Error createError() {
        return new Error();
    }

    /**
     * Create an instance of {@link Body }
     * 
     */
    public Body createBody() {
        return new Body();
    }

    /**
     * Create an instance of {@link HeaderRequest }
     * 
     */
    public HeaderRequest createHeaderRequest() {
        return new HeaderRequest();
    }

}
