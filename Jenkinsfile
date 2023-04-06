pipeline {
    agent any
        environment {
        DOCKER_IMAGE_TAG  = "${env.BUILD_ID}"
    }
    triggers {
        githubPush()
    }
    stages {
        stage('Build Docker Image') {
            steps {
                script {
                        dockerImage = docker.build("-f Dockerfile -t ${DOCKER_REGISTRY}/currency-converter-backend:${DOCKER_IMAGE_TAG} .")
                }
            }
        }
        stage ('Push Docker Image'){
            steps {
                script {
                    docker.withRegistry("https://registry.hub.docker.com", "dockerhub-credentials") {
                        dockerImage.push("${DOCKER_IMAGE_TAG}")
                    }
                }
            }
        }
    }
}

