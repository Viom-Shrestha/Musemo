package com.musemo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.musemo.model.UserModel;
import com.musemo.service.ProfileService;
import com.musemo.util.PasswordUtil;
import com.musemo.util.ValidationUtil;

/**
 * Controller for handling admin profile management operations including viewing
 * and updating admin profile information.
 * 
 * @author Viom Shrestha
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/adminProfile" })
public class AdminProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProfileService service = new ProfileService();

	/**
	 * Handles GET requests to display the admin profile page. Retrieves current
	 * admin details from service and forwards to view page.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get current admin details
		UserModel admin = service.getAdmin();

		// Set admin object in request
		request.setAttribute("admin", admin);

		// Forward to admin profile view page
		request.getRequestDispatcher("/WEB-INF/pages/adminProfile.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests for updating admin profile information. Validates
	 * inputs, updates profile if valid, and returns appropriate response.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// First validate all form inputs
		String validationMessage = validateAdminProfileForm(request);
		if (validationMessage != null) {
			// If validation fails, handle error and return
			handleError(request, response, validationMessage);
			return;
		}

		// Get all parameters from request
		String fullName = request.getParameter("fullName");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String contact = request.getParameter("contact");
		String password = request.getParameter("password");

		// Create new admin model with updated details
		UserModel admin = new UserModel();
		admin.setFullName(fullName);
		admin.setUsername(username);
		admin.setEmail(email);
		admin.setContact(contact);

		// Only encrypt and set password if a new one was provided
		if (!ValidationUtil.isNullOrEmpty(password)) {
			admin.setPassword(PasswordUtil.encrypt(username, password));
		} else {
			admin.setPassword(null); // Keep existing password
		}

		// Attempt to update admin credentials in database
		boolean updated = service.updateAdminCredentials(admin);
		if (updated) {
			request.setAttribute("success", "Profile updated successfully.");
		} else {
			request.setAttribute("error", "Failed to update profile.");
		}

		// Refresh admin data from database
		admin = service.getAdmin();
		request.setAttribute("admin", admin);

		// Forward back to profile page with updated info
		request.getRequestDispatcher("/WEB-INF/pages/adminProfile.jsp").forward(request, response);
	}

	/**
	 * Validates all admin profile form inputs. Checks for duplicates and validates
	 * format of each field.
	 * 
	 * @param request The HttpServletRequest containing form parameters
	 * @return Error message if validation fails, null if all valid
	 */
	private String validateAdminProfileForm(HttpServletRequest request) {
		String username = request.getParameter("username");
		String fullName = request.getParameter("fullName");
		String email = request.getParameter("email");
		String contact = request.getParameter("contact");
		String password = request.getParameter("password");

		// Check if username/email/contact are already taken
		String duplicateError = service.isUserInfoTaken(username, email, contact);
		if (duplicateError != null) {
			return duplicateError;
		}

		// Validate full name contains only letters and spaces
		if (ValidationUtil.isNullOrEmpty(fullName) || !ValidationUtil.isAlphabetic(fullName.replaceAll("\\s+", ""))) {
			return "Full name must contain only letters and spaces.";
		}

		// Validate email format
		if (!ValidationUtil.isValidEmail(email)) {
			return "Invalid email format.";
		}

		// Validate phone number format (10 digits starting with 98)
		if (!ValidationUtil.isValidPhoneNumber(contact)) {
			return "Phone number must be 10 digits and start with 98.";
		}

		// Validate password meets complexity requirements if provided
		if (!ValidationUtil.isNullOrEmpty(password) && !ValidationUtil.isValidPassword(password)) {
			return "Password must be at least 8 characters long, include an uppercase letter, a number, and a symbol.";
		}

		return null;
	}

	/**
	 * Handles form validation errors by setting error message and temporary user
	 * model before redisplaying form.
	 */
	private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
			throws ServletException, IOException {
		// Set error message in request
		req.setAttribute("error", message);

		// Create temporary model with submitted values for form redisplay
		req.setAttribute("admin", createTempUserModel(req));

		// Forward back to form page
		doGet(req, resp);
	}

	/**
	 * Creates a temporary user model from request parameters for redisplaying form
	 * with submitted values after error.
	 */
	private UserModel createTempUserModel(HttpServletRequest req) {
		UserModel user = new UserModel();
		user.setFullName(req.getParameter("fullName"));
		user.setEmail(req.getParameter("email"));
		user.setContact(req.getParameter("contact"));
		return user;
	}
}