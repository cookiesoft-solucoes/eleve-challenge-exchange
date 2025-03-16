
# EleveChallengeExchange API

## 🚀 Sobre o Projeto
A **EleveChallengeExchange API** é uma aplicação desenvolvida em **Java 21 com Spring Boot**, estruturada com o padrão **Clean Architecture**. A aplicação oferece uma API REST que:

- Recebe um par de moedas (exemplo: "USD" para "BRL").
- Utiliza a **ExchangeRate-API** para obter a taxa de conversão.
- Armazena a data, hora e taxa de conversão em um banco de dados **H2 embedded**.
- Utiliza **Testcontainers** para garantir testes de integração isolados.
- Está pronta para ser disponibilizada em um ambiente **Kubernetes** com CI/CD via **GitHub Actions** ou **Jenkins**.

---

## 🏗️ Arquitetura

- **`application`** → Contém os casos de uso, serviços e DTOs.
- **`domain`** → Contém as entidades e repositórios.
- **`infrastructure`** → Contém a configuração de API externa, persistência e configurações.
- **`interfaces`** → Contém os controllers e mapeadores.


---

## 🧪 Testes
- **Testes Unitários**: Cobertura total nas camadas de domínio e aplicação.
- **Testes de Integração**: Utilização de **Testcontainers** para garantir o isolamento dos testes.

Execute os testes com:
```bash
./gradlew clean test
```

---

## ⚙️ Como Subir a Aplicação

### 1. Docker Build
```bash
docker build -t alysonrodrigo/elevechallengeexchange:latest .
```

### 2. Executar a Imagem Localmente
```bash
docker run -p 8080:8080 alysonrodrigo/elevechallengeexchange:latest
```

---

## 🚀 Deploy com GitHub Actions

### Pré-requisitos:
- Adicionar Secrets no GitHub:
    - `DOCKER_USERNAME` → Seu usuário do Docker Hub.
    - `DOCKER_PASSWORD` → Sua senha do Docker Hub.

### Fluxo de CI/CD
1. Ao realizar um `git push` para o branch `main`, o GitHub Actions irá automaticamente:
    - Construir a aplicação com Gradle.
    - Criar e publicar a imagem Docker no Docker Hub.
    - Aplicar os manifests Kubernetes para deploy no cluster.

**Executar o pipeline:**
```bash
git add .
git commit -m "Deploy automático via GitHub Actions"
git push origin main
```

Verifique a execução em **Actions** no GitHub.

---

## 📖 Swagger API Documentation (Springdoc)

- A documentação interativa da API foi configurada com **Springdoc OpenAPI**.
- Acesse a documentação via Swagger UI em:

```
http://localhost:8080/swagger-ui/index.html
```

### Dependências no `build.gradle`
```groovy
dependencies {
    implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0'
}
```

### Configuração de Segurança para Swagger no `SecurityConfig`
```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests()
        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
        .anyRequest().authenticated()
        .and().httpBasic();
    return http.build();
}
```

- **OpenAPI Endpoint:**
```
http://localhost:8080/v3/api-docs
```

---

## ⚙️ Deploy com Jenkins

- Certifique-se de que o Jenkins possui as permissões necessárias para acessar o Docker e Kubernetes.
- Pipeline configurado no `Jenkinsfile`.

**Etapas do Jenkins:**
1. Build com Gradle.
2. Build e Push da Imagem Docker.
3. Deploy no Kubernetes usando `kubectl`.

Execute o pipeline via interface Jenkins ou manualmente com:
```bash
jenkins build <pipeline-name>
```

---

## ☸️ Kubernetes Deployment Manual
1. Configure o contexto do seu cluster Kubernetes com o `kubectl`.
2. Execute os manifests:

```bash
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml
```

Para verificar se o deploy foi bem-sucedido:
```bash
kubectl get pods
kubectl get services
```

---

## ✅ Como Testar a API
- Verifique a URL gerada pelo LoadBalancer no Kubernetes ou use `localhost:8080` localmente.

**Exemplo de Requisição:**
```bash
curl -X GET "http://localhost:8080/api/conversion?from=USD&to=BRL"
```

---

## 🔒 Segurança
- Configurado com **Spring Security** para autenticação via **HTTP Basic**.


---

## 📄 Licença
Este projeto está sob a licença MIT.

---

## 💡 Contribuições
Contribuições são bem-vindas! Crie uma issue ou pull request para sugerir melhorias.


---

Desenvolvido por [Alyson Rodrigo](https://github.com/alysonrodrigo) 🚀
