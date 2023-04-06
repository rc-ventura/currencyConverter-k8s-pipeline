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
                    docker.build("${DOCKER_REGISTRY}/${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}", ".")
                }
            }
        }
        stage ('Push Docker Image'){
            steps {
                script {
                    docker.withRegistry("${DOCKER_REGISTRY}", "dockerhub-credenciais") 
                     dockerImage.push("${env.BUILD_ID}")

                }
            }
        }
    }
     options {
        envFile '.env'
}
}