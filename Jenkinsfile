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
                    docker.build("${DOCKER_REGISTRY}/currency-converter-backend:${DOCKER_IMAGE_TAG}", ".")
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