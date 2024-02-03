package com.intercorp.ms1.MS01.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.intercorp.ms1.MS01.model.Root2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableKafka
public class KafkaConsumerConfiguration {

	//@Value("${custom.kafka.bootstrap-servers}")
	private String bootstrapServers;

	///@Value("${custom.kafka.group-id}")
	private String groupId;

    
    @Bean
    public ConsumerFactory<String, Root2> ordenConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "myGroup1");

        // Configurar el deserializador para manejar un solo objeto String
        //JsonDeserializer<String> deserializer = new JsonDeserializer<>(String.class);

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(Root2.class));
    }
    
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Root2> ordenKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Root2> factory = new ConcurrentKafkaListenerContainerFactory<>();
        
        factory.setConsumerFactory(ordenConsumerFactory());
        
        return factory;
    }
	
}
