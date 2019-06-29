package com.note.apple.error;

/**
 * @Author LiuKe
 * @Date 2019/2/16  12:33
 * @Desc
 */

public interface CommonError {
    int getErrCode();
    String getErrMsg();
    CommonError setErrMsg(String errMsg);
}
