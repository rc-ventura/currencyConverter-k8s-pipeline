pipeline {
    agent any
    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
    }
    environment {
        registry = credentials('dockerhub-credentials') 
        image = "currency-converter-backend"
        dockerfile_backend = "Dockerfile-backend"
        user_docker = "rcventura"
    }
    triggers {
        githubPush()
    }
    stages {
        stage('Build e Push Docker Image') {
            steps {
                script {
                    def imageName = "${user_docker}/${image}:${env.BUILD_ID}"
                    def customImage = docker.build(imageName,"-f ${dockerfile_backend} .")
                    customImage.push()
                    customImage.push('latest')
                }
            }
        }
        stage("Run Tests"){
            steps {
                bat 'mvn test -Dspring.profiles.active=dev' 
            }
        }
    }
}
