package bg.softuni.towebarshopweb.web;

import bg.softuni.towebarshopweb.model.entity.Order;
import bg.softuni.towebarshopweb.model.entity.PriorityPhoneNumbers;
import bg.softuni.towebarshopweb.repository.OrderRepository;
import bg.softuni.towebarshopweb.repository.PhoneRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class CompletedOrdersController {

    private final OrderRepository orderRepository;
    private final PhoneRepository phoneRepository;

    public CompletedOrdersController(OrderRepository orderRepository, PhoneRepository phoneRepository) {
        this.orderRepository = orderRepository;
        this.phoneRepository = phoneRepository;
    }

    @GetMapping("/all-orders/fromPhone")
    public String getFromPhone(Model model) {


        List<PriorityPhoneNumbers> processedFalse = phoneRepository.findAllByIsProcessedFalseOrderByInsertedTime();
        model.addAttribute("processedFalse", processedFalse);

        List<PriorityPhoneNumbers> processedTrue = phoneRepository.findAllByIsProcessedTrueOrderByInsertedTime();
        model.addAttribute("processedTrue", processedTrue);
        return "orders-byphone-waiting";

    }


    @GetMapping("/all-orders/fromPhone/remove/{id}")
    public String setFinishPhoneNumbers(@PathVariable Long id){

        Optional<PriorityPhoneNumbers> byId = phoneRepository.findById(id);
        if (byId.isPresent()){
            PriorityPhoneNumbers number = byId.get();
            number.setProcessed(true);
            number.setCompletedTime(LocalDateTime.now());
            phoneRepository.save(number);
        }

        return "redirect:/all-orders/fromPhone";

    }

    @GetMapping("/all-orders/fromWeb")
    public String example(Model model) {

        List<Order> notSent = orderRepository.findAllByIsFinishedTrueAndIsSentFalseOrderByCompletedTime();
        model.addAttribute("notSent", notSent);
        List<Order> sent = orderRepository.findAllByIsFinishedTrueAndIsSentTrueOrderByCompletedTime();
        model.addAttribute("sent", sent);

        List<PriorityPhoneNumbers> phoneNumbers = phoneRepository.findAll();
        model.addAttribute("phoneNumbers", phoneNumbers);
        return "orders-waiting";
    }

    @GetMapping("/all-orders/fromWeb/remove/{id}")
    public String setFinish(@PathVariable Long id){

        Optional<Order> byId = orderRepository.findById(id);
        if (byId.isPresent()){
            Order order = byId.get();
            order.setIsSent(true);
            order.setSentTime(LocalDateTime.now());
            orderRepository.save(order);
        }

        return "redirect:/all-orders/fromWeb";

    }
}
