package org.anirudha.webservices.messanger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.anirudha.webservices.messanger.database.DatabaseClass;
import org.anirudha.webservices.messanger.model.Profile;


public class ProfileService {

	private Map<String, Profile> profiles = DatabaseClass.getProfiles();

	public ProfileService(){
		profiles.put("ani_kadam", new Profile(1L, "ani_kadam", "Anirudha", "Kadam"));
	}

	public List<Profile> getAllProfiles() {
		return new ArrayList<Profile>(profiles.values());
	}

	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}

	public Profile addProfile(Profile newProfile) {
		newProfile.setId(profiles.size() + 1);
		profiles.put(newProfile.getProfileName(), newProfile);
		return newProfile;
	}

	public Profile updateProfile(Profile updatedProfile) {
		if (updatedProfile.getProfileName().isEmpty()) {
			return null;
		}
		profiles.put(updatedProfile.getProfileName(), updatedProfile);
		return updatedProfile;
	}

	public Profile removeProfile(String profileName) {
		return profiles.remove(profileName);
	}

}
