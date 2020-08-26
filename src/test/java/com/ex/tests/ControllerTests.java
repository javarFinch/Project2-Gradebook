package com.ex.tests;

import com.ex.Dao.Dao;
import com.ex.Models.ClazzEntity;
import com.ex.Models.UsersEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ControllerTests {

    @Autowired
    private Dao dao;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    @Transactional
    @Rollback
    public void studentListTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/admin/student")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback
    public void teacherListTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/admin/teacher")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback
    public void classListTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/admin/classes")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback
    public void newUserTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/admin/newUser")
                .content(asJsonString(new UsersEntity(999999, "test",
                        "test", "test", "student")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback
    public void newClassTest() throws Exception {
        String[] student1 = dao.createUser();
        dao.updateUser(Integer.parseInt(student1[0]), "test", "test", student1[1], "student");
        String[] student2 = dao.createUser();
        dao.updateUser(Integer.parseInt(student2[0]), "test", "test", student2[1], "student");

        String[] teacher = dao.createUser();
        dao.updateUser(Integer.parseInt(teacher[0]), "test", "test", teacher[1], "teacher");

        Map<String,Object> map = new HashMap<>();
        map.put("name", "test");
        map.put("subject", "test");
        map.put("teacherId", teacher[0]+"-(Sample Teacher)");
        ArrayList<Integer> list = new ArrayList<>();
        list.add(Integer.parseInt(student1[0]));
        list.add(Integer.parseInt(student2[0]));
        map.put("studentList", list);
        mvc.perform(MockMvcRequestBuilders
                .post("/admin/newClass")
                .content(asJsonString(map))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback
    public void assignStudentTest() throws Exception {
        String[] ids = {"9999", "9999"};
        mvc.perform(MockMvcRequestBuilders
                .post("/admin/assignStudent")
                .content(asJsonString(ids))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback
    public void classListTeacherTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/teacher/{id}", 1001)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback
    public void updateGrade() throws Exception {
        String[] student1 = dao.createUser();
        dao.updateUser(Integer.parseInt(student1[0]), "test", "test", student1[1], "student");
        String[] student2 = dao.createUser();
        dao.updateUser(Integer.parseInt(student2[0]), "test", "test", student2[1], "student");
        String[] teacher = dao.createUser();
        dao.updateUser(Integer.parseInt(teacher[0]), "test", "test", teacher[1], "teacher");
        ClazzEntity clazz = dao.createClass("test", "test", Integer.parseInt(teacher[0]));
        dao.assignStudent(clazz.getId(), Integer.parseInt(student1[0]));
        dao.assignStudent(clazz.getId(), Integer.parseInt(student2[0]));
        dao.createAssignmentAndAssign("test", "test", 100, "28-08-2020", clazz.getId());

        ArrayList<String> list = new ArrayList<>();
        list.add(student1[0]+"=90");
        list.add(student2[0]+"=80");

        Map<String,Object> map = new HashMap<>();
        map.put("name", "test");
        map.put("type", "test");
        map.put("data", list);

        mvc.perform(MockMvcRequestBuilders
                .post("/teacher/update")
                .content(asJsonString(map))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback
    public void updateClassObjectTest() throws Exception {
        String[] teacher = dao.createUser();
        dao.updateUser(Integer.parseInt(teacher[0]), "test", "test", teacher[1], "teacher");
        ClazzEntity clazz = dao.createClass("test", "test", Integer.parseInt(teacher[0]));

        mvc.perform(MockMvcRequestBuilders
        .get("/teacher/updateClass/{id}", clazz.getId())
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback
    public void newAssignmentTest() throws Exception {
        String[] student1 = dao.createUser();
        dao.updateUser(Integer.parseInt(student1[0]), "test", "test", student1[1], "student");
        String[] student2 = dao.createUser();
        dao.updateUser(Integer.parseInt(student2[0]), "test", "test", student2[1], "student");
        String[] teacher = dao.createUser();
        dao.updateUser(Integer.parseInt(teacher[0]), "test", "test", teacher[1], "teacher");
        ClazzEntity clazz = dao.createClass("test", "test", Integer.parseInt(teacher[0]));
        dao.assignStudent(clazz.getId(), Integer.parseInt(student1[0]));

        Map<String,Object> map = new HashMap<>();
        map.put("name", "test");
        map.put("type", "homework");
        map.put("points", 100);
        map.put("date", "year=2020, month=8, day=28");
        map.put("classId", clazz.getId());

        mvc.perform(MockMvcRequestBuilders
                .post("/teacher/newAssignment")
                .content(asJsonString(map))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback
    public void studentClassList() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/student/{id}", 1002)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback
    public void loginTest() throws Exception {
        String[] student1 = dao.createUser();
        dao.updateUser(Integer.parseInt(student1[0]), "test", "test", student1[1], "student");
        mvc.perform(MockMvcRequestBuilders
                .get("/user/{id}/{password}", Integer.parseInt(student1[0]), student1[1])
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback
    public void userUpdate() throws Exception {
        String[] student1 = dao.createUser();
        dao.updateUser(Integer.parseInt(student1[0]), "test", "test", student1[1], "student");

        Map<String,Object> map = new HashMap<>();
        map.put("userId", student1[0]);
        map.put("firstName", "test");
        map.put("lastName", "test");
        map.put("new1", "password");
        map.put("type", "student");

        mvc.perform(MockMvcRequestBuilders
                .put("/user/update")
                .content(asJsonString(map))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback
    public void userChangePassword () throws Exception {
        String[] student1 = dao.createUser();
        dao.updateUser(Integer.parseInt(student1[0]), "test", "test", student1[1], "student");

        mvc.perform(MockMvcRequestBuilders
                .put("/user/newpassword/{userId}/{newPassword}", Integer.parseInt(student1[0]), student1[1])
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
