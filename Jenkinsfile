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
                tag = "1.0.1"
            }
            steps {
                script {
                    docker.withRegistry("https://registry.hub.docker.com", "dockerhub-credentials") {
                        def dockerImage = docker.build("${registry}/${image}:${tag}",  ".")
                        dockerImage.push()
                    }
                }
            }
        }
    }
}
