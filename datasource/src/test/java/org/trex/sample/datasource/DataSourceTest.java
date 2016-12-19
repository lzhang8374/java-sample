package org.trex.sample.datasource;


import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DataSourceTest {

    private UserDao userDao = null;


    @Before
    public void before() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        this.userDao = context.getBean(UserDao.class);
    }


    private class MyThread implements Runnable {

        private UserDao userDao;

        public MyThread(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        public void run() {
            int size = this.userDao.getAll("select * from user").size();
            System.out.println("size:" + size);
        }
    }


    @Test
    public void testQ() throws Exception {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new MyThread(this.userDao));
            thread.start();
        }
        System.in.read(); // 按任意键退出
    }

}


