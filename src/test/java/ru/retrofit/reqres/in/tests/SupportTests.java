package ru.retrofit.reqres.in.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.retrofit.helpers.RootUtils;
import ru.retrofit.reqres.in.dto.responses.*;
import ru.retrofit.reqres.in.managers.ResourceManager;
import ru.retrofit.reqres.in.managers.UserManager;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SupportTests extends RootUtils {
    @Autowired
    private UserManager userManager;

    @Autowired
    private ResourceManager resourceManager;

    @Test
    @DisplayName("Проверка блока Support")
    void checkSupportObject() {
        int pageNum = 1;
        int objectId = 4;
        SupportResp expectedDTO = jsonFileToDTO("SupportDTO.json", SupportResp.class);

        ListUsersResp listUsersDTO = userManager.getUserList(pageNum).body();
        SingleUserResp singleUserDTO = userManager.getUser(objectId).body();
        ListResourceResp listResourceDTO = resourceManager.getResourceList(pageNum).body();
        SingleResourceResp singleResourceResp = resourceManager.getResource(objectId).body();

        log(listUsersDTO.getSupport(), "Список пользователей");
        log(singleUserDTO.getSupport(), "Сингл пользователь");
        log(listResourceDTO.getSupport(), "Список ресурсов");
        log(singleResourceResp.getSupport(), "Сингл ресурс");

        assertAll(
                () -> assertEquals(expectedDTO, listUsersDTO.getSupport()),
                () -> assertEquals(expectedDTO, singleUserDTO.getSupport()),
                () -> assertEquals(expectedDTO, listResourceDTO.getSupport()),
                () -> assertEquals(expectedDTO, singleResourceResp.getSupport())
        );
    }
}
