package com.baiwa.framework.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.WebServiceMessageFactory;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.server.endpoint.adapter.method.MarshallingPayloadMethodProcessor;
import org.springframework.ws.server.endpoint.interceptor.PayloadLoggingInterceptor;
import org.springframework.ws.server.endpoint.mapping.PayloadRootAnnotationMethodEndpointMapping;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import th.go.excise.edbarcode.common.constant.WebServiceConstant;

@Configuration
@EnableWs
@ComponentScan(
	basePackages = {
		"th.go.excise.edbarcode.ws"
	},
	includeFilters = {
		@ComponentScan.Filter(
			type = FilterType.REGEX,
			pattern = ".*.endpoint"
		)
	}
)
public class WebServiceConfig extends WsConfigurerAdapter {
	
	@Bean
	public WebServiceMessageFactory messageFactory() {
		return new SaajSoapMessageFactory();
	}
	
	@Bean(name = "EDBarcodeService")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema barcodeSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("EDBarcodeService");
		wsdl11Definition.setLocationUri("http://localhost:8080/EDBarcodeWeb/ws/EDBarcodeService/");
		wsdl11Definition.setTargetNamespace(WebServiceConstant.NAMESPACE_URI);
		wsdl11Definition.setSchema(barcodeSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema barcodeSchema() {
		return new SimpleXsdSchema(new ClassPathResource("th/go/excise/edbarcode/ws/provider/xsd/edbarcode.xsd"));
	}
	
	@Bean
	public MarshallingPayloadMethodProcessor methodProcessor() {
		return new MarshallingPayloadMethodProcessor(marshaller());
	}

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan(new String[] {
			"th.go.excise.edbarcode.ws.oxm"
		});
		return marshaller;
	}
	
	@Bean
	public PayloadRootAnnotationMethodEndpointMapping endpointMapping() {
		PayloadRootAnnotationMethodEndpointMapping mapping = new PayloadRootAnnotationMethodEndpointMapping();
		mapping.setInterceptors(new EndpointInterceptor[] {
			new PayloadLoggingInterceptor()
		});
		return mapping;
	}

}
