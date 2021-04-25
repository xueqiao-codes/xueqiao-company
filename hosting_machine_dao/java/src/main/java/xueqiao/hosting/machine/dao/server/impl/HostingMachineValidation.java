package xueqiao.hosting.machine.dao.server.impl;

import org.apache.commons.lang.StringUtils;
import org.soldier.platform.svr_platform.comm.ErrorInfo;

import com.antiy.error_code.ErrorCodeInner;

import xueqiao.hosting.machine.HostingMachine;
import xueqiao.hosting.machine.HostingMachineErrorCode;

public class HostingMachineValidation {
	
	public static void checkRelatedId(long relatedId) throws ErrorInfo {
		if (relatedId <= 0) {
			throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode()
					, "relatedId should not <= 0");
		}
	}
	
	public static void checkMachineId(long machineId) throws ErrorInfo {
		if (machineId <= 0) {
			throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "machineId should not <= 0");
		}
	}
	
	public static void checkHostname(String hostname) throws ErrorInfo {
		if (StringUtils.isBlank(hostname)) {
			throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "hostname should not be blank");
		}
		if (hostname.trim().length() != hostname.length()) {
			throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "hostname should not have space for trim");
		}
	}
	
	public static void checkInnerIP(String innerIp) throws ErrorInfo {
		if (StringUtils.isBlank(innerIp) ) {
			throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "inner ip should not be blank");
		}
		if (innerIp.trim().length() != innerIp.length()) {
			throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "innerIp should not have space for trim");
		}
	}
	
	public static void checkOuterIP(String outerIp) throws ErrorInfo {
		if (StringUtils.isBlank(outerIp)) {
			throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "outer ip should not be blank");
		}
		if (outerIp.trim().length() != outerIp.length()) {
			throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "outer ip should not have space for trim");
		}
	}
	
	public static void checkDuplicate(HostingMachine duplicateMachine
			, HostingMachine compareMachine) throws ErrorInfo {
		if (duplicateMachine.getMachineHostname().equals(compareMachine.getMachineHostname())) {
			throw new ErrorInfo(HostingMachineErrorCode.HOSTING_NAME_ALREADY_EXISTED.getValue()
					, "hostname already existed");
		}
		if (duplicateMachine.getMachineInnerIP().equals(compareMachine.getMachineInnerIP())) {
			throw new ErrorInfo(HostingMachineErrorCode.HOSTING_NAME_ALREADY_EXISTED.getValue()
					, "innerIP already existed");
		}
		if (duplicateMachine.getMachineOuterIP().equals(compareMachine.getMachineOuterIP())) {
			throw new ErrorInfo(HostingMachineErrorCode.HOSTING_OUTERIP_ALREADY_EXISTED.getValue()
					,"outerIP already existed!");
		}
	}
	
}
