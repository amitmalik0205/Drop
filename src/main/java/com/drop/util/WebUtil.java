package com.drop.util;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.drop.dao.IUserDao;
import com.drop.dao.domain.User;
import com.drop.exception.DropException;

@Component
public class WebUtil {
	
	private static final Logger logger = Logger.getLogger(WebUtil.class);
	
	private  static IUserDao userDao;
	
	@Autowired
	public WebUtil(@Qualifier(("userDao")) IUserDao userDao) {
		WebUtil.userDao = userDao;
	}

	public static void updateSessionUser(HttpSession session) {
		if (session != null) {
			User user = userDao.getEntity(getSessionUser(session).getUserId());
			session.setAttribute("user", user);
		}
	}

	public static User getSessionUser(HttpSession session) {
		if (session != null) {
			return (User) session.getAttribute("user");
		}
		return null;
	}
	
	public static boolean adminAuthorization(HttpSession session) {
		try {

			User user = getSessionUser(session);

			if (user == null) {
				return false;
			}

			/*String userRole = user.getRole().getRoleType();
			if (userRole.equals("admin")) {
				return true;
			}*/

		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			throw new DropException();
		}
		return false;
	}
	
	
	public static boolean userAuthorization(HttpSession session) {
		try {

			User user = getSessionUser(session);

			if (user == null) {
				return false;
			}

		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			throw new DropException();
		}
		return true;
	}
}
