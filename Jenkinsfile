pipeline {
    agent any
    options {
        buildDiscarder(logRotator(numToKeepStr: '3'))
    }
    environment {
        registry = credentials('dockerhub-credentials') 
        image_backend = "currency-converter-backend"
        image_frontend = "currency-converter-frontend"
        dockerfile_backend = "Dockerfile-backend"
        dockerfile_frontend = "Dockerfile-frontend"
        user_docker = "rcventura"
        MYSQL_ROOT_PASSWORD="Rafa81041683!"
        MYSQL_ROOT_HOST="%"
        MYSQL_DATABASE="currency-converterdb"
        MYSQL_USER="admindb"
        MYSQL_PASSWORD="Rafa81041683!!"
        MYSQL_HOST="localhost"
        MYSQL_PORT="3306"
    }
    triggers {
        githubPush()
    }
    stages {

        stage('Build e Push Docker Image Backend') {
            steps {
                script {
                    def imageBackend = "${user_docker}/${image_backend}:${env.BUILD_ID}"
                    def backendCustom_image = docker.build(imageBackend,"-f ${dockerfile_backend} .")
                    
                    backendCustom_image.push()
                    backendCustom_image.push('latest')
                }
            }
        }
         stage('Build e Push Docker Image Frontend') {
            steps {
                script {
                    def imageFrontend = "${user_docker}/${image_frontend}:${env.BUILD_ID}"
                    def frontendCustom_image = docker.build(imageFrontend,"-f ${dockerfile_frontend} .")
                    
                    frontendCustom_image.push()
                    frontendCustom_image.push('latest')
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
    

