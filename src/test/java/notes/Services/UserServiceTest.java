package notes.Services;

import notes.dao.UserStore;
import notes.exception.ValidationException;
import notes.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class) // запускаем вместе с MockitoJunitRunner.Class
public class UserServiceTest {
    private User user = new User();

    @Mock // заглушка для класса (в нашем случае это UserStore)
    UserStore userStore;

    @InjectMocks // вставляем заглушку в классе (в нашем случае это UserService)
    UserService userService;

    @Before // Перед запуском инициализируем заглушки в этом тестовом классе
    public void setup(){
        initMocks(UserServiceTest.class);
    }

    @Test(expected = ValidationException.class)
    public void TestAddWithUserNull(){
        userService.add(null);
    }

    @Test(expected = ValidationException.class)
    public void TestAddWithUserNameNull(){
        userService.add(user);
    }

    @Test(expected = ValidationException.class)
    public void TestAddWithUserNameIsEmpty(){
        user.setName("");
        userService.add(user);
    }

    @Test(expected = ValidationException.class)
    public void TestAddWithLoginNull(){
        user.setName("some");
        userService.add(user);
    }

    @Test(expected = ValidationException.class)
    public void TestAddWithLoginIsEmpty(){
        user.setName("some");
        user.setLogin("");
        userService.add(user);
    }

    @Test(expected = ValidationException.class)
    public void TestAddWithPasswordNull(){
        user.setName("some");
        user.setLogin("some");
        userService.add(user);
    }

    @Test(expected = ValidationException.class)
    public void TestAddWithPasswordIsEmpty(){
        user.setName("some");
        user.setLogin("some");
        user.setPassword("");
        userService.add(user);
    }

    @Test(expected = ValidationException.class)
    public void TestUpdateWithUserNull(){
        userService.update(null, 1);
    }

    @Test(expected = ValidationException.class)
    public void TestUpdateWithUserNullIdNull(){
        userService.update(null, null);
    }

    @Test(expected = ValidationException.class)
    public void TestUpdateWithUserNameNull(){
        userService.update(user, null);
    }

    @Test(expected = ValidationException.class)
    public void TestUpdateWithUserNameEmpty(){
        user.setName("");
        userService.update(user, null);
    }

    @Test(expected = ValidationException.class)
    public void TestUpdateWithUserLoginNull(){
        user.setName("some");
        userService.update(user, null);
    }

    @Test(expected = ValidationException.class)
    public void TestUpdateWithUserLoginEmpty(){
        user.setName("some");
        user.setLogin("");
        userService.update(user, null);
    }

    @Test(expected = ValidationException.class)
    public void TestUpdateWithUserPasswordNull(){
        user.setName("some");
        user.setLogin("some");
        userService.update(user, null);
    }

    @Test(expected = ValidationException.class)
    public void TestUpdateWithUserPasswordEmpty(){
        user.setName("some");
        user.setLogin("some");
        user.setPassword("");
        userService.update(user, null);
    }

    @Test(expected = ValidationException.class)
    public void TestUpdateWithUserIdNull(){
        user.setName("some");
        user.setLogin("some");
        user.setPassword("some");
        userService.update(user, null);
    }

    @Test(expected = ValidationException.class)
    public void TestUpdateWithUserIdZero(){
        user.setName("some");
        user.setLogin("some");
        user.setPassword("some");
        userService.update(user, 0);
    }

    @Test(expected = ValidationException.class)
    public void TestUpdateWithUserIdMinus(){
        user.setName("some");
        user.setLogin("some");
        user.setPassword("some");
        userService.update(user, -1);
    }

    @Test(expected = ValidationException.class)
    public void TestDeleteNull(){
        userService.delete(null);
    }

    @Test(expected = ValidationException.class)
    public void TestDeleteZero(){
        userService.delete(0);
    }

    @Test(expected = ValidationException.class)
    public void TestDeleteMinus(){
        userService.delete(-1);
    }

    @Test
    public void TestDeleteWithLegalId() {
        doNothing().when(userStore).delete(isA(Integer.class));
        userService.delete(1);
        verify(userStore, times(1)).delete(1);
    }

    @Test (expected = ValidationException.class)
    public void TestFindOneNull(){
        userService.findOne(null);
    }

    @Test (expected = ValidationException.class)
    public void TestFindOneZero(){
        userService.findOne(0);
    }

    @Test (expected = ValidationException.class)
    public void TestFindOneMinus(){
        userService.findOne(-1);
    }

    @Test
    public void TestFindOneWithLegalArgument(){
        when(userStore.findOne(1)).thenReturn(new User());
        assertEquals(userService.findOne(1), userStore.findOne(1));
    }

    @Test
    public void TestFindAllWithLegalArgument(){
        when(userStore.findAll()).thenReturn(new ArrayList<User>());
        assertEquals(userService.findAll(), userStore.findAll());
    }








}