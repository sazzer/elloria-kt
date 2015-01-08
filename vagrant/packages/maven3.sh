#!/bin/bash

apt-get remove maven2 maven
add-apt-repository "deb http://ppa.launchpad.net/natecarlson/maven3/ubuntu precise main"
apt-get -qq update
apt-get install maven3

update-alternatives --install /usr/bin/mvn Maven /usr/share/maven3/bin/mvn 10
update-alternatives --set Maven /usr/share/maven3/bin/mvn
