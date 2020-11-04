package com.ooftf.engine.gsonlivedatadapter.sample

import androidx.lifecycle.MutableLiveData

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2020/11/4
 */
class TestBean {
    val stringLD = MutableLiveData<String>().apply { value = "123" }
    val intLD = MutableLiveData<Int>().apply { value = 123 }
    val objLD = MutableLiveData<InnerBean>().apply { value = InnerBean() }
}