pipeline {
    agent any
    options {
        buildDiscarder(logRotator(numToKeepStr: '3'))
    }
    environment {
        registry = credentials('dockerhub-credentials') 
        image = "currency-converter-backend"
        dockerfile_backend = "Dockerfile-backend"
        dockerfile_frontend = "Dockerfile-frontend"
        user_docker = "rcventura"
    }
    triggers {
        githubPush()
    }
    stages {

        stage('Build e Push Docker Image Backend') {
            steps {
                script {
                    def imageName = "${user_docker}/${image}:${env.BUILD_ID}"
                    def customImage = docker.build(imageName,"-f ${dockerfile_backend} .")
                    
                    customImage.push()
                    customImage.push('latest')
                }
            }
        }
         stage('Build e Push Docker Image Frontend') {
            steps {
                script {
                    def imageName = "${user_docker}/${image}:${env.BUILD_ID}"
                    def customImage = docker.build(imageName,"-f ${dockerfile_frontend} .")
                    
                    customImage.push()
                    customImage.push('latest')
                }
            }
        }
        stage("Run Tests Backend"){
            steps {
                retry(3){
                bat 'mvn clean test -Dspring.profiles.active=dev' 
            }
         }
     }
         //stage('Code Integrity') {
           // steps {
             //   withSonarQubeEnv('SonarQube') {
               //     sh 'mvn sonar:sonar'
                //}
            //}
         //}
    }
 }
    

