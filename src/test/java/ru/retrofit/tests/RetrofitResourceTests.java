package ru.retrofit.tests;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import retrofit2.Response;
import ru.retrofit.dto.responses.ListResourceResp;
import ru.retrofit.dto.responses.SingleResourceResp;
import ru.retrofit.managers.ResourceManager;

import static java.net.HttpURLConnection.*;
import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@SpringBootTest
class RetrofitResourceTests {
    @Autowired
    private ResourceManager resourceManager;

    @Test
    @DisplayName("GET LIST RESOURCE - success")
    void checkResourceList() {
        int pageNum = 2;
        Response<ListResourceResp> resp = resourceManager.getResourceList(pageNum);
        ListResourceResp listResourceDTO = resp.body();

        log.info(resourceManager.dtoToJson(listResourceDTO));

        assertAll(
                () -> assertEquals(HTTP_OK, resp.code()),
                () -> assertEquals(pageNum, listResourceDTO.getPage()),
                () -> assertEquals(listResourceDTO.getPerPage(), listResourceDTO.getData().size()),
                () -> assertNotNull(listResourceDTO.getData().get(0)),
                () -> assertNotNull(listResourceDTO.getSupport())
        );
    }

    @Test
    @DisplayName("GET LIST RESOURCE - empty set")
    void checkEmptyResourceList() {
        int pageNum = 5;
        Response<ListResourceResp> resp = resourceManager.getResourceList(pageNum);
        ListResourceResp listResourceDTO = resp.body();

        log.info(resourceManager.dtoToJson(listResourceDTO));

        assertAll(
                () -> assertEquals(HTTP_OK, resp.code()),
                () -> assertEquals(pageNum, listResourceDTO.getPage()),
                () -> assertEquals(0, listResourceDTO.getData().size()),
                () -> assertNotNull(listResourceDTO.getSupport())
        );
    }

    @Test
    @DisplayName("GET SINGLE RESOURCE - success")
    void checkGettingSingleResource() {
        int resourceId = 12;
        Response<SingleResourceResp> resp = resourceManager.getResource(resourceId);
        SingleResourceResp singleResourceDTO = resp.body();

        log.info(resourceManager.dtoToJson(singleResourceDTO));

        assertAll(
                () -> assertEquals(HTTP_OK, resp.code()),
                () -> assertEquals(resourceId, singleResourceDTO.getData().getId()),
                () -> assertEquals("honeysuckle", singleResourceDTO.getData().getName()),
                () -> assertEquals(2011, singleResourceDTO.getData().getYear()),
                () -> assertEquals("#D94F70", singleResourceDTO.getData().getColor()),
                () -> assertEquals("18-2120", singleResourceDTO.getData().getPantoneValue()),
                () -> assertNotNull(singleResourceDTO.getSupport())
        );
    }

    @Test
    @DisplayName("GET SINGLE RESOURCE - not found")
    void checkSingleResourceNotFound() {
        int resourceId = 16;
        Response<SingleResourceResp> resp = resourceManager.getResource(resourceId);
        SingleResourceResp singleResourceDTO = resp.body();

        log.info(resourceManager.dtoToJson(singleResourceDTO));

        assertAll(
                () -> assertEquals(HTTP_NOT_FOUND, resp.code()),
                () -> assertNull(singleResourceDTO)
        );
    }
}