package springAOP_annotations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springAOP_annotations.config.AppConfig;
import springAOP_annotations.service.InventoryService;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        InventoryService service =
                context.getBean(InventoryService.class);

        System.out.println("Stock: " + service.checkStock("ABC"));

        System.out.println("Stock: " + service.checkStock("ABC"));

        System.out.println("Stock: " + service.checkStock("XYZ"));
    }
}
