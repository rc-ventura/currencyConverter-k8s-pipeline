terraform {
  required_providers {
    digitalocean = {
      source  = "digitalocean/digitalocean"
      version = "2.27.1"
    }
  }
}

provider "digitalocean" {
  token = var.personal_token
}

# Create a new Web Droplet in the nyc2 region
resource "digitalocean_droplet" "jenkins" {
  image    = "ubuntu-18-04-x64"
  name     = "jenkins"
  region   = var.region
  size     = "s-2vcpu-2gb"
  ssh_keys = [data.digitalocean_ssh_key.ssh_key_name.id]

  connection {
    type        = "ssh"
    user        = "root"
    private_key = file("~/.ssh/terraform_digitalOcean")
    host        = digitalocean_droplet.jenkins.ipv4_address

  }

  

  provisioner "remote-exec" {
    inline = [ 
      "mkdir -p /scripts"
     ]
  }

  provisioner "file" {
    source      = "../scripts/pipeline-config.sh"
    destination = "/scripts/pipeline-config.sh"

  }


  provisioner "remote-exec" {
    inline = [
      "sudo chmod +x /scripts/pipeline-config.sh",
      "/scripts/pipeline-config.sh"
    ]
  }


}

data "digitalocean_ssh_key" "ssh_key_name" {
  name = var.ssh_key_name
}

# resource "digitalocean_kubernetes_cluster" "k8s" {
#   name    = "k8s"
#   region  = var.region
#   version = "1.26.3-do.0"

#   node_pool {
#     name       = "default"
#     size       = "s-2vcpu-2gb"
#     node_count = 2
#   }
# }

variable "ssh_key_name" {
  default = ""
}

variable "personal_token" {
  default = ""
}

variable "region" {
  default = ""

}



output "jenkins_ip" {
  value = digitalocean_droplet.jenkins.ipv4_address

}

# resource  "local_file" "kube_config" {
#     content = digitalocean_kubernetes_cluster.k8s.kube_config.0.raw_config
#     filename = "kube_config.yaml" 

# }