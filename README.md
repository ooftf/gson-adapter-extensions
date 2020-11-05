# GsonLiveDataAdapter  [ ![Download](https://api.bintray.com/packages/ooftf/maven/gson-livedata-adapter/images/download.svg) ](https://bintray.com/ooftf/maven/gson-livedata-adapter/_latestVersion)
添加了LiveData类型的序列化和反序列化
添加String,Int,Long,Double,Long,Boolean 带有默认值的适配器
# 使用方式
```
 implementation 'com.ooftf:gson-livedata-adapter:0.0.2'
 
    GsonBuilder().deployLiveData().deployDefaultValue().create()
```