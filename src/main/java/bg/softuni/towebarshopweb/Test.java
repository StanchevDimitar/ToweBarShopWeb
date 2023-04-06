package bg.softuni.towebarshopweb;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class Test {

    private int counter;

    public int soutCounter(){
        return this.counter++;
    }
}
