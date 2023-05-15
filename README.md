<div align="center">
  
![Converta-me_cover](https://user-images.githubusercontent.com/87483916/224345890-7077b778-878c-42cb-9292-2590de454519.png)



<img src= "./frontend/assets/currency_theme_white-trim.png" width="850" height="300" alt=" rosto de uma presidente americano em uma moeda">

</div>

## :8ball: Descrição

Conversor de pares de moeda que faz uma consulta em uma API LAYER retorna o câmbio em tempo real e faz a conversão dos respectivos pares.

<div align="center">

  <img  src=  "https://user-images.githubusercontent.com/87483916/232828544-c7af4a6d-8670-4616-a5bc-7ea078acdab2.png " width="1000" alt= "esquema do design da aplicação"/>
  
</div>
 
## :smile: Status do Projeto

![→_-em desenvolvimento-green](https://user-images.githubusercontent.com/87483916/224333868-804e4712-ecb7-492b-8c47-6c6bff8e3b7a.svg)


## 🚀 Começando

Essas instruções permitirão que você obtenha uma cópia do projeto em operação na sua máquina local para fins de desenvolvimento e teste.

Consulte **[Implantação](#-implanta%C3%A7%C3%A3o)** para saber como implantar o projeto.

### 📋 Pré-requisitos

 ##### <a href="https://www.oracle.com/br/java/technologies/downloads"> * Maven </a>
 ##### <a href="https://www.oracle.com/br/java/technologies/downloads"> * Eclipse ou outra IDE </a>
 ##### <a href="https://www.oracle.com/br/java/technologies/downloads"> * JDK 11 ou superior </a>
 ##### <a href="https://www.oracle.com/br/java/technologies/downloads"> * MySQL Workbench </a>


### 🔧 Instalação


#### :anger: Pelo Git Bash:
<hr>

1. Abra Git Bash. 
2. Altere o diretório de trabalho atual para o local em que deseja ter o diretório clonado. 
3. Digite git clone e cole a URL já copiada.

```
$ git clone https://github.com/rc-ventura/CurrencyConverter-App
```
4. Pressione **ENTER** para criar seu clone local.
```
$ git clone https://github.com/rc-ventura/CurrencyConverter-App
> Cloning into `Spoon-Knife`...
> remote: Counting objects: 10, done.
> remote: Compressing objects: 100% (8/8), done.
> remove: Total 10 (delta 1), reused 10 (delta 1)
> Unpacking objects: 100% (10/10), done.
```
5. Abra sua o Eclipse
6. Selecione -> File -> Open Projects From File System -> Selecione o caminho da pasta onde fez o download.
7. Pressione **RUN** no Eclipe para rodar a aplicação ou **Ctrl + F11**


#### :anger: Por Download do arquivo .zip:
<hr>

1. No GitHub.com, navegue até a página principal do repositório. 
2. Acima da lista de arquivos, clique em  **Código** ou **Code**.
3. Selecione a opção **Download .zip**
5. Salve em uma máquina local.
5. Abra sua o Eclipse
6. Selecione -> File -> Open Projects From File System -> Selecione o caminho da pasta onde fez o download.
7. Pressione **RUN** no Eclipe para rodar a aplicação ou **Ctrl + F11**


#### :anger: Executando com o Maven:
<hr>

1. Clona ou faça download da aplicação.
2. Abra o prompt de comando ou terminal.
3. Selecione o caminho da pasta onde fez download.
5. Execute o seguinte comando:
 ```
 mvn spring-boot:run
 ```


## :scroll: Funcionalidades

<Br>

* Conversor de câmbio entre um par de moedas.

https://user-images.githubusercontent.com/87483916/224521177-28dfcd7d-a7f8-4b07-8165-008f50befc2d.mp4

<Br>
<Br>

* Salvando transações de conversão no banco de dados.

https://user-images.githubusercontent.com/87483916/224570140-59ec3d15-2057-4b94-b85e-2c733daf2f98.mp4



## :airplane: Roadmap
 
 - [x] Main Feature: Método para conversão de pares de moeda. 
 - [x] Feature: Interface do usuário em Html/Css.
 - [x] Feature: Link entre backend e frontend com JQuery.
 - [x] Feature: Controlador de Exceptions
 - [x] Testes unitários com Junit e Mockito da Main Feature.
 - [x] Teste de integração da API com a biblioteca restAssured.
 - [x] Testes com cobertura de 80%.
 - [x] Ajustes na UI.
 - [x] Feature: Método para salvar toda transação de conversão no banco de ddaos.
 - [x] Teste para método salvar toda transação no banco de dados.
 - [x] Teste de integração de todas as features.
 - [ ] Teste de carga em transações no banco de dados. 
 - [ ] Teste de UI com Selenium.
 - [x] Documentação da API com Swagger/OpenApi.
 - [x] Documentação com Testes com Allure Framework.
 - [x] Deploy da aplicação em ambiente de teste com K3d da Ranch.
 - [x] Automatização com script de incialização com Terraform para o servidor remoto na Digital Ocean.
 - [x] Deploy da aplicação em ambiente de desenvolvimento em um servidor remoto na Digital Ocean com Kubernetes.
 - [x] Pipeline CI/CD com Jenkins em ambiente de desenvolvimento.
 - [ ] Pipeline CI/CD com Jenkins na Digital Ocean em produção.
 - [x] SRE: Observabilidade da aplicação: Métricas com Micrometer
 - [x] SRE: Observabilidade da aplicação: Monitoramento com o Prometheus
 - [x] SRE: Observabilidade da aplicação: Dashboards com o Grafana.


 


## :book: Documentação
  
##### <a href="https://swagger.io/specification/)" target="_blank"> Swagger/OpenAPI </a>
  
<img src="https://user-images.githubusercontent.com/87483916/230924283-b1cf026a-ca3d-4ad0-a721-d762791bc960.png" alt="documentacão Api swagger"/>


<div align="center">

https://user-images.githubusercontent.com/87483916/230924780-c11f1a06-e2d6-4cf1-a6d8-3e75b8c24f01.mp4

  </div>
  
## ⚙️ Executando os testes
<Br>
  
* Overview dos testes feitos em Junit, Mockito, Rest Assured
<Br>
   
 <div  style="display: inline-block" >

<img src="https://user-images.githubusercontent.com/87483916/230927490-7ff73c1c-cc32-4e4f-bf0c-e899d38d96d9.png" alt="Imagem modificada" width="800"/>

<img src="https://user-images.githubusercontent.com/87483916/230928492-249e0adf-1f74-48d4-9b40-b95a67b7846b.png" alt="Imagem modificada" width="800"/>
<Br>
<Br>
  
  ### :fast_forward: Testes de Integração
  <Hr>
  
   #### :o: shouldConvertCurrencyAndSaveTransaction
   * Simula uma consulta na API externa. 
   * Retorna com a taxa de conversão.
   * Processa a conversão.
   * Salva no banco de dados a transação. 
  <Br>
 <img src= "https://user-images.githubusercontent.com/87483916/230958891-d9ff3b4b-1f7f-43ef-96a4-fb72c2a47303.png" alt= "Imagem modificada" width="800"/>

    
  #### :o: Transaction Controller Feature
  * Simula todos os endpoints da aplicação ao manipular uma transação
  * Controller->Service->Repository
  * GET, UPDATE, GET/ID, DELETE/ID, SAVE
  <Br>
  

<img src="https://user-images.githubusercontent.com/87483916/230958940-8f6d3e33-36f8-4244-a334-59429143a0a1.png" alt=" Imagem modificada" width="800"/>

<Br>

 ### :fast_forward: Testes Unitários
<Hr>
 
 #### :o: CurrencyExceptionHandler
 * Simula o controle de exceções
  <Br>
    
  ![Sem título - Brave 10_04_2023 11_56_51](https://user-images.githubusercontent.com/87483916/230982651-7dbe2ae6-b3f4-4808-8c83-38cb4f8733d3.png)

     
 #### :o: Currency Conversion
 * Simula uma conversão.
 * Chama a API Externa
 * Faz uma conversão do par USD para BRL.
  <Br>
    
 ![Sem título - Brave 10_04_2023 11_54_53](https://user-images.githubusercontent.com/87483916/230982897-a2460374-df92-43b4-8a68-ae14140fe758.png)

    
<Br>
    
 #### :o: Transaction Service
* Simula todas as manipulações de transações na camada de Serviços
* Service->Repository
    
<Br>
 
![Sem título - Brave 10_04_2023 11_55_35](https://user-images.githubusercontent.com/87483916/230983762-5092a75c-d24e-4d28-8d41-6b3ee1b71449.png)

  
</div>
  
  
<!-- Explicar como executar os testes automatizados para este sistema.

### 🔩 Analise os testes de ponta a ponta

Explique que eles verificam esses testes e porquê.

```
Dar exemplos
```

### ⌨️ E testes de estilo de codificação

Explique que eles verificam esses testes e porquê.

```
Dar exemplos
```
-->
  
## 📦 Implantação
  
  <Br>
  
<div align="center">
 
  ![Deploy-Currency-Converter drawio](https://github.com/rc-ventura/CurrencyConverter_CI-CD-K3D/assets/87483916/8ce8a791-f8fc-4e4d-8d05-8cc90a121330)

  
</div> 
  
  <Br>
  
  #### :crystal_ball: DEVOPS
    
  * Utilizando o Terraform provisiona uma infra-estrutura básica na  Digital Ocean.
  * Criação de um cluster kubernetes com três serviços: backend, frontend e bancode dados
  * Criação de 3 réplicas do backend e do frontend para escalonamento
  * Criação de um load balancer aberto para internet que balanceia a carga para o frontend na porta 80 (http)
  * Provisionamento de um máquina virtual (Droplet) para configurar a orquestração com o Jenkins.
  * Criação do pipeline com o Jenkins CI utilizando o Docker e o DockerHub.
  * Configuração d eum webhook no github para automatização do início do pipeline CI após um gitpush no repositório.
    
  #### :mag: SRE 
  
 * Criação de observabilidade utlizando o Helm para configurar o cluster Kubernetes.
 * Criação um load balancer aberto para requisições http na porta 80 e redirecionamento para a porta 9090 (prometheus) 
 * Criação um load balancer aberto para requisições http na porta 80 e redirecionamento para 3000 (grafana).
 * Criação dos dashboards das métricas funcionais e não funcionais da aplicação.
  
  #### :computer: DEV
  
 * Promove alterações no código e ativa o trigger do pipeline.
 * Manutenção e implementação dos testes unitários 
 * Promove a cobertura do código nos testes.
  
<Br>



## 🛠️ Tecnologias

 <div  style="display: inline-block" >

 <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="80"  /> 
 <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" width="80"  />          
 <img src= "https://user-images.githubusercontent.com/87483916/224313415-17511f48-87e4-4d9d-98ed-01daba701270.png" width="80"/>  
 <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/javascript/javascript-original.svg"width="80" />
 <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/jquery/jquery-plain-wordmark.svg"width="80" />
 <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/html5/html5-original-wordmark.svg"width="80" />
 <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/css3/css3-original.svg"width="80" />
 <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mysql/mysql-original-wordmark.svg"width="80"  />
 <img src= "https://user-images.githubusercontent.com/87483916/224327989-cbe927a8-c35c-48e1-99e2-6baadec00d9b.svg" width="80">
 <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/docker/docker-original.svg" width="80">
 <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/jenkins/jenkins-original.svg"  width="80" />
 <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/kubernetes/kubernetes-plain.svg" width="80" />
 <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/terraform/terraform-original.svg" width="80" />
 <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/digitalocean/digitalocean-original-wordmark.svg" width="80" />
 <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/prometheus/prometheus-original.svg" width="80" />
 <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/grafana/grafana-original.svg" width="80"/>
 <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/ansible/ansible-original.svg" width="80"/>
 <img src="https://user-images.githubusercontent.com/87483916/235651549-6462c52d-2e71-4afc-9baa-e8bb05b1ddfb.png" width="80"/>
 <img src="https://user-images.githubusercontent.com/87483916/235651471-f5d27c13-0881-4cde-8001-29ad237636fc.svg" width="80"/>
 <img src="https://user-images.githubusercontent.com/87483916/235651505-baebd218-b8eb-4452-89a4-1263ac864abe.svg" width="80"/>

</div>




## 🖇️ Colaborando

Por favor, leia o [COLABORACAO.md](https://gist.github.com/usuario/linkParaInfoSobreContribuicoes) para obter detalhes sobre o nosso código de conduta e o processo para nos enviar pedidos de solicitação.

## 📌 Versão

 Para as versões disponíveis, observe as [tags neste repositório](https://github.com/suas/tags/do/projeto). 

## ✒️ Autores

Mencione todos aqueles que ajudaram a levantar o projeto desde o seu início

* **Rafael Ventura** - *Idealizador do projeto* - [Dev Ventura](https://github.com/rc-ventura)

Você também pode ver a lista de todos os [colaboradores](https://github.com/usuario/projeto/colaboradores) que participaram deste projeto.

## 📄 Licença

Este projeto está sob a licença (sua licença) - veja o arquivo [LICENSE.md](https://github.com/usuario/projeto/licenca) para detalhes.

## 🎁 Expressões de gratidão


---
⌨️ com ❤️ por [Rafael Ventura](https://gist.github.com/rc-ventura) 😊
