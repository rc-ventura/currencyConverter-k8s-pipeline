pipeline {
    agent any
    environment {
        DOCKER_IMAGE_TAG  = "${env.BUILD_ID}"
        DOCKER_REGISTRY = readFile('.env').trim()
        DOCKER_IMAGE_NAME = readFile('.env').trim()
    }
    triggers {
        githubPush()
    }
    stages {
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKER_REGISTRY}/${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}", ".")
                }
            }
        }
        stage ('Push Docker Image'){
            steps {
                script {
                    docker.withRegistry("${DOCKER_REGISTRY}", "dockerhub-credentials") 
                     dockerImage.push("${env.BUILD_ID}")

                }
            }
        }
    }
     
}