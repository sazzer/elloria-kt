# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure(2) do |config|
  config.vm.box = "hashicorp/precise32"
  config.vm.network "forwarded_port", guest: 8080, host: 58080
  config.vm.provision :shell, path: "vagrant/bootstrap.sh"
end
