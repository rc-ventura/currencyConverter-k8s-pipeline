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
                        def DATENOW = sh(script: 'TZ=America/New_York date +"%Y-%m-%d_%H-%M"', returnStdout: true).trim()
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
