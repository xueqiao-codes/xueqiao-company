package xueqiao.hosting.machine.daemon.report;

import org.soldier.platform.svr_platform.comm.ErrorInfo;

public class ErrorCode {
    public static ErrorInfo TaskMessageBlankErrorInfo = new ErrorInfo(1301, "Invalid task, task message is blank.");

    public static ErrorInfo TaskMessageFormatErrorInfo = new ErrorInfo(1302, "Invalid task, task message can not convert to task.");

    public static ErrorInfo HostingMachineInitMachineIdNotMatchErrorInfo = new ErrorInfo(1303, "This machine is already used by other service, please check.");

    public static ErrorInfo HostingMachineNotInitErrorInfo = new ErrorInfo(1304, "Hosting Machine is not init yet.");

    public static ErrorInfo HostingMachineAlreadyBindErrorInfo = new ErrorInfo(1305, "Hosting Machine is already binded by other company group.");

    public static ErrorInfo HostingServiceRelatedInfoErrorInfo = new ErrorInfo(1306, "Hosting service related info not found.");

    public static ErrorInfo HostingServiceExpiredTimeErrorInfo = new ErrorInfo(1308, "Hosting service expiredTime error.");
}
