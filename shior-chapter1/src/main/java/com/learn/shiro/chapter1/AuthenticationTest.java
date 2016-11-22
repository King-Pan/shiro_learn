package com.learn.shiro.chapter1;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
/**
 * 1. 通过ini配置文件创建securityManager
 * 2. 调用subject.login方式提交认证
 *      内部通过securityManager进行认证，securityManager通过authenticator最终由ModularReamlAuthenticator进行认证
 * 3. ModularReamlAuthenticator拿着token
 * @author King-Pan
 *
 */
public class AuthenticationTest {
	@Test
	public void login() {
		// 创建SecurityManager工厂,通过ini配置文件创建SecurityManager工厂
		// Factory<> factory = new
		// IniSecurityManagerFactory("classpath:shiro-first.ini");
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-first.ini");

		SecurityManager securityManager = factory.getInstance();

		SecurityUtils.setSecurityManager(securityManager);

		Subject subject = SecurityUtils.getSubject();
		// 在认证提交前，需要准备token(令牌)
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "11111111");
		// 执行提交认证
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		// 是否认证通过
		boolean isAuthenticated = subject.isAuthenticated();

		System.out.println("是否认证通过:" + isAuthenticated);

		subject.logout();
		
		isAuthenticated = subject.isAuthenticated();

		System.out.println("登出后是否认证通过:" + isAuthenticated);

	}
}
