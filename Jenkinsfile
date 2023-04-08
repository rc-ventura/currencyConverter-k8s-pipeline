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
            }
            steps {
                script {
                    docker.withRegistry("https://hub.docker.com/", "dockerhub-credentials") {
                        def dockerfile = "Dockerfile-backend"
                        def imageName = "${registry}/${image}:${env.BUILD_ID}"
                        def customImage = docker.build(imageName,"-f ${dockerfile} .")
                        customImage.push()
                        customImage.push('latest')
                    }
                }
            }
        }
    }
}
