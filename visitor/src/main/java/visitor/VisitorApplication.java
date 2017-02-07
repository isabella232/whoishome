package visitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@SpringBootApplication
@EnableDiscoveryClient
@Controller
public class VisitorApplication {

    @Autowired
    ContactHomeowner contactHomeowner;

    public static void main(String[] args) {
        SpringApplication.run(VisitorApplication.class, args);
    }

    @RequestMapping(value = "/knock", method = RequestMethod.GET)
    public String knock(Model model) {
      String knockResponse = contactHomeowner.checkIfAnybodyIsHome();
      String message = formatMessage(knockResponse);
      model.addAttribute("message", message);

      return "knock";
    }

    @RequestMapping(value = "/greeting", method = RequestMethod.POST)
    public String knock(@RequestParam(value="visitorsName", defaultValue="Bob") String name, Model model) {
      String greetingResponse = contactHomeowner.respondToGreeting(name);
      String message = formatMessage(greetingResponse);
      model.addAttribute("message", message);

      return "greeting";
    }

    private String formatMessage(String aResponse) {
      String message = "<h1>Conversation:<br/><small>" + aResponse + "</small></h1>";

      return message;
    }

}
