package ua.kpi.jobsearch.config;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.util.Arrays;
import java.util.List;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
  
List<String> categories = Arrays.asList(
            "IT та Розробка",
            "Маркетинг і Дизайн",
            "Продажі та Клієнти",
            "Фінанси та Бухгалтерія",
            "Керівництво та HR",
            "Виробництво та Будівництво",
            "Освіта та Наука",
            "Сфера обслуговування",
            "Інше"
        );
        

        sce.getServletContext().setAttribute("appCategories", categories);
        System.out.println("Глобальні категорії успішно завантажено!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}