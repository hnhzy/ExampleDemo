//
// Created by Administrator on 2019/5/27.
//

#include "com_hzy_exampledemo_ui_ndk_HelloWorld.h"

JNIEXPORT jstring JNICALL Java_com_hzy_exampledemo_ui_ndk_HelloWorld_sayHello(JNIEnv * env, jclass jc){
  return env->NewStringUTF("sayHello from c");
}