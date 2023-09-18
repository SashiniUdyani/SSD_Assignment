package lk.backend.service.impl;

import java.util.Optional;
import lk.backend.entity.AppUser;
import lk.backend.repository.UserRepository;
import lk.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public AppUser login(AppUser appUser) {
        Optional<AppUser> userOptional = userRepository.login(appUser.getEmail(), appUser.getPassword());
        if (userOptional.isPresent()) {
            AppUser appUserObj = userOptional.get();
            appUserObj.setIdFormatted(appUser.getFormattedId());
            return new AppUser(appUserObj);
        }
        return null;
    }

}
