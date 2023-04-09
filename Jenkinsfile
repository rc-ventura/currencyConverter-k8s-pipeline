pipeline {
    agent any
        
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
    }
    environment {
        registry = credentials('dockerhub-credentials') 
        image = "currency-converter-backend"
        dockerfile_backend = "Dockerfile-backend"
    }
    triggers {
        githubPush()
    }
    stages {
        stage('Build Docker Image') {
            steps {
                script {
                    def imageName = "${registry}/${image}:${env.BUILD_ID}"
                    def customImage = docker.build(imageName,"-f ${dockerfile_backend}.")
                    customImage.push()
                    customImage.push('latest')
                }
            }
        }
        stage("Run Tests"){
            steps {
                sh 'mvn test -Dspring.profiles.active=dev' 
            }
        }
    }

