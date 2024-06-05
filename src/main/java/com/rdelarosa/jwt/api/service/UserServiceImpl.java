package com.rdelarosa.jwt.api.service;

import com.rdelarosa.jwt.api.exception.CreatingUserWithExistentEmailException;
import com.rdelarosa.jwt.api.exception.UserErrorResponse;
import com.rdelarosa.jwt.api.util.CoDate;
import org.springframework.beans.factory.annotation.Autowired;
import com.rdelarosa.jwt.api.entity.User ;
import com.rdelarosa.jwt.api.entity.Phone ;
import com.rdelarosa.jwt.api.repository.UserDAO;
import com.rdelarosa.jwt.api.vo.PhoneVO;
import com.rdelarosa.jwt.api.vo.UserVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.transaction.Transactional;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO theUserDAO) {
        this.userDAO = theUserDAO;
    }

    @Override
    @Transactional
    public void deactivateById(Integer userId) {
        userDAO.deactivateById(userId);
        User user = userDAO.getUserById(userId);
    }

    @Override
    public List<UserVO> findAll() {
        List<User> list = this.userDAO.findAll();
        List<UserVO> voList = new ArrayList<UserVO>();
        PhoneVO[]  phonesVOArray = null;

        for (User user : list) {

            List<Phone> listPhone =  this.userDAO.getPhonesByUserId(user.getId());

            phonesVOArray = new PhoneVO[ listPhone.size() ];
            int i=0;
            for (Phone phone:listPhone) {
                phonesVOArray[i] = new PhoneVO(phone.getNumber(), phone.getCitycode(), phone.getCountrycode());
                i++ ;
            }

            UserVO vo = new UserVO(user.getId(), user.getName(), user.getEmail(), user.getPassword(),user.getCreated().toString(), ((user.getModified()!=null)? user.getModified().toString() : "")
                    , user.getLastLogin(), user.getToken(), user.getActive(), phonesVOArray);
            voList.add(vo);
        }
        return voList;
    }


    @Override
    public UserVO getUserById(Integer userId) {
        User theUser = this.userDAO.getUserById(userId);
        List<Phone> phones = this.userDAO.getPhonesByUserId(userId);
        PhoneVO[] phonesVO = new PhoneVO[phones.size()];

        for (int i=0; i<phones.size(); i++) {
            phonesVO[i] = new PhoneVO(phones.get(i).getNumber() ,phones.get(i).getCitycode() , phones.get(i).getCountrycode() );
        }

        //if ( theUser.getLastLogin()!=null ) {
        //   CoDate.newDate(theUser.getLastLogin());
        //}

        return new UserVO( userId, theUser.getName(), theUser.getEmail(), theUser.getPassword(), theUser.getCreated().toString()
                , ((theUser.getModified()!=null)? theUser.getModified().toString() : "")
                , theUser.getLastLogin(), theUser.getToken(), theUser.getActive() ,phonesVO);
    }

    @Override
    public UserVO getUserByUsername(String username) {
        User theUser = this.userDAO.findByUserName(username);
        return getUserById(theUser.getId());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByUserName(username);
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new ArrayList<>());
    }

    @Override
    public UserVO save(UserVO theUser) {
        Date now = new Date();
        User user = new User(null,theUser.getName(), theUser.getEmail(), theUser.getPassword(), theUser.getEmail(), now,null,now,null, theUser.getActive());

        User userTmp = null;

        userTmp = this.userDAO.findByUserName(theUser.getEmail());

        if (userTmp!=null) {
           throw new CreatingUserWithExistentEmailException("Se esta tratando de crear un usuario con un email que ya esta asignado a otro usuario");
        }

        this.userDAO.save(user);

        PhoneVO[] arrayPhones = new PhoneVO[theUser.getPhones().length];
        for (int i=0 ; i < theUser.getPhones().length ; i++ ) {
            Phone phone = new Phone(user.getId(), theUser.getPhones()[i].getNumber(), theUser.getPhones()[i].getCitycode(), theUser.getPhones()[i].getCountrycode());
            this.userDAO.savePhone(phone);
            arrayPhones[i] = new PhoneVO(phone.getNumber(), phone.getCitycode(), phone.getCountrycode());
        }
        Date creationDate = user.getCreated();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");
        StringBuffer creationDateSB = new StringBuffer("");
        FieldPosition fp = new  FieldPosition(0);
        creationDateSB = sdf.format(creationDate,creationDateSB,fp);

        UserVO theUserVOResp = new UserVO(user.getId(), user.getName(),user.getPassword(), user.getEmail(), creationDateSB.toString()
                , "",user.getLastLogin(), user.getToken(), user.getActive(),arrayPhones);
        return theUserVOResp;
    }

    @Override
    public UserVO update(UserVO theUserVO) {
        User user = new User(theUserVO.getId(),theUserVO.getName(), theUserVO.getEmail(), theUserVO.getPassword(), theUserVO.getEmail(), null,new Date(),theUserVO.getLastLogin()
                , theUserVO.getToken(), theUserVO.getActive());
        this.userDAO.update(user);

        PhoneVO[] arrayPhones = new PhoneVO[theUserVO.getPhones().length];
        for (int i=0 ; i < theUserVO.getPhones().length ; i++ ) {
            Phone phone = new Phone(user.getId(), theUserVO.getPhones()[i].getNumber(), theUserVO.getPhones()[i].getCitycode(), theUserVO.getPhones()[i].getCountrycode());
            this.userDAO.updatePhone(phone);
            arrayPhones[i] = new PhoneVO(phone.getNumber(), phone.getCitycode(), phone.getCountrycode());
        }

        User aUser = this.userDAO.getUserById(user.getId());

        Date creationDate = aUser.getCreated();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd Z HH:mm:ss z");
        StringBuffer creationDateSB = new StringBuffer();
        FieldPosition fp = new  FieldPosition(0);
        if (creationDate!=null) {
            creationDateSB = sdf.format(creationDate, creationDateSB, fp);
        }

        Date modificationDate = aUser.getModified();
        fp = new  FieldPosition(0);
        StringBuffer modificationDateSB = new StringBuffer();
        if (modificationDate!=null) {
            modificationDateSB = sdf.format(modificationDate,modificationDateSB,fp);
        }
        UserVO theUserVOResp = null ;

        theUserVOResp = new UserVO(aUser.getId(), aUser.getName(), aUser.getEmail(), aUser.getPassword(), creationDateSB.toString(), modificationDateSB.toString()
                , aUser.getLastLogin(),aUser.getToken(), aUser.getActive() ,arrayPhones);

        return theUserVOResp;
    }

    @Override
    public void updateLastLogin(String username, String token) {
        Date currentDate = new Date();

        UserVO userVO = getUserByUsername(username);
        userVO.setLastLogin(new Date());
        userVO.setToken(token);
        update(userVO);
    }
}
