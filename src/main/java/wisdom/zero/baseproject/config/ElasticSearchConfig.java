package wisdom.zero.baseproject.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.io.*;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;

@Slf4j
@Configuration
public class ElasticSearchConfig {

    /**
     * secure connection
     */
    @Value("${elasticsearch.use-ssl:false}")
    public Boolean ssl;

    /**
     * elastic secure config
     */
    @Value("${elasticsearch.url:}")
    public String url;

    @Value("${elasticsearch.username:}")
    public String user;

    @Value("${elasticsearch.password:}")
    public String password;

    @Value("${elasticsearch.ca-cert:}")
    private String certPath;

    @Bean(destroyMethod = "close")
    public RestHighLevelClient client() {

        ClientConfiguration clientConfiguration = null;

        if (!ssl) {
            clientConfiguration = ClientConfiguration.builder().connectedTo(url).withConnectTimeout(10000).withSocketTimeout(10000).build();
        } else {

            SSLContext sslContext = null;

            try {
                sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, getKeyManagers(), null);

                clientConfiguration = ClientConfiguration.builder().connectedTo(url)
                        .usingSsl(sslContext, NoopHostnameVerifier.INSTANCE)
                        .withBasicAuth(user, password).build();

            } catch (KeyManagementException | CertificateException | KeyStoreException | NoSuchAlgorithmException |
                     IOException e) {
                log.error(Arrays.toString(e.getStackTrace()));
            }

        }

        return RestClients.create(clientConfiguration).rest(); // NOSONAR java:S2095 closed by spring @Bean(destroyMethod = "close")

    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(client());
    }

    private TrustManager[] getKeyManagers() throws CertificateException, KeyStoreException,
            NoSuchAlgorithmException, IOException, KeyManagementException {

        final File initialFile = new File(certPath);
        try (final InputStream inputStream = new DataInputStream(new FileInputStream(initialFile))) {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate caCert = (X509Certificate) cf.generateCertificate(inputStream);

            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(null); // You don't need the KeyStore instance to come from a file.
            ks.setCertificateEntry("caCert", caCert);

            tmf.init(ks);

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);

            return tmf.getTrustManagers();
        }
    }


}
