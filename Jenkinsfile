pipeline {
    agent any 
    triggers {
        githubPush()
    }
    stages {
        stage('Build and Push Docker Image') {
            environment {
                registry = "rcventura"
                image = "currency-converter-backend"
                dockerfile = "Dockerfile-backend"
            }
            steps {
                script {
                    docker.withRegistry("https://registry.hub.docker.com", "dockerhub-credentials") {
                        def customImage = docker.build ("${registry}/${image}:${env.BUILD_ID}", "-f ${dockerfile},  .")
                        customImage.push()

                        customImage.push('latest')
                    }
                }
            }
        }
    }
}
