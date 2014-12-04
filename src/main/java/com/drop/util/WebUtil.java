package com.drop.util;

import javax.servlet.http.HttpSession;

import com.drop.dao.domain.User;

public class WebUtil {

	public static void updateSessionUser(HttpSession session, User user) {
		if (session != null) {
			session.setAttribute("user", user);
		}
	}

	public static User getSessionUser(HttpSession session) {
		if (session != null) {
			return (User) session.getAttribute("user");
		}
		return null;
	}
}
