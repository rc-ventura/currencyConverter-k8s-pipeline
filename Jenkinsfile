pipeline {
    agent any
    environment {
      TAG_NAME = "1.0.1"
    }
    triggers {
        githubPush()
    }
    stages {
        stage('Build Docker Image') {
            steps {
                script {
                        dockerImage = docker.build("-f Dockerfile -t ${DOCKER_REGISTRY}/currency-converter-backend:${TAG_NAME} .")
                }
            }
        }
        stage ('Push Docker Image'){
            steps {
                script {
                    docker.withRegistry("https://registry.hub.docker.com", "dockerhub-credentials") {
                        dockerImage.push('${DOCKER_REGISTRY}/currency-converter-backend$:{TAG_NAME}')
                        dockerImage.push('${DOCKER_REGISTRY}/currency-converter-backend:latest')                    }
                }
            }
        }
    }
}

