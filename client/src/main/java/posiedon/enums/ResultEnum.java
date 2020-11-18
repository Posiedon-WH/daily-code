package posiedon.enums;

public enum ResultEnum {
    SUCCESS,//成功
    ERROR_PARAM,//参数错误
    EXCEPTION,//日志解析异常
    ERROR_LOG_PATH,//文件路径创建失败
    ERROR_DECRYPT,//解密过程中出错
    ERROR_DATABASE,//保存到数据库出错
    ERROR_GET_USERID_FAIL, // 获取userId失败
    SAVE_ORIGIN, // 日志解析错误, 但是保存了原始日志
    ERROR_NOT_SUPPORT; // 不支持该功能
}
