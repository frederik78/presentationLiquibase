//package com.techsolcom.carnet.controler;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.ModelAttribute;
//
//import ca.techsolcom.usurvey.model.User;
//
//public abstract class AdminBaseController extends BaseController {
//
//	protected abstract String getMenuSectionName();
//
//	@ModelAttribute
//	public void init(ModelMap model, HttpServletRequest request) {
//		super.init(model, request);
//		if (SecurityContextHolder.getContext().getAuthentication() != null && !SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")) {
//			User loggedUser = getLoggedUser();
//			if (loggedUser.getClient() != null) {
//				model.put("clientConfig", getLoggedUser().getClient());
//			}
//		}
//		model.put("menuSectionName", getMenuSectionName());
//	}
//
//	/**
//	 * Check if:<br />
//	 * - logged user has permissions to view or modify an object<br />
//	 * - logged user is an administrator
//	 *
//	 * @param id
//	 *            id of the object
//	 */
//	protected abstract void checkClientSecurity(Long id);
//
//	/**
//	 * Check if logged user is an administrator
//	 */
//	protected abstract void checkAdminRoleSecurity();
//}
