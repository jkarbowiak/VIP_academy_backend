package org.itsurvival.books.mapper;

import org.itsurvival.books.entity.Author;
import org.itsurvival.books.entity.PersonalData;
import org.itsurvival.books.to.AuthorTo;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper extends AbstractMapper<Author, AuthorTo> {

    @Override
    public AuthorTo convertToTransportObject(Author source) {
    	if(source != null){
    		return mapAuthor(source);
    	}
    	return null;
    }

    @Override
    public Author convertToEntity(AuthorTo target) {
    	if(target != null){
    		return new Author(target.getId(), copyPersonalData(target.getPersonalData()), target.getNickName(),
                target.getVersion());
    	}
    	return null;
    }

    private AuthorTo mapAuthor(Author source) {
    	AuthorTo target = new AuthorTo();
        target.setId(source.getId());
        target.setNickName(source.getNickName());
        target.setPersonalData(copyPersonalData(source.getPersonalData()));
        target.setVersion(source.getVersion());
        return target;
    }

    private PersonalData copyPersonalData(PersonalData personalDataToCopy) {
        PersonalData personalData = null;
        if (personalDataToCopy != null) {
            personalData = new PersonalData(personalDataToCopy.getFirstName(), personalDataToCopy.getLastName(), personalDataToCopy.getBirthDate());
        }
        return personalData;
    }
}
