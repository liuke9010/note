package com.note.apple.error;

/**
 * @Author LiuKe
 * @Date 2019/2/16  12:36
 * @Desc
 */

public enum EnumBusinessError implements CommonError{
    //10001开头的为通用错误
    PARAMS_NOT_ALLOWED(10001,"参数不合法"),
    UNKNOWEN_ERROR(10002,"未知错误"),

    //20000开头用户信息相关错误定义
    ENCRYPT_ERROR(20001,"密码加密异常"),

    PARAM_VALIDATION_ERROR(20002,"短信验证码错误"),
    INPUT_PARAM_EMPTY(20003,"输入参数为空"),
    USER_EXIST(20004,"当前用户已存在"),
    ;



    private int errCode;
    private String errMsg;

    EnumBusinessError(int errCode,String errMsg){
        this.errCode=errCode;
        this.errMsg=errMsg;
    }
    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    /**
     * 对通用错误的处理，比如参数不合法等
     * @param errMsg
     * @return
     */
    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg=errMsg;

        return this;
    }
}
