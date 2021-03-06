package com.mnykolaichuk.luv2code.springboot.thymeleafdemo.model.email;

import com.mnykolaichuk.luv2code.springboot.thymeleafdemo.model.entity.Car;
import com.mnykolaichuk.luv2code.springboot.thymeleafdemo.model.entity.EmployeeDetail;
import com.mnykolaichuk.luv2code.springboot.thymeleafdemo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponentsBuilder;

public class CarVerificationEmailContext extends AbstractEmailContext {

    @Autowired
    private CarService carService;

    private String token;

    @Override
    public <T> void init(T context1, T context2){
        //we can do any common configuration setup here
        // like setting up some base URL and context
        EmployeeDetail employeeDetail = (EmployeeDetail) context1; // we pass the customer informati
        Car car = (Car) context2;
        put("name", employeeDetail.getFirstName() + " " + employeeDetail.getLastName());
        put("car", car);
        setTo(employeeDetail.getEmail());

        setTemplateLocation("email/car-verification");
        setSubject("SomeOne try to add your car");
        setFrom("no-reply@javadevjournal.com");
    }

    public void setToken(String token) {
        this.token = token;
        put("token", token);
    }

    public void buildVerificationUrl(final String baseURL, final String token){
        final String url= UriComponentsBuilder.fromHttpUrl(baseURL)
                .path("/verify/car").queryParam("token", token).toUriString();
        put("verificationURL", url);
    }
}
