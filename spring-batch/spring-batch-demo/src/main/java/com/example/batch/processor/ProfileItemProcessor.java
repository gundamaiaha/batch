package com.example.batch.processor;

import com.example.batch.model.Profile;
import com.example.batch.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;


@Slf4j
public class ProfileItemProcessor implements ItemProcessor<User, Profile> {


    @Override
    public Profile process(User user) throws Exception {
        log.info("processing user data ...{}",user);

        Profile transformedProfile= new Profile();
        transformedProfile.setUserId(user.getId());
        transformedProfile.setFullName(user.getFirstName()+" "+user.getLastName());

        return transformedProfile;
    }
}
