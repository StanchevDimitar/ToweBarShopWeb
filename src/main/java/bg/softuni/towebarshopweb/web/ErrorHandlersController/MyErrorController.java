//package bg.softuni.towebarshopweb.web.ErrorHandlersController;
//
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//public class MyErrorController implements ErrorController {
//
//    @RequestMapping("/bad-error")
//    public String handleError() {
//        //do something like logging
//        return "index";
//    }
//
//    @RequestMapping("/bad-error")
//    public String handleError(HttpServletRequest request) {
//        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//
//        if (status != null) {
//            Integer statusCode = Integer.valueOf(status.toString());
//
//            if(statusCode == HttpStatus.NOT_FOUND.value()) {
//                return "/ErrorPages/404";
//            }
//            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
//                return "cart";
//            }
//        }
//        return "index";
//    }
//}
