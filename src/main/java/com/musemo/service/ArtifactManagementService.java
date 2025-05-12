package com.musemo.service;

import com.musemo.config.DbConfig;
import com.musemo.model.ArtifactModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtifactManagementService {
	private Connection dbConn;

	public ArtifactManagementService() {
		try {
			dbConn = DbConfig.getDbConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<ArtifactModel> getAllArtifacts() {
		List<ArtifactModel> artifacts = new ArrayList<>();

		String sql = "SELECT * FROM artifact";

		try (Connection conn = DbConfig.getDbConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				ArtifactModel artifact = new ArtifactModel();
				artifact.setArtifactID(rs.getString("artifactID"));
				artifact.setArtifactName(rs.getString("artifactName"));
				artifact.setArtifactType(rs.getString("artifactType"));
				artifact.setCreatorName(rs.getString("creatorName"));
				artifact.setTimePeriod(rs.getString("timePeriod"));
				artifact.setOrigin(rs.getString("origin"));
				artifact.setCondition(rs.getString("condition"));
				artifact.setDescription(rs.getString("description"));
				artifact.setArtifactImage(rs.getString("artifactImage"));
				artifacts.add(artifact);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return artifacts;
	}

	// General search method (private/internal)
	private List<ArtifactModel> searchArtifactsInternal(String keyword, String filter, String type) {
		List<ArtifactModel> artifactList = new ArrayList<>();
		StringBuilder sql = new StringBuilder(
				"SELECT artifactID, artifactName, artifactType, creatorName, timePeriod, origin, "
						+ "`condition`, description, artifactImage FROM artifact WHERE 1=1");

		List<String> parameters = new ArrayList<>();

		// Handle filter-based search (if filter is provided)
		if (filter != null && !filter.trim().isEmpty()) {
			String column;
			switch (filter) {
			case "artifactName":
			case "artifactType":
			case "origin":
			case "condition":
				column = filter;
				break;
			default:
				return getAllArtifacts(); // fallback for invalid filters
			}
			sql.append(" AND ").append(column).append(" LIKE ?");
			parameters.add("%" + keyword + "%");
		}
		// Handle general keyword and type search (if no filter)
		else {
			if (keyword != null && !keyword.trim().isEmpty()) {
				sql.append(" AND (artifactName LIKE ? OR description LIKE ?)");
				parameters.add("%" + keyword + "%");
				parameters.add("%" + keyword + "%");
			}

			if (type != null && !type.trim().isEmpty()) {
				sql.append(" AND artifactType = ?");
				parameters.add(type);
			}
		}

		try (Connection conn = DbConfig.getDbConnection();
				PreparedStatement ps = conn.prepareStatement(sql.toString())) {

			// Set all parameters
			for (int i = 0; i < parameters.size(); i++) {
				ps.setString(i + 1, parameters.get(i));
			}

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ArtifactModel artifact = new ArtifactModel();
				artifact.setArtifactID(rs.getString("artifactID"));
				artifact.setArtifactName(rs.getString("artifactName"));
				artifact.setArtifactType(rs.getString("artifactType"));
				artifact.setCreatorName(rs.getString("creatorName"));
				artifact.setTimePeriod(rs.getString("timePeriod"));
				artifact.setOrigin(rs.getString("origin"));
				artifact.setCondition(rs.getString("condition"));
				artifact.setDescription(rs.getString("description"));
				artifact.setArtifactImage(rs.getString("artifactImage"));
				artifactList.add(artifact);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return artifactList;

	}

	// Public API methods
	public List<ArtifactModel> searchArtifactByKeywordAndType(String keyword, String type) {
		return searchArtifactsInternal(keyword, null, type);
	}

	public List<ArtifactModel> searchArtifactByFilter(String filter, String keyword) {
		return searchArtifactsInternal(keyword, filter, null);
	}

	public boolean deleteArtifact(String artifactId) {
		String sql = "DELETE FROM artifact WHERE artifactId = ?";
		try (PreparedStatement ps = dbConn.prepareStatement(sql)) {
			ps.setString(1, artifactId);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArtifactModel getArtifactById(String id) {
		ArtifactModel artifact = null;
		String query = "SELECT * FROM artifact WHERE artifactId = ?";

		try (Connection conn = DbConfig.getDbConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, id);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				artifact = new ArtifactModel();
				artifact.setArtifactID(rs.getString("artifactId"));
				artifact.setArtifactName(rs.getString("artifactName"));
				artifact.setArtifactType(rs.getString("artifactType"));
				artifact.setDescription(rs.getString("description"));
				artifact.setTimePeriod(rs.getString("timePeriod"));
				artifact.setOrigin(rs.getString("origin"));
				artifact.setCreatorName(rs.getString("creatorName"));
				artifact.setCondition(rs.getString("condition"));
				artifact.setArtifactImage(rs.getString("artifactImage"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return artifact;
	}

	public boolean updateArtifact(ArtifactModel artifact) {
		String sql = "UPDATE artifact SET artifactName = ?, artifactType = ?, creatorName = ?, timePeriod = ?, origin = ?, `condition` = ?, description = ?, artifactImage = ? WHERE artifactId = ?";
		try (PreparedStatement ps = dbConn.prepareStatement(sql)) {
			ps.setString(1, artifact.getArtifactName());
			ps.setString(2, artifact.getArtifactType());
			ps.setString(3, artifact.getCreatorName());
			ps.setString(4, artifact.getTimePeriod());
			ps.setString(5, artifact.getOrigin());
			ps.setString(6, artifact.getCondition());
			ps.setString(7, artifact.getDescription());
			ps.setString(8, artifact.getArtifactImage());
			ps.setString(9, artifact.getArtifactID());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void addArtifact(ArtifactModel artifact) {
		String sql = "INSERT INTO artifact (artifactId, artifactName, artifactType, creatorName, timePeriod, origin, `condition`, description, artifactImage) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement ps = dbConn.prepareStatement(sql)) {
			ps.setString(1, artifact.getArtifactID());
			ps.setString(2, artifact.getArtifactName());
			ps.setString(3, artifact.getArtifactType());
			ps.setString(4, artifact.getCreatorName());
			ps.setString(5, artifact.getTimePeriod());
			ps.setString(6, artifact.getOrigin());
			ps.setString(7, artifact.getCondition());
			ps.setString(8, artifact.getDescription());
			ps.setString(9, artifact.getArtifactImage());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
