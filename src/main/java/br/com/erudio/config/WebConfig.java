package br.com.erudio.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.erudio.serealizationConverter.YamlJackson2HttpMessageConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	public static final MediaType MEDIA_TYPE_APPLICATION_YAML = MediaType.valueOf("application/x-yaml"); 
	
	//@Override
	//Via QUERY param
	/*public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorParameter(true)//indica que aceita parâmetros
		.parameterName("mediaType")//será usado na URL para indicar o tipo do media (como o nome de uma variável)
		.ignoreAcceptHeader(true)//será ignorado os parâmetros no Header
		.useRegisteredExtensionsOnly(false)
		.defaultContentType(MediaType.APPLICATION_JSON) // tipo de conteúdo padrão
		.mediaType("json",MediaType.APPLICATION_JSON) // aceita json
		.mediaType("xml", MediaType.APPLICATION_XML);//aceita xml
	}*/

	
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// TODO Auto-generated method stub
		converters.add(new YamlJackson2HttpMessageConverter());
	}
	
	
	//Via HEADER param
		public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
			configurer.favorParameter(false)//indica que aceita parâmetros
			.ignoreAcceptHeader(false)
			.useRegisteredExtensionsOnly(false)
			.defaultContentType(MediaType.APPLICATION_JSON) // tipo de conteúdo padrão
				.mediaType("json",MediaType.APPLICATION_JSON) // aceita json
				.mediaType("xml", MediaType.APPLICATION_XML)//aceita xml
				.mediaType("x-yaml", MEDIA_TYPE_APPLICATION_YAML);//aceita yaml
				
		}


}
