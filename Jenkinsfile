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
                tag = "latest"
            }
            steps {
                script {
                    docker.withRegistry("https://registry.hub.docker.com", "dockerhub-credentials") {
                        def dockerImage = docker.build("${registry}/${image}:${tag}", ".")
                        dockerImage.push()
                    }
                }
            }
        }
    }
}
