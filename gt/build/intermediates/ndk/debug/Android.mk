LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := mem_fill_tool
LOCAL_LDFLAGS := -Wl,--build-id
LOCAL_SRC_FILES := \
	/Users/chenwei/a23qws_github/AndroidStudy2/gt/src/main/jni/Android.mk \
	/Users/chenwei/a23qws_github/AndroidStudy2/gt/src/main/jni/Application.mk \
	/Users/chenwei/a23qws_github/AndroidStudy2/gt/src/main/jni/com_tencent_wstt_gt_api_utils_MemFillTool.c \

LOCAL_C_INCLUDES += /Users/chenwei/a23qws_github/AndroidStudy2/gt/src/main/jni
LOCAL_C_INCLUDES += /Users/chenwei/a23qws_github/AndroidStudy2/gt/src/debug/jni

include $(BUILD_SHARED_LIBRARY)
