package com.lec.spmvc.mapper;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.lec.spmvc.dto.TodoDTO;
import com.lec.spmvc.service.TodoService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoServicesTest {

    @Autowired
    private TodoService todoService;

    @Test
    public void testGetTime() {
        log.info("============> " + todoService.getTime());
    }
    
    @Test
    public void testRegister(){

        TodoDTO todoDTO = TodoDTO.builder()
                .title("Test......")
                .dueDate(LocalDate.now())
                .writer("user1")
                .build();

        todoService.register(todoDTO);

    }	
}
