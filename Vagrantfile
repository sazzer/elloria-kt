# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure(2) do |config|
  config.vm.box = "hashicorp/precise32"
  config.vm.network "forwarded_port", guest: 8080, host: 58080

  config.vm.provision :puppet do |puppet|
      puppet.manifests_path = 'vagrant/puppet/manifests'
      puppet.manifest_file = 'site.pp'
      puppet.module_path = 'vagrant/puppet/modules'
  end
end
