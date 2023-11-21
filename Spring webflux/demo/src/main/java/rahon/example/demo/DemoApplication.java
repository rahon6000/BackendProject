package rahon.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.Option;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

@Configuration
class ReactiveConfig extends AbstractR2dbcConfiguration {

	@Override
	@Bean
	public ConnectionFactory connectionFactory(){
		ConnectionFactoryOptions options = ConnectionFactoryOptions.builder()
				.option(DRIVER, "mysql")
				.option(HOST, "127.0.0.1")
				.option(USER, "root")
				.option(PORT, 3306) // optional, default 3306
				.option(PASSWORD, "1234") // optional, default null, null means has no password
				.option(DATABASE, "sakila") // optional, default null, null means not specifying the database
				.option(Option.valueOf("connectionTimeZone"), "UTC")
				// .option(CONNECT_TIMEOUT, Duration.ofSeconds(3)) // optional, default null, null means no timeout
				// .option(Option.valueOf("socketTimeout"), Duration.ofSeconds(4)) // deprecated since 1.0.1, because it has no
				// 																// effect and serves no purpose.
				// .option(SSL, true) // optional, default sslMode is "preferred", it will be ignore if sslMode is set
				// .option(Option.valueOf("sslMode"), "verify_identity") // optional, default "preferred"
				// .option(Option.valueOf("sslCa"), "/path/to/mysql/ca.pem") // required when sslMode is verify_ca or
				// 															// verify_identity, default null, null means has
				// 															// no server CA cert
				// .option(Option.valueOf("sslCert"), "/path/to/mysql/client-cert.pem") // optional, default null, null means
				// 																		// has no client cert
				// .option(Option.valueOf("sslKey"), "/path/to/mysql/client-key.pem") // optional, default null, null means has
				// 																	// no client key
				// .option(Option.valueOf("sslKeyPassword"), "key-pem-password-in-here") // optional, default null, null means
				// 																		// has no password for client key
				// 																		// (i.e. "sslKey")
				// .option(Option.valueOf("tlsVersion"), "TLSv1.3,TLSv1.2,TLSv1.1") // optional, default is auto-selected by
				// 																	// the server
				// .option(Option.valueOf("sslHostnameVerifier"), "com.example.demo.MyVerifier") // optional, default is null,
				// 																				// null means use standard
				// 																				// verifier
				// .option(Option.valueOf("sslContextBuilderCustomizer"), "com.example.demo.MyCustomizer") // optional, default
				// 																						// is no-op
				// 																						// customizer
				// .option(Option.valueOf("zeroDate"), "use_null") // optional, default "use_null"
				// .option(Option.valueOf("useServerPrepareStatement"), true) // optional, default false
				// .option(Option.valueOf("tcpKeepAlive"), true) // optional, default false
				// .option(Option.valueOf("tcpNoDelay"), true) // optional, default false
				// .option(Option.valueOf("autodetectExtensions"), false) // optional, default false
				// .option(Option.valueOf("passwordPublisher"), Mono.just("password")) // optional, default null, null means
				// 																	// has no passwordPublisher (since 1.0.5
				// 																	// / 0.9.6)
				.build();
		return ConnectionFactories.get(options);
	
		// Creating a Mono using Project Reactor
		// Mono<Connection> connectionMono = Mono.from(connectionFactory.create());
	}
}