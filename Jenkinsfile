pipeline {
    agent any
      TAG_NAME = "${env.BRANCH_NAME}-${env.BUILD_NUMBER}"

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
                        dockerImage.push('${TAG_NAME}')
                        dockerImage.push('latest')                    }
                }
            }
        }
    }
}

