package com.musemo.service;

import com.musemo.model.UserModel;
import com.musemo.config.DbConfig;
import com.musemo.util.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Service class for managing user profiles.
 */
public class ProfileService {

	private Connection dbConn;
	private boolean isConnectionError = false;

	/**
	 * Constructor initializes the database connection.
	 */
	public ProfileService() {
		try {
			dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			isConnectionError = true;
		}
	}

	/**
	 * Retrieves user details based on username.
	 *
	 * @param username the username
	 * @return UserModel object or null if not found / connection error
	 */
	public UserModel getUserByUsername(String username) {
		if (isConnectionError) {
			System.out.println("Connection Error!");
			return null;
		}

		UserModel user = null;
		// ✅ Include the userImage column in the SELECT query
		String sql = "SELECT username, fullName, password, gender, email, dateOfBirth, contact, userImage FROM user WHERE username = ?";
		try (PreparedStatement ps = dbConn.prepareStatement(sql)) {
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new UserModel();
				user.setUsername(rs.getString("username"));
				user.setFullName(rs.getString("fullName"));
				user.setPassword(rs.getString("password"));
				user.setGender(rs.getString("gender"));
				user.setEmail(rs.getString("email"));
				java.sql.Date sqlDate = rs.getDate("dateOfBirth");
				if (sqlDate != null) {
					user.setDateOfBirth(sqlDate.toLocalDate());
				}
				user.setContact(rs.getString("contact"));
				// ✅ Retrieve the userImage from the ResultSet
				user.setUserImage(rs.getString("userImage"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * Retrieves the total number of bookings for the user.
	 *
	 * @param username the username
	 * @return total bookings count
	 */
	public int getTotalBookings(String username) {
		if (isConnectionError) {
			System.out.println("Connection Error!");
			return 0;
		}

		int totalBookings = 0;
		String sql = "SELECT COUNT(*) FROM booking WHERE username = ?";
		try (PreparedStatement ps = dbConn.prepareStatement(sql)) {
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				totalBookings = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalBookings;
	}

	/**
	 * Retrieves the number of distinct exhibitions visited by the user.
	 *
	 * @param username the username
	 * @return count of exhibitions visited
	 */
	public int getExhibitionsVisited(String username) {
		if (isConnectionError) {
			System.out.println("Connection Error!");
			return 0;
		}

		int exhibitionsVisited = 0;
		String sql = "SELECT COUNT(DISTINCT exhibitionId) FROM booking WHERE username = ?";
		try (PreparedStatement ps = dbConn.prepareStatement(sql)) {
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				exhibitionsVisited = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exhibitionsVisited;
	}

	/**
	 * Updates the user profile information.
	 *
	 * @param user the updated UserModel object
	 */
	public void updateUser(UserModel user) {
		if (isConnectionError) {
			System.out.println("Connection Error!");
			return;
		}

		String sql = "UPDATE user SET fullName = ?, password = ?, gender = ?, email = ?, dateOfBirth = ?, contact = ?, userImage = ? WHERE username = ?";
		try (PreparedStatement ps = dbConn.prepareStatement(sql)) {
			ps.setString(1, user.getFullName());

			// Only update password if a new one is provided
			if (user.getPassword() != null && !user.getPassword().isEmpty()) {
				String encryptedPassword = PasswordUtil.encrypt(user.getUsername(), user.getPassword());
				ps.setString(2, encryptedPassword);
			} else {
				UserModel existingUser = getUserByUsername(user.getUsername());
				if (existingUser != null) {
					ps.setString(2, existingUser.getPassword());
				} else {
					System.err.println("Warning: Existing user not found while updating password.");
					return;
				}
			}
			ps.setString(3, user.getGender());
			ps.setString(4, user.getEmail());

			if (user.getDateOfBirth() != null) {
				ps.setDate(5, java.sql.Date.valueOf(user.getDateOfBirth()));
			} else {
				ps.setDate(5, null);
			}
			ps.setString(6, user.getContact());
			ps.setString(7, user.getUserImage());
			ps.setString(8, user.getUsername());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String isUserInfoTaken(String username, String email, String contact) {
		if (dbConn == null) {
			return "Database connection not available.";
		}

		String query = "SELECT username, email, contact FROM user WHERE (email = ? OR contact = ?) AND username != ?";

		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			stmt.setString(1, email);
			stmt.setString(2, contact);
			stmt.setString(3, username); // Exclude current user's record

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if (email.equalsIgnoreCase(rs.getString("email"))) {
					return "Email is already registered.";
				} else if (contact.equals(rs.getString("contact"))) {
					return "Contact number is already registered.";
				}
			}
		} catch (SQLException e) {
			System.err.println("Error checking user uniqueness: " + e.getMessage());
			e.printStackTrace();
			return "Server error occurred. Please try again later.";
		}

		return null; // No conflicts
	}

	public boolean deleteUser(String username) {
		if (isConnectionError) {
			System.out.println("Connection Error!");
			return false;
		}
		String sql = "DELETE FROM user WHERE username = ?";
		try (PreparedStatement stmt = dbConn.prepareStatement(sql)) {
			stmt.setString(1, username);
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public UserModel getAdmin() {
		String sql = "SELECT username, password, fullName, email, contact FROM user WHERE role='Admin'";
		try (PreparedStatement ps = dbConn.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				UserModel admin = new UserModel();
				admin.setUsername(rs.getString("username"));
				admin.setPassword(rs.getString("password"));
				admin.setFullName(rs.getString("fullName"));
				admin.setEmail(rs.getString("email"));
				admin.setContact(rs.getString("contact"));
				return admin;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateAdminCredentials(UserModel admin) {
		String sql = "UPDATE user SET fullName = ?, username = ?, password = ?, email = ?, contact = ? WHERE role = 'Admin'";

		try (PreparedStatement ps = dbConn.prepareStatement(sql)) {
			ps.setString(1, admin.getFullName());
			ps.setString(2, admin.getUsername());

			// Handle password logic
			if (admin.getPassword() != null && !admin.getPassword().isEmpty()) {
				ps.setString(3, admin.getPassword()); // Password is already encrypted in the controller
			} else {
				// Fetch the existing password if not provided
				UserModel existing = getAdmin();
				if (existing == null) {
					System.out.println("Existing admin not found.");
					return false;
				}
				ps.setString(3, existing.getPassword());
			}

			// Set email and contact fields
			ps.setString(4, admin.getEmail());
			ps.setString(5, admin.getContact());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}