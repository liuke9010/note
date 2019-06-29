package com.note.apple.error;

/**
 * @Author LiuKe
 * @Date 2019/2/16  12:48
 * @Desc    包装器业务异常类实现
 *  继承RuntimeException在使用时不用在方法上抛出异常，而Exception则需要在方法上抛出异常或者try ,catch
 */

public class BusinessException extends RuntimeException implements CommonError{

    private CommonError commonError;

    //构造方法的作用：直接接收EnumBusinessError的传参用于构造业务异常
    public BusinessException(CommonError commonError){
//        super(commonError.getErrMsg());
//        this.commonError.getErrCode();
        super();
        this.commonError=commonError;
    }


    //接收自定义的errMsg的方式构造业务异常
    public BusinessException(CommonError commonError,String errMsg){
//        super(errMsg);
//        this.commonError=commonError;

        super();
        this.commonError=commonError;
        this.commonError.setErrMsg(errMsg);
    }


    @Override
    public int getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.commonError.setErrMsg(errMsg);
        return this;
    }
}
