# GsonLiveDataAdapter
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.ooftf/gson-adapter-extensions/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.ooftf/gson-adapter-extensions)
添加了LiveData,Observable类型的解析

添加String,Int,Long,Double,Long,Boolean 带有默认值的适配器
# 使用方式
```
    implementation 'com.github.ooftf:gson-adapter-extensions:0.0.4'
 
    GsonBuilder().deployLiveData().deployDefaultValue().create()
```
