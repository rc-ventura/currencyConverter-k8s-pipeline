package com.oracle.next.one.config;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.oracle.next.one.Services.TransactionService;

import io.github.mweirauch.micrometer.jvm.extras.ProcessMemoryMetrics;
import io.github.mweirauch.micrometer.jvm.extras.ProcessThreadMetrics;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.binder.MeterBinder;
import io.micrometer.core.instrument.binder.jvm.ClassLoaderMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics;
import io.micrometer.core.instrument.binder.system.ProcessorMetrics;
import io.micrometer.core.instrument.binder.tomcat.TomcatMetrics;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.prometheus.client.exporter.HTTPServer;


/**
 * Classe de configuração do Micrometer para exportação de métricas para o Prometheus.
 */
@Configuration
public class MetricsConfig {

	    /**
	     * Configura um Prometheus Meter Registry e um servidor HTTP para expor métricas.
	     *
	     * @return o Prometheus Meter Registry configurado
	     * @throws IOException se houver um erro ao iniciar o servidor HTTP
	     */
	
	
	    @Bean
	    public PrometheusMeterRegistry prometheusRegistry() throws IOException {
	    	PrometheusMeterRegistry prometheusRegistry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);

	    	  // Registra as métricas de sistema
	         classLoaderMetrics().bindTo(prometheusRegistry);
	         jvmMemoryMetrics().bindTo(prometheusRegistry);
	         jvmThreadMetrics().bindTo(prometheusRegistry);
	         processorMetrics().bindTo(prometheusRegistry);
	         processMemoryMetrics().bindTo(prometheusRegistry);
	         processThreadMetrics().bindTo(prometheusRegistry);

	         
	         
	        // Cria um servidor HTTP e registra o Prometheus Meter Registry
	         new HTTPServer(new InetSocketAddress(9090), prometheusRegistry.getPrometheusRegistry(), true);

	       
	        return prometheusRegistry;
	    }
	    
	       
	    
	    @Bean
		public Gauge myGaugeTransaction(PrometheusMeterRegistry prometheusRegistry, TransactionService transactionService) {
			return Gauge.builder("currency.converter.transactions", transactionService::countTransactions)
					.description("Número de transação de conversão de moeda no banco de dados")
					.register(prometheusRegistry);
		}

	    
	    @Bean
	    public Counter myCounterSuccessCalls(PrometheusMeterRegistry prometheusRegistry) {
	    	
	    	return  Counter.builder("http.requests.success.total")
	    	        .description("Total number of successful HTTP requests")
	    	        .tags("status", "success")
	    	        .register(prometheusRegistry);
	    }
	    

	    @Bean
	    public Counter myCounterErrorCalls(PrometheusMeterRegistry prometheusRegistry) {
	    	
	    	return Counter.builder("http.requests.error.total")
    	        .description("Total number of HTTP requests that resulted in an error")
    	        .tags("status", "error")
    	        .register(prometheusRegistry);

	    }

	    @Bean
	    public Timer currencyConverterTimer(PrometheusMeterRegistry prometheusRegistry) {
	        return Timer.builder("currency_converter.response.time")
	                .description("Response time for currency endpoint")
	                .register(prometheusRegistry);
	    }
	    
	    @Bean 
	    public Timer myTimerLatencyCalls (PrometheusMeterRegistry prometheusRegistry) {
	    	
	    	return Timer.builder("latency.timer")
	    		  .description("Total time of HTTP request")
	    		  .register(prometheusRegistry);
	    }
	    
	    @Bean
	    public MeterBinder classLoaderMetrics() {
	        return new ClassLoaderMetrics();
	    
	}

	    
	    @Bean
	    public MeterBinder jvmMemoryMetrics() {
	        return new JvmMemoryMetrics();
	    
	}
	    
	    
	    @Bean
	    public MeterBinder jvmThreadMetrics() {
	        return new JvmThreadMetrics();
	    
	}
	 
	    
	    @Bean
	    public MeterBinder processorMetrics() {
	        return new ProcessorMetrics();
	    
	} 
	    
	    @Bean
	    public MeterBinder processMemoryMetrics() {
	        return new ProcessMemoryMetrics();
	    }

	    
	    @Bean
	    public MeterBinder processThreadMetrics() {
	        return new ProcessThreadMetrics();
	    }
	    
	   
}
	


