//package com.techsolcom.carnet.controler;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//import java.util.Set;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.servlet.LocaleResolver;
//import org.springframework.web.servlet.support.RequestContextUtils;
//
//import ca.techsolcom.usurvey.model.User;
//import ca.techsolcom.usurvey.util.Constants;
//
//public abstract class BaseController {
//
//	/** The Constant SYSTEM_MODEL_KEY. */
//	public static final String SYSTEM_MODEL_KEY = "system";
//
//	/** The Constant STATUS_MESSAGE_KEY. */
//	public static final String STATUS_MESSAGE_KEY = "statusMessageKey";
//
//	protected static final String STATUS_KEY = "status";
//
//	protected void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
//		final LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
//		if (localeResolver != null) {
//			localeResolver.setLocale(request, response, locale);
//		}
//	}
//
//	protected Locale getLocale(HttpServletRequest request) {
//		Locale locale = new Locale("fr", "CA");
//
//		final LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
//		if (localeResolver != null) {
//			locale = localeResolver.resolveLocale(request);
//		}
//
//		return locale;
//	}
//
//	@ModelAttribute
//	public void init(ModelMap model, HttpServletRequest request) {
//		if (SecurityContextHolder.getContext().getAuthentication() != null && !SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")) {
//
//			final Locale locale = getLocale(request);
//			String language = null;
//			if (locale.getLanguage().equals(Constants.FRENCH_LANGUAGE)) {
//				language = Constants.ENGLISH_LANGUAGE;
//			} else {
//				language = Constants.FRENCH_LANGUAGE;
//			}
//			final String requestUrl = request.getRequestURL().toString();
//
//			StringBuilder parameters = new StringBuilder();
//			if (request.getMethod().equals("GET")) {
//				final Set<String> params = request.getParameterMap().keySet();
//				for (final String paramName : params) {
//					if (!paramName.equals("locale")) {
//						final String paramValue = request.getParameter(paramName);
//						parameters.append(paramName).append("=").append(paramValue).append("&");
//					}
//				}
//			}
//
//			List<String> languages = new ArrayList<String>();
//			languages.add(Constants.FRENCH_LANGUAGE);
//			languages.add(Constants.ENGLISH_LANGUAGE);
//			model.put("languages", languages);
//
//			model.put("currentLanguages", locale.getLanguage());
//			model.put("toggleRequestUrl", requestUrl + "?" + parameters + "locale=" + language);
//			model.put("loggedUser", getLoggedUser());
//		}
//
//	}
//
//	protected User getLoggedUser() {
//		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//	}
//
//	protected void successSave(ModelMap model) {
//		model.put(STATUS_KEY, Constants.STATUS_OK);
//		model.put(STATUS_MESSAGE_KEY, "common.form.msg.success");
//	}
//
//	private void error(ModelMap model, String errorMessage) {
//		model.put(STATUS_KEY, Constants.STATUS_NO);
//		model.put(STATUS_MESSAGE_KEY, errorMessage);
//	}
//
//	private void warning(ModelMap model, String errorMessage) {
//		model.put(STATUS_KEY, Constants.STATUS_WARNING);
//		model.put(STATUS_MESSAGE_KEY, errorMessage);
//	}
//
//	protected void errorLock(ModelMap model) {
//		error(model, "common.message.lock");
//	}
//
//	protected void errorSave(ModelMap model) {
//		error(model, "common.form.msg.error");
//	}
//
//	protected void errorUnsupportedBrowser(ModelMap model) {
//		warning(model, "common.message.unsupportedbrowser");
//	}
//
//}
