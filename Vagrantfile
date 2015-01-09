# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure(2) do |config|
  config.vm.box = "ubuntu/trusty64"
  config.vm.network "forwarded_port", guest: 8080, host: 58080

  # install librarian-puppet and run it to install puppet common modules.
  # This has to be done before puppet provisioning so that modules are available
  # when puppet tries to parse its manifests
  config.vm.provision :shell, :path => "vagrant/puppet-librarian.sh"

  config.vm.provision :puppet do |puppet|
      puppet.manifests_path = 'vagrant/puppet/manifests'
      puppet.manifest_file = 'site.pp'
      puppet.module_path = ['vagrant/puppet/modules', 'vagrant/puppet/modules-contrib']
  end
end
