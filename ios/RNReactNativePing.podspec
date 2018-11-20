
Pod::Spec.new do |s|
  s.name         = "RNReactNativePing"
  s.version      = "1.0.0"
  s.summary      = "RNReactNativePing"
  s.description  = <<-DESC
                  RNReactNativePing
                   DESC
  s.homepage     = ""
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "author" => "396912848@qq.com" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/Mandmg/RNReactNativePing.git", :tag => "master" }
  s.source_files  = "RNReactNativePing/**/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  #s.dependency "others"

end

  