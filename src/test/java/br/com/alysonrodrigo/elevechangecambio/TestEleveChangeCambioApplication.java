package br.com.alysonrodrigo.elevechangecambio;

import org.springframework.boot.SpringApplication;

public class TestEleveChangeCambioApplication {

    public static void main(String[] args) {
        SpringApplication.from(EleveChangeCambioApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
