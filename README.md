# GsonLiveDataAdapter  [ ![Download](https://api.bintray.com/packages/ooftf/maven/gson-adapter-extensions/images/download.svg) ](https://bintray.com/ooftf/maven/gson-adapter-extensions/_latestVersion)
    添加了LiveData,Observable类型的解析
    添加String,Int,Long,Double,Long,Boolean 带有默认值的适配器
# 使用方式
```
    implementation 'com.ooftf:gson-adapter-extensions:0.0.1'
 
    GsonBuilder().deployLiveData().deployDefaultValue().create()
```
