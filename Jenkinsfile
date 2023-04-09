pipeline {
    agent {
        docker {
            image 'ubuntu'
        } 
        
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
    }
    environment {
        registry = credentials('dockerhub-registry-url') // Use a Jenkins credential to store the Docker registry URL
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
                sh 'mvn test -Dspring.profiles.active=dev' // Use the sh command instead of bat for cross-platform compatibility
            }
        }
    }
}
