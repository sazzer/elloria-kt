class maven
(   
  # Define  maven base diectory path here.
  $base_dir   = hiera('maven::base_dir',  '/usr/local'),
  # Define  maven version.
  $maven_version  = hiera('maven::maven_version',  'apache-maven-3.2.5'),
  # Define  maven download url.
  $download_url   = hiera('maven::download_url',  'http://apache.mirror.anlx.net/maven/maven-3/3.2.5/binaries/apache-maven-3.2.5-bin.tar.gz'),
  # Define  maven download dir.
  $download_dir   = hiera('maven::download_dir',  '/opt'),
  $maven_file  = hiera('maven::maven_file',    'apache-maven-3.2.5-bin.tar.gz'),
){
  # Setting default exec path for this module
  Exec { path    => ['/usr/bin', '/usr/sbin', '/bin', '/sbin',] }

  exec { "download_maven":
    cwd   => "$download_dir",
    command => "wget '${download_url}'",
    unless  => "test -e ${maven_file}",
  }
      
  exec { "extract_maven":
    cwd   => "$download_dir",
    command => "tar xzf '${maven_file}' -C ${base_dir}",
    unless  => "test -e ${base_dir}/${maven_version}",
    require => Exec["download_maven"],
  }  
      
  exec { "activate_maven":
    cwd   => "$download_dir",
    command => "update-alternatives --install /usr/local/bin/mvn Maven ${base_dir}/${maven_version}/bin/mvn 10",
    require => Exec["extract_maven"],
  }  
}
