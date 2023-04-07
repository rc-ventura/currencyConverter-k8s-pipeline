@Library('my-shared-library')  // Importa uma biblioteca compartilhada

import java.time.ZoneId
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

pipeline {
    agent any
    
    triggers {
        githubPush()
    }
    
    environment {
        DOCKER_REGISTRY = "rcventura" // altere para o endereço do seu registro Docker
    }
    
    stages {
        stage('Build Docker Image') {
            steps {
                script {
                            def zone = ZoneId.of("America/Sao_Paulo")
                            def DATENOW = LocalDateTime.now(zone).format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm"))                   
                            def TAG = "currency-converter-backend:${DATENOW}"
                            dockerImage = docker.build("-f Dockerfile -t ${DOCKER_REGISTRY}/${TAG}")

                    
                }
            }
        }
        
        stage ('Push Docker Image'){
            steps {
                script {
                    docker.withRegistry("https://registry.hub.docker.com", "dockerhub-credentials") {
                        dockerImage.push("${DOCKER_REGISTRY}/${TAG}")
                        dockerImage.push("${DOCKER_REGISTRY}/currency-converter-backend:latest")
                    }
                }
            }
        }
    }
}
