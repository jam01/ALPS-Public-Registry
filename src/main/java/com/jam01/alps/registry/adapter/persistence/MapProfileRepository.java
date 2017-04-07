package com.jam01.alps.registry.adapter.persistence;

import com.jam01.alps.registry.domain.profile.*;
import org.springframework.stereotype.Repository;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by jam01 on 4/4/17.
 */

@Repository
public class MapProfileRepository implements ProfileRepository {
	private HashMap<String, Profile> map;

	public MapProfileRepository() {
		this.map = new HashMap<>();

		URI selfUrl, givenNameUrl, familyNameUrl, emailUrl, telephoneUrl;
		try {
			selfUrl = new URI("https://rawgithub.com/alps-io/profiles/master/contacts.xml");
			givenNameUrl = new URI("http://schema.org/givenName");
			familyNameUrl = new URI("http://schema.org/familyName");
			emailUrl = new URI("http://schema.org/email");
			telephoneUrl = new URI("http://schema.org/telephone");
		} catch (URISyntaxException e) {
			throw new IllegalStateException(e);
		}
//
//		Link self = new Link(new Rel("self"), selfUrl);
//
//		Id contactId = new Id("contact");
//		Id searchId = new Id("search");
//
//		Descriptor link = new Descriptor();
//		link.setId(new Id("link"));
//		link.setType(Type.SAFE);
//		link.setDoc(new Doc("link to contact"));
//
//		Descriptor search = new Descriptor();
//		search.setId(searchId);
//		search.setType(Type.SAFE);
//		search.setRt(new Rt(contactId));
//		search.setDoc(new Doc("search for contacts in the list"));
//
//		Descriptor searchName = new Descriptor();
//		searchName.setId(new Id("name"));
//		searchName.setType(Type.SEMANTIC);
//		searchName.setDoc(new Doc("input for searching"));
//
//		search.setDescriptors(Arrays.asList(searchName));
//
//		Descriptor givenName = new Descriptor();
//		givenName.setId(new Id("givenName"));
//		givenName.setType(Type.SEMANTIC);
//		givenName.setHref(new Href(givenNameUrl));
//
//		Descriptor familyName = new Descriptor();
//		familyName.setId(new Id("familyName"));
//		familyName.setType(Type.SEMANTIC);
//		familyName.setHref(new Href(familyNameUrl));
//
//		Descriptor email = new Descriptor();
//		email.setId(new Id("email"));
//		email.setType(Type.SEMANTIC);
//		email.setHref(new Href(emailUrl));
//
//		Descriptor telephone = new Descriptor();
//		telephone.setId(new Id("telephone"));
//		telephone.setType(Type.SEMANTIC);
//		telephone.setHref(new Href(telephoneUrl));
//
//		Descriptor contacts = new Descriptor();
//		contacts.setId(contactId);
//		contacts.setDoc(new Doc("Individual contact"));
//		contacts.setDescriptors(Arrays.asList(givenName, familyName, email, telephone));
//
//		Alps dummy = new Alps(Arrays.asList(search, contacts));
//		dummy.setLink(self);
//		dummy.setDoc(new Doc("A list of contacts that also supports search"));
//
//		Profile profile = new Profile("Contacts Profile");
//		profile.setAlps(dummy);
//
//		map.put("contacts", profile);
		Descriptor contact = new Descriptor(new Id("contact"));

		Descriptor givenName = new Descriptor(new Id("givenName"));
		givenName.setSuperDescriptor(new Descriptor(givenNameUrl));

		Descriptor familyName = new Descriptor(new Id("familyName"));
		familyName.setSuperDescriptor(new Descriptor(familyNameUrl));

		contact.addDescriptor(givenName);
		contact.addDescriptor(familyName);

		Descriptor searchByGivenName = new Descriptor(new Id("searchByGivenName"), Type.SAFE);
		searchByGivenName.addDescriptor(givenName);
		searchByGivenName.setRt(contact);

		Descriptor addContact = new Descriptor(new Id("addContact"), Type.UNSAFE);
		addContact.addDescriptor(contact);
		addContact.setRt(contact);

		Alps dummy = new Alps();
		dummy.setDescriptor(Arrays.asList(contact, addContact, searchByGivenName));

		Profile profile = new Profile("Contacts Profile", dummy);

		map.put("contacts", profile);
	}

	@Override
	public Profile getBy(String id) {
		return map.get(id);
	}

	@Override
	public Profile save(Profile profile) {
		String profileId = UUID.randomUUID().toString();
		profile.setId(profileId);
		map.put(profileId, profile);

		return profile;
	}
}
