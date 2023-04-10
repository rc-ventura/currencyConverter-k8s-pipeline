<div align="center">
  
![Converta-me_cover](https://user-images.githubusercontent.com/87483916/224345890-7077b778-878c-42cb-9292-2590de454519.png)



<img src= "./frontend/assets/currency_theme_white-trim.png" width="850" height="300" alt=" rosto de uma presidente americano em uma moeda">

</div>

## :8ball: Descrição

Conversor de pares de moeda que faz uma consulta em uma API LAYER retorna o câmbio em tempo real e faz a conversão dos respectivos pares.


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
 - [ ] Teste de UI.
 - [x] Documentação da API com Swagger/OpenApi.
 - [x] Documentação com Testes com Allure Framework.
 - [ ] Deploy da aplicação em ambiente de teste com Docker play.
 - [ ] Deploy da aplicação em ambiente de desenvolvimento em um servidor remoto no Fly.io ou Aws.
 - [ ] Pipeline CI/CD com Jenkins
 - [ ] SRE: Observabilidade da aplicação: Métricas e Monitoramento.


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
    
  #### :o: Transaction Controller Feature
  * Simula todos os endpoints da aplicação ao manipular uma transação
  * Controller->Service->Repository
  * GET, UPDATE, GET/ID, DELETE/ID, SAVE
  <Br>
  
<img src= "https://user-images.githubusercontent.com/87483916/230958891-d9ff3b4b-1f7f-43ef-96a4-fb72c2a47303.png" alt= "Imagem modificada" width="800"/>

<img src="https://user-images.githubusercontent.com/87483916/230958940-8f6d3e33-36f8-4244-a334-59429143a0a1.png" alt=" Imagem modificada" width="800"/>

 ### :fast_forward: Testes Unitários

    k

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
  
![----_-Em Construção  ](https://user-images.githubusercontent.com/87483916/224585085-61045e0a-cbe7-48cb-b29a-e6a89c4cf4e7.svg)


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
