package wisdom.zero.baseproject.client.example;

import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class ExampleClientConfig implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {

        /**
         * Applied by default to all defined requests
         * */
        requestTemplate.header("DEFAULTHEADER1", "test");

    }

    /**
     * Multipart form encoder encoder.
     *
     * @return the encoder
     */
    @Bean
    public Encoder multipartFormEncoder() {

        return new SpringFormEncoder(
                new SpringEncoder(() -> new HttpMessageConverters(new RestTemplate().getMessageConverters()))
        );

    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
