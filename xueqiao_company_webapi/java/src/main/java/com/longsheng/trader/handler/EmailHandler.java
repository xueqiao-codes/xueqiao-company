package com.longsheng.trader.handler;

import com.longsheng.trader.error.TraderSiteErrorInfo;
import com.longsheng.trader.utils.QconfUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;
import xueqiao.company.CompanyEntry;
import xueqiao.company.CompanyUser;
import xueqiao.mail.ContentType;
import xueqiao.mail.MailContent;
import xueqiao.mail.MailEntity;
import xueqiao.mail.MailSettings;
import xueqiao.mail.ao.thriftapi.client.MailAoStub;

public class EmailHandler {

    private static EmailHandler instance = new EmailHandler();

    public static EmailHandler getInstance() {
        return instance;
    }

    /**
     * 发送邮件
     * 通知用户已被添加为公司成员
     * */
    public void sendEmailForAddCompanyUser(CompanyEntry companyEntry, CompanyUser user, String passwd) throws TException {
        if (StringUtils.isBlank(user.getEmail())) {
            throw TraderSiteErrorInfo.ERROR_EMAIL_EMPTY;
        }

        MailContent mailContent = getAddCompanyUserContent(companyEntry, user, passwd);
        if (mailContent == null) {
            AppLog.e("EmailHandler # sendEmailForAddCompanyUser ---- mailContent is null, send mail fail");
            return;
        }

        MailAoStub mailAoStub = new MailAoStub();
        MailEntity mailEntity = new MailEntity();

        mailEntity.addToReceivers(user.getEmail());
        mailEntity.setSubject("欢迎使用雪橇，这是您的登录信息");
        mailEntity.setContent(mailContent);

        mailAoStub.sendMail(RandomUtils.nextInt(), 4000, getSettings(), mailEntity);
    }

    /**
     *  发送邮件
     *  通知用户密码已被重置
     * */
    public void sendEmailForResetPasswd(CompanyUser user, String newPasswd) throws TException {
        if (StringUtils.isBlank(user.getEmail())) {
            throw TraderSiteErrorInfo.ERROR_EMAIL_EMPTY;
        }

        MailContent mailContent = getResetCompanyUserPasswdContent(user, newPasswd);
        if (mailContent == null) {
            AppLog.e("EmailHandler # sendEmailForResetPasswd ---- mailContent is null, send mail fail");
            return;
        }

        MailAoStub mailAoStub = new MailAoStub();
        MailEntity mailEntity = new MailEntity();

        mailEntity.addToReceivers(user.getEmail());
        mailEntity.setSubject("您的雪橇密码重置成功，欢迎回到雪橇");
        mailEntity.setContent(mailContent);

        mailAoStub.sendMail(getSettings(), mailEntity);
    }

    /*
    * 如果字符长度大于等于4，则隐藏中间的字符
    * */
    private String getSecretUserName(String userName) {
        if (userName == null) {
            return "";
        }
        if (userName.length() < 4) {
            return userName;
        }
        StringBuilder secretUserNameBuilder = new StringBuilder();
        secretUserNameBuilder.append(userName.substring(0,2));
        secretUserNameBuilder.append("*****");
        secretUserNameBuilder.append(userName.substring(userName.length() - 2));
        return secretUserNameBuilder.toString();
    }

    private MailSettings getSettings() {
        MailSettings settings = new MailSettings();
        settings.setSmtpHost("smtp.xueqiao.cn");
        settings.setSender("service@xueqiao.cn");
        settings.setPasswd("SerVice1919");
        settings.setSenderAlias("雪橇科技");
        return settings;
    }

    private MailContent getAddCompanyUserContent(CompanyEntry companyEntry, CompanyUser user, String password) {
        String mailContent = QconfUtil.getNewCompanyUserMailContent();
        if (StringUtils.isBlank(mailContent)) {
            return null;
        }

        String cnName = user.getCnName();
        String companyName = companyEntry.getCompanyName();
        String companyCode = companyEntry.getCompanyCode();
        String userName = user.getUserName();
        String passwd = password;

        mailContent = mailContent.replace("{cnName}", cnName);
        mailContent = mailContent.replace("{companyName}", companyName);
        mailContent = mailContent.replace("{companyCode}", companyCode);
        mailContent = mailContent.replace("{userName}", userName);
        mailContent = mailContent.replace("{password}", passwd);

        MailContent content = new MailContent();
        content.setContentType(ContentType.HTML);
        content.setContent(mailContent);
//        content.setContent("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>欢迎使用雪橇，这是您的登录信息</title><style>.attention-text{line-height: 14px;color: rgba(245, 166, 35, 1);font-size: 14px;text-align: left;font-family: STHeitiSC-Light;}.title{line-height: 19px;color: rgba(48, 48, 48, 1);font-size: 18px;text-align: left;font-weight: bold;font-family: STHeitiSC-Medium;}.text-item{margin-top: 10px;margin-bottom: 10px;line-height: 14px;color: rgba(48, 48, 48, 1);font-size: 14px;text-align: left;font-family: STHeitiSC-Light;}.margin-top{margin-top: 30px;}.sign-text{text-align: right;}</style></head><body><div class=\"title\">雪橇科技</div><hr/><div class=\"text-item margin-top\"><span>尊敬的  " + cnName + "（先生/女士），您好。</span></div><div  class=\"text-item\"><span>您已经被  " + companyName + " 添加为公司用户，您可以通过下方的下载地址下载雪橇投资管理系统客户端，通过您的用户信息登录使用，如有疑问请及时联系贵公司相关负责人。</span></div><div class=\"text-item margin-top\"><span>公司登录名：" + companyCode + "</span></div><div class=\"text-item\"><span>您的用户名：" + userName + "</span></div><div class=\"text-item\"><span>您的密码：" + passwd + "</span></div><div class=\"text-item\"><span class=\"attention-text\">请登录后在客户左下角的系统设置内修改密码。</span></div><div class=\"text-item margin-top\"><span>客户端下载地址：</span><a href=\"www.1234qwer.cn/mouou/123\">www.1234qwer.cn/mouou/123</a></div><div class=\"text-item margin-top sign-text\"><span>此致  雪橇科技</span></div><hr/></body></html>");
        return content;
    }

    private MailContent getResetCompanyUserPasswdContent(CompanyUser user, String newPasswd) {
        String mailContent = QconfUtil.getResetCompanyUserPasswdMailContent();
        if (StringUtils.isBlank(mailContent)) {
            return null;
        }

        String cnName = user.getCnName();
        String userName = getSecretUserName(user.getUserName());
        String password = newPasswd;

        mailContent = mailContent.replace("{cnName}", cnName);
        mailContent = mailContent.replace("{userName}", userName);
        mailContent = mailContent.replace("{password}", password);

        MailContent content = new MailContent();
        content.setContentType(ContentType.HTML);
        content.setContent(mailContent);
//        content.setContent("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>欢迎使用雪橇，这是您的登录信息</title><style>.attention-text{line-height: 14px;color: rgba(245, 166, 35, 1);font-size: 14px;text-align: left;font-family:STHeitiSC-Light;}.title{line-height: 19px;color: rgba(48, 48, 48, 1);font-size: 18px;text-align: left;font-weight: bold;font-family: STHeitiSC-Medium;}.text-item{margin-top: 10px;margin-bottom: 10px;line-height: 14px;color: rgba(48, 48, 48, 1);font-size: 14px;text-align: left;font-family: STHeitiSC-Light;}.margin-top{margin-top: 30px;}.sign-text{text-align: right;}</style></head><body><div class=\"title\">雪橇科技</div><hr/><div class=\"text-item margin-top\"><span>尊敬的  " + cnName + "（先生/女士），您好。</span></div><div class=\"text-item\"><span>您用户名为  " + userName + " 的账号的密码已重置成功，请使用新密码登录。</span></div><div class=\"text-item\"><span>新密码：" + passwd + "</span></div><div class=\"text-item\"><span class=\"attention-text\">请登录后在客户左下角的系统设置内修改密码。</span></div><div class=\"text-item margin-top sign-text\"><span>此致  雪橇科技</span></div><hr/></body></html>");
        return content;
    }
}
