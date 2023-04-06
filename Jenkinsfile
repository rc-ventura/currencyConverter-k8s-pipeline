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
                    dockerImage = docker.build("-f Dockerfile -t ${DOCKER_REGISTRY}/currency-converter-backend:1.0.1 .")
                }
            }
        }
        
        stage ('Push Docker Image'){
            steps {
                script {
                    docker.withRegistry("https://${DOCKER_REGISTRY}", "dockerhub-credentials") {
                        dockerImage.push("${DOCKER_REGISTRY}/currency-converter-backend:1.0.1")
                        dockerImage.push("${DOCKER_REGISTRY}/currency-converter-backend:latest")
                    }
                }
            }
        }
    }
}
