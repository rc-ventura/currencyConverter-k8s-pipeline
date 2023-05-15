#!/bin/bash

## Kill nos processos APT
sudo lsof /var/lib/dpkg/lock-frontend | awk '{print $2}' | xargs sudo kill -9

# Instalação do OpenJDK 17
sudo apt-get update
sudo apt-get install -y openjdk-17-jdk

# Instalação do Jenkins
curl -fsSL https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key | sudo tee \
  /usr/share/keyrings/jenkins-keyring.asc > /dev/null
echo deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc] \
  https://pkg.jenkins.io/debian-stable binary/ | sudo tee \
  /etc/apt/sources.list.d/jenkins.list > /dev/null
sudo apt-get update
sudo apt-get install -y jenkins

# Instalação do Docker
curl -fsSL https://get.docker.com -o get-docker.sh
yes | sudo sh get-docker.sh 
# Adição do usuário jenkins ao grupo docker
sudo usermod -aG docker jenkins

# Instalação do kubectl
sudo apt-get update && sudo apt-get install -y apt-transport-https gnupg2
curl -s https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo apt-key add -
echo "deb https://apt.kubernetes.io/ kubernetes-xenial main" | sudo tee /etc/apt/sources.list.d/kubernetes.list
sudo apt-get update
sudo apt-get install -y kubectl


#ver os certificados 
#cat /var/lib/jenkins/secrets/initialAdminPassword

